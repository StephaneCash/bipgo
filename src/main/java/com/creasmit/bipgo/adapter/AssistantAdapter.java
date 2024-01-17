/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Assistant;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;

/**
 *
 * @author Christ Mantima
 */
public class AssistantAdapter {

    private Assistant assistant;
    private Identite identite;
    private Structure structure;
    private Vehicule vehicule;
    private ConfirmationCompte confirmationCompte;

    public AssistantAdapter() {

    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public ConfirmationCompte getConfirmationCompte() {
        return confirmationCompte;
    }

    public void setConfirmationCompte(ConfirmationCompte confirmationCompte) {
        this.confirmationCompte = confirmationCompte;
    }

}
