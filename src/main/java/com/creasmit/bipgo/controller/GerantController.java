/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.GerantAdapter;
import com.creasmit.bipgo.dao.IDaoGerant;
import com.creasmit.bipgo.dao.IDaoIdentite;
import com.creasmit.bipgo.entity.Gerant;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class GerantController implements IGerantController {

    private IDaoGerant daoGerant;
    private IDaoIdentite daoIdentite;

    public GerantController() {
    }

    public void setDaoGerant(IDaoGerant daoGerant) {
        this.daoGerant = daoGerant;
    }

    public void setDaoIdentite(IDaoIdentite daoIdentite) {
        this.daoIdentite = daoIdentite;
    }

    public GerantController(IDaoGerant daoGerant, IDaoIdentite daoIdentite) {
        this.daoGerant = daoGerant;
        this.daoIdentite = daoIdentite;
    }

    @Override
    public GerantAdapter get(Structure structure) {
        Log.i(this, "structureId:::" + structure.getId());
        List<GerantAdapter> gerantAdapters = this.daoGerant.getGerants(structure);
        if (!gerantAdapters.isEmpty()) {
            return gerantAdapters.get(0);
        }
        return null;
    }

    @Override
    public GerantAdapter add(GerantAdapter gerantAdapter) {

        Gerant gerant = gerantAdapter.getGerant();
        Identite identite = gerantAdapter.getIdentite();

        Identite identiteSaved = this.daoIdentite.add(identite);
        if (identiteSaved != null) {
            gerant.setFkIdentite(identiteSaved.getId());
            gerant.setDateCreate(new Date());

            Gerant gerantSaved = this.daoGerant.add(gerant);
            if (gerantSaved != null) {
                gerantAdapter.setGerant(gerantSaved);
                gerantAdapter.setIdentite(identiteSaved);
                
                
                
                return gerantAdapter;
            } else {
                this.daoIdentite.delete(identiteSaved);
                return null;
            }
        }

        return null;
    }

    @Override
    public GerantAdapter update(GerantAdapter gerantAdapter) {

        Identite identite = gerantAdapter.getIdentite();
  
        Identite identiteSaved = this.daoIdentite.update(identite);
        Log.i(this, new Gson().toJson(identiteSaved));

        Gerant gerant = gerantAdapter.getGerant();
        if (identiteSaved != null) {
            gerant.setDateModif(new Date());
            Gerant gerantModif = this.daoGerant.update(gerant);
            
            Log.i(this, new Gson().toJson(gerantModif));

            gerantAdapter.setIdentite(identiteSaved);
            gerantAdapter.setGerant(gerantModif);
            return gerantAdapter;
        }
        return null;
    }

    @Override
    public GerantAdapter delete(Gerant gerant) {

        List<GerantAdapter> aa = this.daoGerant.getGerants(new QueryParam("g.id", gerant.getId()));

        if (!aa.isEmpty()) {
            Identite idGerant = aa.get(0).getIdentite();

            Identite identiteDeleted = this.daoIdentite.delete(idGerant);
            if (identiteDeleted != null) {
                this.daoGerant.delete(gerant);

                GerantAdapter a = new GerantAdapter();
                a.setIdentite(identiteDeleted);
                return a;
            }
        } else {
            Log.i(this, "Aucun gerant trouvé");
        }

        return null;
    }

}
