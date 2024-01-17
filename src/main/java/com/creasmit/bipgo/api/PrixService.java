/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.controller.IPrixController;
import com.creasmit.bipgo.entity.Prix;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
 * @author georges
 */
@Path("/prix")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrixService {

    private IPrixController controller;

    public PrixService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IPrixController) context.getBean("prixController");
    }

    //add the Price
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (data != null) {
            Prix p = new Gson().fromJson(data, new TypeToken<Prix>() {
            }.getType());
            Prix prixAdapter = this.controller.add(p);
            if (prixAdapter != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(p)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement de Prix")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement Prix avec la valeur null")
                    .toJSON();
        }
        return response;

    }
    
    //update the Price
    @PUT
    public String update(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            Prix prix = new Gson().fromJson(data, new TypeToken<Prix>() {
            }.getType());
            Prix p = this.controller.update(prix);
            if (p != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(p)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur de modification de Prix ")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de modification Prix avec la valeur null")
                    .toJSON();
        }
        return response;

    }
    
    //delete Price
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        String response = null;
        Prix prix = this.controller.delete(new Prix(id));

        if (prix != null) {
            response = HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(prix)
                    .toJSON();
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de suppression Prix")
                    .toJSON();
        }
        return response;
    }

}
