package com.creasmit.bipgo.entity;

import java.util.Date;

public class Driver implements java.io.Serializable {

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
    private String adresse;

    public Driver() {
    }

    public Driver(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Integer getFkStructure() {
        return fkStructure;
    }

    public void setFkStructure(Integer fkStructure) {
        this.fkStructure = fkStructure;
    }

    public Integer getFkIdentite() {
        return fkIdentite;
    }

    public Integer getFkVehicule() {
        return fkVehicule;
    }

    public void setFkVehicule(Integer fkVehicule) {
        this.fkVehicule = fkVehicule;
    }

    public void setFkIdentite(Integer fkIdentite) {
        this.fkIdentite = fkIdentite;
    }

    public Integer getFkWallet() {
        return fkWallet;
    }

    public void setFkWallet(Integer fkWallet) {
        this.fkWallet = fkWallet;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
}
