/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.controller.ITypeVehiculeController;
import com.creasmit.bipgo.entity.TypeVehicule;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import one.creas.emalib.http.HttpResponse;
import one.creas.emalib.util.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author georges
 */

@Path("/typeVehicule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TypeVehiculeService {
    
    private ITypeVehiculeController controller;

    public TypeVehiculeService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (ITypeVehiculeController) context.getBean("typeVehiculeController");
    }
    
    
    
    // read for TypeVehicule
    @GET
    public String getList() {
        List<TypeVehicule> applications = this.controller.listing();
        Log.i(this, new Gson().toJson(applications));

        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(applications)
                .toJSON();
        return reponse;
    }

    
}
