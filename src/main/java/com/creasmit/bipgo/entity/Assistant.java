/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

import java.util.Date;

/**
 *
 * @author Christ Mantima
 */
public class Assistant implements java.io.Serializable {
    private int id;
     private String modePaiement;
    private Integer fkStructure;
    private Integer fkIdentite;
    private Integer fkVehicule;
     private Integer fkWallet;
    private Integer agentCreate;
    private Date dateCreate;
    private Integer agentModif;
    private Date dateModif;
    
    public Assistant(){
        
    }
    
    public Assistant(int id) {
        this.id=id;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }
    

    public Integer getAgentCreate() {
        return agentCreate;
    }

    public void setAgentCreate(Integer agentCreate) {
        this.agentCreate = agentCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getAgentModif() {
        return agentModif;
    }

    public void setAgentModif(Integer agentModif) {
        this.agentModif = agentModif;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }
    
    
    public Integer getFkStructure() {
        return fkStructure;
    }
    
    public void setFkStructure(Integer fkStructure){
        this.fkStructure=fkStructure;
    }
    
    public Integer getFkIdentite() {
        return fkIdentite;
    }
    
    public void setFkIdentite(Integer fkIdentite){
        this.fkIdentite=fkIdentite;
    }

    public Integer getFkVehicule() {
        return fkVehicule;
    }

    public void setFkVehicule(Integer fkVehicule) {
        this.fkVehicule = fkVehicule;
    }

    public Integer getFkWallet() {
        return fkWallet;
    }

    public void setFkWallet(Integer fkWallet) {
        this.fkWallet = fkWallet;
    }
    
    
    
}
