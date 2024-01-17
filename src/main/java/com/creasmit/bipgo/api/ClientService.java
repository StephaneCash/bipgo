/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.ClientAdapter;
import com.creasmit.bipgo.controller.IClientController;
import com.creasmit.bipgo.entity.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author CREASMIT_ZEUS
 */
@Path("/client")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class ClientService {

    private IClientController controller;

    public ClientService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IClientController) context.getBean("clientController");
    }

    // read Client
    @GET
    public String getList() {
        List<ClientAdapter> client = this.controller.listClient();

        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(client)
                .toJSON();
        return reponse;
    }

    //Create Agent
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (data != null) {
            ClientAdapter c = new Gson().fromJson(data, new TypeToken<ClientAdapter>() {
            }.getType());
            ClientAdapter client = this.controller.add(c);
            if (client != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(c)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement du Client")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement Client avec la valeur null")
                    .toJSON();

        }
        return response;
    }

    @PUT
    //
    public String update(String data) {
        String response = null;
        Log.i(this, data);

        if (!data.isEmpty()) {
            ClientAdapter a = new Gson().fromJson(data, new TypeToken<ClientAdapter>() {
            }.getType());
            ClientAdapter application = this.controller.update(a);
            if (application != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(a)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur de mmise à jour du CLIENT")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement Client avec la valeur null")
                    .toJSON();
        }
        return response;
    }
    
    //delete Client
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        String response = null;
        ClientAdapter client = this.controller.delete(new Client(id));

        if (client != null) {
            response = HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(client)
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
