/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.controller.IPosController;
import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.StatutPos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.Consumes;
import java.util.List;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author emmanueltombo
 */
@Path("/pos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PosService {

    private IPosController controller;

    public PosService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IPosController) context.getBean("posController");
    }

    //affichage de listePOS  
    @GET
    public String getList() {
        List<Pos> applications = this.controller.listing();

        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(applications)
                .toJSON();
        return reponse;
    }
    
    @GET
    @Path("/find/{code}")
    public String getListPos(@PathParam("code") String code) {
        List<Pos> applications = this.controller.find(code);

        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(applications)
                .toJSON();
        return reponse;
    }

    //affichage de liste de POS  par critere
    @GET
    @Path("/{statut}")
    public String get(@PathParam("statut") int statutId) {
        List<Pos> posList = this.controller.getByStatut(new StatutPos(statutId));
        String response = HttpResponse.build()
                .setStatus("200")
                .setResponse(posList)
                .toJSON();
        return response;
    }

    //Enregistement de POS 
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (data != null) {
            Pos a = new Gson().fromJson(data, new TypeToken<Pos>() {
            }.getType());
            Pos application = this.controller.add(a);
            if (application != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(a)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement de POS")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement POS avec la valeur null")
                    .toJSON();
        }
        return response;
    }

    // activation et désactivation de POS
    @PUT
    public String enableAndDisable(String data) {
        String response = null;
        if (!data.isEmpty()) {
            Pos posData = new Gson().fromJson(data, new TypeToken<Pos>() {
            }.getType());

            Pos pos = this.controller.EnableAndDisable(posData);
            if (pos != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setMessage("Succés")
                        .setResponse(pos)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur avec comme valeur null")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur avec comme valeur null")
                    .toJSON();
        }

        return response;
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        Pos d = this.controller.delete(new Pos(id));
        String response = null;
        if(d!=null) {
            response = HttpResponse.build()
                .setStatus("204")
                .setMessage("supprimé avec Succés")
                .setResponse(d)
                .toJSON();
        }else {
            response = HttpResponse.build()
                .setStatus("400")
                .setMessage("Erreur de suppression")
                .toJSON();
        }
        return response;
    }

}
