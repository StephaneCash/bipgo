/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;



import com.creasmit.bipgo.adapter.CompteAdapter;
import com.creasmit.bipgo.controller.ICompteController;
import com.creasmit.bipgo.entity.Compte;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
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
@Path("/compte")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompteService {
    private ICompteController controller;

    public CompteService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (ICompteController) context.getBean("compteController");
    }
    
    
    //Create Compte
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (data != null) {
            Compte c = new Gson().fromJson(data, new TypeToken<CompteAdapter>() {
            }.getType());
            Compte compte = this.controller.add(c);
            if (compte != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(c)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement de COMPTE")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement de COMPTE avec la valeur null")
                    .toJSON();
            
        }
        return response;
    }
    
    
    
    
    //delete Agent
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        String response = null;
        Compte compte = this.controller.delete(new Compte(id));

        if (compte != null) {
            response = HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(compte)
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
