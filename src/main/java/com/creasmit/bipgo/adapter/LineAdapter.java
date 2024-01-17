/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Line;
import com.creasmit.bipgo.entity.Prix;
import com.creasmit.bipgo.entity.TypeVehicule;

/**
 *
 * @author emmanueltombo
 */
public class LineAdapter {

    private Line line;
    private TypeVehicule typeVehicule;
    private Prix prix;

    public LineAdapter() {
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }
    
    
}
