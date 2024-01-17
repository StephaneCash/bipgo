/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.AssistantAdapter;
import com.creasmit.bipgo.dao.IDaoAssistant;
import com.creasmit.bipgo.dao.IDaoIdentite;
import com.creasmit.bipgo.entity.Assistant;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author Christ Mantima
 */
public class AssistantController implements IAssistantController {

    private IDaoAssistant daoAssistant;
    private IDaoIdentite daoIdentite;

    public AssistantController() {
    }

    public void setDaoAssistant(IDaoAssistant daoAssistant) {
        this.daoAssistant = daoAssistant;
    }

    public void setDaoIdentite(IDaoIdentite daoIdentite) {
        this.daoIdentite = daoIdentite;
    }

    @Override
    public List<AssistantAdapter> getAssistantByStructure(Structure structure) {
        return this.daoAssistant.getAssistant(structure);
    }

    @Override
    public AssistantAdapter add(AssistantAdapter assistantAdapter) {

        Assistant assistant = assistantAdapter.getAssistant();
        assistant.setDateCreate(new Date());
        assistant.setFkStructure(assistantAdapter.getStructure().getId());
        assistant.setFkVehicule(assistantAdapter.getVehicule().getId());

        Identite identite = assistantAdapter.getIdentite();

        Identite i = this.daoIdentite.add(identite);
        Log.i(this, new Gson().toJson(i));
        if (i != null) {
            assistant.setFkIdentite(i.getId());
            Assistant assistantSaved = this.daoAssistant.add(assistant);
            if (assistantSaved != null) {
                assistantAdapter.setIdentite(identite);
                assistantAdapter.setAssistant(assistantSaved);
                return assistantAdapter;
            } else {
                this.daoIdentite.delete(i);
                return null;
            }

        }
        return null;
    }

    @Override
    public AssistantAdapter update(AssistantAdapter assistantAdapter) {

        Identite i = this.daoIdentite.update(assistantAdapter.getIdentite());
        if (i != null) {
            Assistant a = assistantAdapter.getAssistant();
            a.setDateModif(new Date());

            Assistant assistantModif = this.daoAssistant.update(a);
            assistantAdapter.setAssistant(assistantModif);
            return assistantAdapter;
        }
        return null;
    }

    @Override
    public AssistantAdapter delete(Assistant assistant) {
        List<AssistantAdapter> assistantAdapters = this.daoAssistant.getAssistant(new QueryParam("a.id", assistant.getId()));

        if (!assistantAdapters.isEmpty()) {
            Identite i = assistantAdapters.get(0).getIdentite();
            Identite identiteDeleted = this.daoIdentite.delete(i);

            if (identiteDeleted != null) {
                Assistant a = this.daoAssistant.delete(assistant);

                AssistantAdapter aa = new AssistantAdapter();
                aa.setAssistant(a);
                aa.setIdentite(identiteDeleted);
                return aa;
            }
        } else {
            return null;
        }

        return null;
    }

}
