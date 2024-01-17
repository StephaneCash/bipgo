/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

import java.util.Date;

/**
 *
 * @author georges
 */
public class Compte implements java.io.Serializable{
    
    private int id;
    private Double montant;
    private Integer fkDevise;
    private Integer fkWallet;

    public Compte(){
        
    }
    public Compte(int id) {
         this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getFkDevise() {
        return fkDevise;
    }

    public void setFkDevise(Integer fkDevise) {
        this.fkDevise = fkDevise;
    }

    public Integer getFkWallet() {
        return fkWallet;
    }

    public void setFkWallet(Integer fkWallet) {
        this.fkWallet = fkWallet;
    }
    
}
