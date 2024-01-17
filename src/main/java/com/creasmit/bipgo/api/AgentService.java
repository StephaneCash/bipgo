/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.AgentAdapter;
import com.creasmit.bipgo.controller.ConfirmationCompteController;
import com.creasmit.bipgo.controller.IAgentController;
import com.creasmit.bipgo.controller.IConfirmationCompteController;
import com.creasmit.bipgo.entity.Agent;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.TypeAgent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
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
 * @author georges
 */
@Path("/agent")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgentService {

    private IAgentController controller;
    private IConfirmationCompteController confirmationCompteController;

    public AgentService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IAgentController) context.getBean("agentController");
        this.confirmationCompteController = (ConfirmationCompteController) context.getBean("confirmationCompteController");

    }

    //Read Agent par typeAgent
    @GET
    @Path("/{typeAgent}")
    public String getAgents(@PathParam("typeAgent") int typeAgent) {
        List<AgentAdapter> agent = this.controller.listAgent(new TypeAgent(typeAgent));
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(agent)
                .toJSON();
        return reponse;
    }

    //Create Agent
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (data != null) {
            AgentAdapter a = new Gson().fromJson(data, new TypeToken<AgentAdapter>() {
            }.getType());
            AgentAdapter agent = this.controller.add(a);
            if (agent != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(a)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement AGENT")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement AGENT avec la valeur null")
                    .toJSON();

        }
        return response;
    }

    //update Agent
    @PUT
    public String update(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            AgentAdapter a = new Gson().fromJson(data, new TypeToken<AgentAdapter>() {
            }.getType());
            AgentAdapter application = this.controller.update(a);
            if (application != null) {
                try {
                    ConfirmationCompte cc = new ConfirmationCompte();
                    cc.setFkIdentite(a.getIdentite().getId());
                    cc.setDateCreate(new Date());
                    cc.setEmail(a.getIdentite().getEmail());
                    cc.setPhone(a.getIdentite().getNumTel());
                    cc.setPin(a.getAgent().getId() + Utils.generatePin(9));
                    this.confirmationCompteController.init(cc);
                } catch (Exception e) {
                    Log.i(this, e);
                }
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(a)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement de AGENT")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement AGENT avec la valeur null")
                    .toJSON();
        }
        return response;

    }

    //delete Agent
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        String response = null;
        AgentAdapter agent = this.controller.delete(new Agent(id));

        if (agent != null) {
            response = HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(agent)
                    .toJSON();
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de suppression")
                    .toJSON();
        }
        return response;
    }

    @POST
    @Path("/activeCompte")
    public String activeCompte(String data) {
        Agent agent = new Gson().fromJson(data, new TypeToken<Agent>() {
        }.getType());

        AgentAdapter agentAdapter = this.controller.activeCompte(agent);
        if (agentAdapter != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setResponse(agentAdapter)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Code d'activation invalide")
                    .toJSON();
        }
    }
}
