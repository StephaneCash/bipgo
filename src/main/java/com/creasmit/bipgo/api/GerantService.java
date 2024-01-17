/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.GerantAdapter;
import com.creasmit.bipgo.controller.IConfirmationCompteController;
import com.creasmit.bipgo.controller.IGerantController;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Gerant;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import one.creasmit.emalib.media.MediaUtility;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Christ Mantima
 */
@Path("/gerant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GerantService {

    private IGerantController controller;
    private IConfirmationCompteController confirmationCompteController;

    public GerantService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IGerantController) context.getBean("gerantController");
        this.confirmationCompteController = (IConfirmationCompteController) context.getBean("confirmationCompteController");
    }

    // listing des gerant
    @GET
    @Path("/{structureId}")
    public String getListByStructure(@PathParam("structureId") int structureId) {
        GerantAdapter gerant = this.controller.get(new Structure(structureId));
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(gerant)
                .toJSON();
        return reponse;
    }

    //Create Gerant
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (data != null) {
            GerantAdapter ga = new Gson().fromJson(data, new TypeToken<GerantAdapter>() {
            }.getType());

            Identite i = ga.getIdentite();
            try {
                String carteBase64 = i.getCarteIdentite();
                i.setCarteIdentite(MediaUtility.convertBase64ToFile("D:\\Projets\\Data repo", carteBase64));
            } catch (Exception e) {
            }
            ga.setIdentite(i);

            GerantAdapter gerant = this.controller.add(ga);
            if (gerant != null) {
                try {
                    ConfirmationCompte cc = new ConfirmationCompte();
                    cc.setFkIdentite(gerant.getIdentite().getId());
                    cc.setDateCreate(new Date());
                    cc.setEmail(gerant.getIdentite().getEmail());
                    cc.setPhone(gerant.getIdentite().getNumTel());
                    cc.setPin(gerant.getGerant().getId() + Utils.generatePin(9));
                    this.confirmationCompteController.init(cc);
                } catch (Exception e) {
                    Log.i(this, e);
                }
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(gerant)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement Gerant")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement Gerant avec la valeur null")
                    .toJSON();

        }
        return response;
    }

    //update Gerant
    @PUT
    public String update(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            GerantAdapter a = new Gson().fromJson(data, new TypeToken<GerantAdapter>() {
            }.getType());
            GerantAdapter gerant = this.controller.update(a);
            if (gerant != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(a)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement de Gerant")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement Gerant avec la valeur null")
                    .toJSON();
        }
        return response;

    }

    //delete Gerant
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        String response = null;
        GerantAdapter gerant = this.controller.delete(new Gerant(id));

        if (gerant != null) {
            response = HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(gerant)
                    .toJSON();
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de suppression")
                    .toJSON();
        }
        return response;
    }

}
