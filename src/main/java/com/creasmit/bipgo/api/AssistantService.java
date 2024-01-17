/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.AssistantAdapter;
import com.creasmit.bipgo.controller.ConfirmationCompteController;
import com.creasmit.bipgo.controller.IAssistantController;
import com.creasmit.bipgo.controller.IConfirmationCompteController;
import com.creasmit.bipgo.entity.Assistant;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Structure;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import one.creas.emalib.http.HttpResponse;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.Utils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Christ Mantima
 */
@Path("/assistant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class AssistantService {

    private IAssistantController controller;
    private IConfirmationCompteController confirmationCompteController;

    public AssistantService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IAssistantController) context.getBean("assistantController");
        this.confirmationCompteController=(ConfirmationCompteController)context.getBean("confirmationCompteController");
    }

    // affichage de la liste des assistants
    @GET
    @Path("/{structure}")
    public String get(@PathParam("structure") int structureId) {
        List<AssistantAdapter> assistantList = this.controller.getAssistantByStructure(new Structure(structureId));
        String response = HttpResponse.build()
                .setStatus("200")
                .setResponse(assistantList)
                .toJSON();
        return response;
    }

    // création de l'assistant
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            AssistantAdapter d = new Gson().fromJson(data, new TypeToken<AssistantAdapter>() {
            }.getType());
            AssistantAdapter assistantAdapter = this.controller.add(d);
            if (assistantAdapter != null) {
                try {
                    ConfirmationCompte cc = new ConfirmationCompte();
                    cc.setFkIdentite(d.getIdentite().getId());
                    cc.setDateCreate(new Date());
                    cc.setEmail(d.getIdentite().getEmail());
                    cc.setPhone(d.getIdentite().getNumTel());
                    cc.setPin(d.getAssistant().getId() + Utils.generatePin(9));
                    this.confirmationCompteController.init(cc);
                } catch (Exception e) {
                    Log.i(this, e);
                }
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(assistantAdapter)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setMessage("Erreur d'enregistrement de l'assistant")
                        .toJSON();
            }
        }
        return response;
    }

    //update Assistant
    @PUT
    public String update(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            AssistantAdapter aa = new Gson().fromJson(data, new TypeToken<AssistantAdapter>() {
            }.getType());
            AssistantAdapter application = this.controller.update(aa);
            if (application != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(aa)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur de modification de l'assistant")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de modification assistant avec la valeur null")
                    .toJSON();
        }
        return response;

    }

    //delete Assistant
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        AssistantAdapter assistant = this.controller.delete(new Assistant(id));

        if (assistant != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(assistant)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de suppression")
                    .toJSON();
        }
    }

}
