/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Gerant;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.model.Profil;

/**
 *
 * @author emmanueltombo
 */
public class GerantAdapter {

    private Gerant gerant;
    private Identite identite;
    private Structure structure;
    private Profil profil;
    private ConfirmationCompte confirmationCompte;

    public GerantAdapter() {
    }

    public Gerant getGerant() {
        return gerant;
    }

    public void setGerant(Gerant gerant) {
        this.gerant = gerant;
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

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public ConfirmationCompte getConfirmationCompte() {
        return confirmationCompte;
    }

    public void setConfirmationCompte(ConfirmationCompte confirmationCompte) {
        this.confirmationCompte = confirmationCompte;
    }
}
