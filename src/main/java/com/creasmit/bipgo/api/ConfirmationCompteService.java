/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.controller.IConfirmationCompteController;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.Consumes;
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
 * @author emmanueltombo
 */
@Path("/confirmationCompte")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConfirmationCompteService {

    private IConfirmationCompteController controller;

    public ConfirmationCompteService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IConfirmationCompteController) context.getBean("confirmationCompteController");
    }

    @GET
    @Path("/{pin}")
    public String confirmCompte(@PathParam("pin") String pin) {
        Log.i("Pin", pin);
        ConfirmationCompte cc = new ConfirmationCompte();
        cc.setPin(pin);

        ConfirmationCompte compteConfirm = this.controller.confirm(cc);
        if (compteConfirm != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setResponse(compteConfirm)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Pin non valide")
                    .toJSON();
        }
    }

    @GET
    @Path("/resend/{phone}")
    public String resendPin(@PathParam("phone") String phone) {
        ConfirmationCompte compteConfirm = this.controller.resend(phone);
        if (compteConfirm != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setResponse(compteConfirm)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Pin non valide")
                    .toJSON();
        }
    }

    @POST
    public String init(String data) {
        ConfirmationCompte cc = new Gson().fromJson(data, new TypeToken<ConfirmationCompte>() {
        }.getType());

        ConfirmationCompte compteConfirm = this.controller.init(cc);
        if (compteConfirm != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setResponse(compteConfirm)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Pin non valide")
                    .toJSON();
        }
    }
    

}
