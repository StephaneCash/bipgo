/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.TypeVehicule;
import com.creasmit.bipgo.entity.Vehicule;

/**
 *
 * @author Christ Mantima
 */
public class VehiculeAdapter {

    private Vehicule vehicule;
    private Structure structure;
    private TypeVehicule typeVehicule;
    private Pos pos;
    private WalletAdapter walletAdapter;

    public VehiculeAdapter() {

    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public Pos getPos() {
        return pos;
    }

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public WalletAdapter getWalletAdapter() {
        return walletAdapter;
    }

    public void setWalletAdapter(WalletAdapter walletAdapter) {
        this.walletAdapter = walletAdapter;
    }

}
