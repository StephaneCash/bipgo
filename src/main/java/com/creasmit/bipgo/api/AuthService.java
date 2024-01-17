/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.controller.IAuthController;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.model.Profil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import one.creas.emalib.http.HttpResponse;
import one.creas.emalib.util.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author emmanueltombo
 */
@Path("/auth")
public class AuthService {

    private IAuthController controller;

    public AuthService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IAuthController) context.getBean("authController");
    }

    @GET
    public String get() {
        return "";
    }

    @POST
    @Path("/signup")
    public String signup(String data) {
        Profil profil = new Gson().fromJson(data, new TypeToken<Profil>() {
        }.getType());
        
        HttpResponse<Profil> p = this.controller.signup(profil);
        if (p != null) {
            return new Gson().toJson(p);
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Echec de connexion au service d'authentification")
                    .toJSON();
        }
    }

    @POST
    @Path("/confirm_signup/{pin}")
    public String confirm_signup(String data, @PathParam("pin") String pin) {
        Log.i(this, data);
        Log.i(this, pin);

        Profil profil = new Gson().fromJson(data, new TypeToken<Profil>() {
        }.getType());

        HttpResponse<ConfirmationCompte> compteConfirm = this.controller.confirm_signup(pin);
        if (compteConfirm != null) {
            Log.i(this, new Gson().toJson(compteConfirm));
            return new Gson().toJson(compteConfirm);
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Pin non valide")
                    .toJSON();
        }
    }

    @POST
    @Path("/login")
    public String login(String data) {
        Log.i(this, data);
        Profil profil = new Gson().fromJson(data, new TypeToken<Profil>() {
        }.getType());
        HttpResponse<Profil> p = this.controller.login(profil);
        if (p != null) {
            return new Gson().toJson(p);
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Echec de connexion au service d'authentification")
                    .toJSON();
        }
    }
}
