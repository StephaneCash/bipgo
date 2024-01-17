
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.StructureAdapter;
import com.creasmit.bipgo.controller.IStructureController;
import com.creasmit.bipgo.entity.Structure;
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
 * @author georges
 */
@Path("/structure")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StructureService {

    private IStructureController controller;

    public StructureService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (IStructureController) context.getBean("structureController");
    }

    // read structure  
    @GET
    public String getList() {
        List<StructureAdapter> structure = this.controller.listing();

        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(structure)
                .toJSON();
        return reponse;
    }

    @GET
    @Path("/{denomination}")
    public String getListById(@PathParam("denomination") String denomination) {
        List<StructureAdapter> structure = this.controller.listing(denomination);
        String reponse = new Gson().toJson(structure);
        return reponse;
    }

    @GET
    @Path("/byId/{id}")
    public String getListById(@PathParam("id") int id) {
        Log.i(this,"ID: "+ id);
        List<StructureAdapter> structure = this.controller.getStructure(id);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(structure)
                .toJSON();
        return reponse;
    }

    // create structure
    @POST
    public String add(String data) {
        String response = null;
        Log.i(this, data);
        if (data != null) {
            Structure s = new Gson().fromJson(data, new TypeToken<Structure>() {
            }.getType());
            Structure structure = this.controller.add(s);
            if (structure != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setMessage("Succès")
                        .setResponse(s)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement de Structure")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement Structure avec la valeur null")
                    .toJSON();
        }
        return response;
    }

    // update Structure
    @PUT
    public String update(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            Structure s = new Gson().fromJson(data, new TypeToken<Structure>() {
            }.getType());
            Structure structure = this.controller.update(s);
            if (structure != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(s)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur d'enregistrement de Structure")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur d'enregistrement Structure avec la valeur null")
                    .toJSON();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) {

        Structure s = new Structure(id);
        Structure structure = this.controller.delete(s);
        if (structure != null) {
            return HttpResponse.build()
                    .setStatus("200")
                    .setResponse(s)
                    .toJSON();
        } else {
            return HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de suppression de la Structure")
                    .toJSON();
        }

    }

}
