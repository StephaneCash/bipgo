/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.controller.IWalletController;
import com.creasmit.bipgo.entity.Wallet;
import com.creasmit.bipgo.model.WalletChangePin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import one.creas.emalib.http.HttpResponse;
import one.creas.emalib.util.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author emmanueltombo
 */
@Path("/wallet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletService {

    IWalletController controller;

    public WalletService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IWalletController) context.getBean("walletController");
    }

    @PUT
    @Path("/changePin")
    public String changePin(String data) {
        Log.i(this, data);
        WalletChangePin changePin = new Gson().fromJson(data, new TypeToken<WalletChangePin>() {
        }.getType());

        if (!data.isEmpty()) {

            WalletAdapter walletAdapter = this.controller.get(changePin.getOldWallet());
            Log.i(this, new Gson().toJson(walletAdapter));

            if (walletAdapter != null) {
                Wallet walletUpdated = this.controller.update(changePin.getNewWallet());
                if (walletUpdated != null) {
                    return HttpResponse.build()
                            .setStatus("200")
                            .setMessage("Pin modifié")
                            .toJSON();
                } else {
                    return HttpResponse.build()
                            .setStatus("400")
                            .setMessage("Echec de modification de pin")
                            .toJSON();
                }
            } else {
                return HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Ancien pin incorrect")
                        .toJSON();
            }
        }
        return HttpResponse.build()
                .setStatus("400")
                .setMessage("Aucune information trouvée")
                .toJSON();
    }

    @POST
    public String getWalletInfos(String data) {
        Log.i(this, data);
        Wallet wallet = new Gson().fromJson(data, new TypeToken<Wallet>() {
        }.getType());

        WalletAdapter walletAdapter = this.controller.get(wallet);
        if (walletAdapter != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setResponse(walletAdapter)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur")
                    .toJSON();
        }

    }

    @POST
    @Path("/mainRecharge")
    public String rechargeMainAccount(String data) {
        Log.i(this, data);
        WalletAdapter wa = new Gson().fromJson(data, new TypeToken<WalletAdapter>() {
        }.getType());
        Log.i(this, new Gson().toJson(wa));

        WalletAdapter waChecked = this.controller.checkMainAccount(wa.getWallet());

        if (waChecked != null) {
            WalletAdapter wa1 = this.controller.rechargeMainAccount(wa.getListeDeComptes().get(0).getCompte().getMontant(), waChecked);
            if (wa1 != null) {
                return HttpResponse.build()
                        .setStatus("200")
                        .setMessage("Succes")
                        .setResponse(wa1)
                        .toJSON();
            }
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Echec d'opération")
                    .toJSON();

        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Pin invalide")
                    .toJSON();
        }

    }
}
