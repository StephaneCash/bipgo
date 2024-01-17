/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

/**
 *
 * @author georges
 */
public class TypeVehiculeLinePrix {
    
    
   
    private Integer fkTypeVehicule;
    private Integer fkLine;
    private Integer fkPrix;

   

    public Integer getFkTypeVehicule() {
        return fkTypeVehicule;
    }

    public void setFkTypeVehicule(Integer fkTypeVehicule) {
        this.fkTypeVehicule = fkTypeVehicule;
    }

    public Integer getFkLine() {
        return fkLine;
    }

    public void setFkLine(Integer fkLine) {
        this.fkLine = fkLine;
    }

    public Integer getFkPrix() {
        return fkPrix;
    }

    public void setFkPrix(Integer fkPrix) {
        this.fkPrix = fkPrix;
    }
    
    
 
}