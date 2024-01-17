/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.LineAdapter;
import com.creasmit.bipgo.controller.ILineController;
import com.creasmit.bipgo.entity.TypeVehicule;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import one.creas.emalib.http.HttpResponse;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author emmanueltombo
 */
@Path("/line")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LineService {

    private ILineController controller;

    public LineService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (ILineController) context.getBean("lineController");
    }

    @GET
    @Path("/{typeVehiculeId}")
    public String getLines(@PathParam("typeVehiculeId") int typeVehiculeId) {
        List<LineAdapter> lineAdapters = this.controller.list(new TypeVehicule(typeVehiculeId));
        return HttpResponse.build()
                .setStatus("200")
                .setResponse(lineAdapters)
                .toJSON();
    }

}
