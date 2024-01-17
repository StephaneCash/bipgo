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
import com.creasmit.bipgo.entity.Wallet;

/**
 *
 * @author emmanueltombo
 */
public class StructureAdapter {

    private Structure structure;
    private Gerant gerant;
    private Identite identite;
    private Wallet wallet;
    private ConfirmationCompte confirmationCompte;

    public StructureAdapter() {
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public ConfirmationCompte getConfirmationCompte() {
        return confirmationCompte;
    }

    public void setConfirmationCompte(ConfirmationCompte confirmationCompte) {
        this.confirmationCompte = confirmationCompte;
    }

}
