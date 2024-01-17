package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.DriverAdapter;
import com.creasmit.bipgo.controller.ConfirmationCompteController;
import com.creasmit.bipgo.controller.IConfirmationCompteController;
import com.creasmit.bipgo.controller.IDriverController;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Driver;
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

@Path("/driver")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DriverService {

    private IDriverController controller;
    private IConfirmationCompteController confirmationCompteController;

    public DriverService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IDriverController) context.getBean("driverController");
        this.confirmationCompteController=(ConfirmationCompteController)context.getBean("confirmationCompteController");
    }

    //affichage de liste de Drivers par structure
    @GET
    @Path("/{structure}")
    public String get(@PathParam("structure") int structureId) {
        Log.i(this, "fetching...");
        List<DriverAdapter> driverList = this.controller.getDriverByStructure(new Structure(structureId));
        String response = HttpResponse.build()
                .setStatus("200")
                .setResponse(driverList)
                .toJSON();
        return response;
    }

    // Création du conducteur(Driver)
    @POST
    public String add(String data) {
        Log.i(this, data);
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            DriverAdapter d = new Gson().fromJson(data, new TypeToken<DriverAdapter>() {
            }.getType());
            DriverAdapter driverAdapter = this.controller.add(d);
            if (driverAdapter != null) {
                try {
                    ConfirmationCompte cc = new ConfirmationCompte();
                    cc.setFkIdentite(d.getIdentite().getId());
                    cc.setDateCreate(new Date());
                    cc.setEmail(d.getIdentite().getEmail());
                    cc.setPhone(d.getIdentite().getNumTel());
                    cc.setPin(d.getDriver().getId() + Utils.generatePin(9));
                    this.confirmationCompteController.init(cc);
                } catch (Exception e) {
                    Log.i(this, e);
                }
                response = HttpResponse.build()
                        .setStatus("200")
                        .setMessage("Driver enregistré avec succès")
                        .setResponse(driverAdapter)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement du driver")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement du driver")
                    .toJSON();
        }
        return response;
    }

    //update Driver
    @PUT
    public String update(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            DriverAdapter d = new Gson().fromJson(data, new TypeToken<DriverAdapter>() {
            }.getType());
            DriverAdapter driverUpdated = this.controller.update(d);
            if (driverUpdated != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(d)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur de modification du driver")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de modification du driver avec la valeur null")
                    .toJSON();
        }
        return response;

    }

    //delete Driver
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        String response = null;
        DriverAdapter driver = this.controller.delete(new Driver(id));

        if (driver != null) {
            response = HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(driver)
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
