/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.VehiculeAdapter;
import com.creasmit.bipgo.controller.IPosController;
import com.creasmit.bipgo.controller.IVehiculeController;
import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;
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
 * @author Christ Mantima
 */
@Path("/vehicule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehiculeService {

    private IVehiculeController controller;
    private IPosController iPosController;

    public VehiculeService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IVehiculeController) context.getBean("vehiculeController");
        this.iPosController = (IPosController) context.getBean("posController");
    }

    //affichage de vehicule par structure
    @GET
    @Path("/{structureId}")
    public String getListByStructure(@PathParam("structureId") int structureId) {
        Log.i(this, "" + structureId);
        List<VehiculeAdapter> vehiculeList = this.controller.getVehiculeByStructure(new Structure(structureId));
        String response = HttpResponse.build()
                .setStatus("200")
                .setResponse(vehiculeList)
                .toJSON();
        Log.i(this, response);
        return response;
    }

    //affichage de vehicule par structure
    @POST
    @Path("/activeCompte")
    public String activeCompte(String data) {
        Log.i(this, "" + data);
        Vehicule vehicule = new Gson().fromJson(data, new TypeToken<Vehicule>() {
        }.getType());
        
        VehiculeAdapter vehiculeAdapter=this.controller.activeCompte(vehicule);
        if(vehiculeAdapter!=null){
            return HttpResponse.build()
                    .setStatus("200")
                    .setResponse(vehiculeAdapter)
                    .toJSON();
        }else{
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Code d'activation invalide")
                    .toJSON();
        }

    }

    // creation vehicule
    @POST
    public String add(String data) {
        Log.i(this, data);
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {

            VehiculeAdapter vehiculeAdapter = new Gson().fromJson(data, new TypeToken<VehiculeAdapter>() {
            }.getType());

            Vehicule v = vehiculeAdapter.getVehicule();
            Pos pos = vehiculeAdapter.getPos();

            Pos posChecked = this.iPosController.getByCode(pos.getCode());
            Log.i(this, new Gson().toJson(posChecked));

            if (posChecked != null) {
                //Check whether id pos is already attributed by a vehicule
                VehiculeAdapter va = this.controller.getVehiculeByIdPos(posChecked);
                Log.i(this, "check vehicule posid");
                Log.i(this, new Gson().toJson(va));

                if (va == null) {
                    v.setFkPos(posChecked.getId());
                    Vehicule vehicule = this.controller.add(v);
                    if (vehicule != null) {
                        response = HttpResponse.build()
                                .setStatus("200")
                                .setResponse(vehicule)
                                .toJSON();
                    } else {
                        response = HttpResponse.build()
                                .setStatus("400")
                                .setMessage("Erreur d'enregistrement du vehicule")
                                .toJSON();
                    }
                } else {
                    response = HttpResponse.build()
                            .setStatus("400")
                            .setMessage("Pos N°" + posChecked.getCode() + " déjà attribué")
                            .toJSON();
                }

            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("POS non trouvé")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement du vehicule")
                    .toJSON();
        }
        return response;
    }

    @PUT
    public String update(String data) {
        Log.i(this, data);
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {

            VehiculeAdapter vehiculeAdapter = new Gson().fromJson(data, new TypeToken<VehiculeAdapter>() {
            }.getType());

            Vehicule v = vehiculeAdapter.getVehicule();
            Pos pos = vehiculeAdapter.getPos();

            Pos posChecked = this.iPosController.getByCode(pos.getCode());
            Log.i(this, new Gson().toJson(posChecked));

            if (posChecked != null) {
                if (posChecked.getId() != pos.getId()) {
                    Log.i(this, "POSCODE différent");
                    //Check whether id pos is already attributed by a vehicule
                    VehiculeAdapter va = this.controller.getVehiculeByIdPos(posChecked);
                    Log.i(this, "check vehicule posid");
                    Log.i(this, new Gson().toJson(va));

                    if (va == null) {
                        v.setFkPos(posChecked.getId());
                        v.setFkTypeVehicule(vehiculeAdapter.getTypeVehicule().getId());
                        Vehicule vehicule = this.controller.update(v);
                        if (vehicule != null) {
                            response = HttpResponse.build()
                                    .setStatus("200")
                                    .setResponse(vehicule)
                                    .toJSON();
                        } else {
                            response = HttpResponse.build()
                                    .setStatus("400")
                                    .setMessage("Erreur de modification")
                                    .toJSON();
                        }
                    } else {
                        response = HttpResponse.build()
                                .setStatus("400")
                                .setMessage("Pos N°" + posChecked.getCode() + " déjà attribué")
                                .toJSON();
                    }
                } else {
                    Log.i(this, "POSCODE identique");
                    v.setFkTypeVehicule(vehiculeAdapter.getTypeVehicule().getId());
                    Vehicule vehicule = this.controller.update(v);
                    if (vehicule != null) {
                        response = HttpResponse.build()
                                .setStatus("200")
                                .setResponse(vehicule)
                                .toJSON();
                    } else {
                        response = HttpResponse.build()
                                .setStatus("400")
                                .setMessage("Erreur de modification")
                                .toJSON();
                    }
                }

            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("POS non trouvé")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de modification du vehicule")
                    .toJSON();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {
        Log.i(this, "delete id=" + id);
        Vehicule vehicule = this.controller.delete(new Vehicule(id));

        if (vehicule != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setMessage("")
                    .setResponse(vehicule)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de suppression")
                    .toJSON();
        }

    }
}
