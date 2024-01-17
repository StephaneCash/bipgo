/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Christ Mantima
 */
public class Vehicule implements Serializable {
    
    private int id;
    private Integer fkTypeVehicule;
    private Integer fkStructure;
    private Integer fkPos;
    private String numero_plaque;
    private String modele;
    private String marque;
    private String carte_rose;
    private Integer agentCreate;
    private Date dateCreate;
    private Integer agentModif;
    private Date dateModif;
    private String codeActivationTerminal;
    private Integer fkWallet;

    
    public Vehicule(){
        
    }

    public Vehicule(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFkTypeVehicule() {
        return fkTypeVehicule;
    }

    public void setFkTypeVehicule(Integer fkTypeVehicule) {
        this.fkTypeVehicule = fkTypeVehicule;
    }

    public Integer getFkStructure() {
        return fkStructure;
    }

    public void setFkStructure(Integer fkStructure) {
        this.fkStructure = fkStructure;
    }

    public Integer getFkPos() {
        return fkPos;
    }

    public void setFkPos(Integer fkPos) {
        this.fkPos = fkPos;
    }

    public String getNumero_plaque() {
        return numero_plaque;
    }

    public void setNumero_plaque(String numero_plaque) {
        this.numero_plaque = numero_plaque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCarte_rose() {
        return carte_rose;
    }

    public void setCarte_rose(String carte_rose) {
        this.carte_rose = carte_rose;
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

    public String getCodeActivationTerminal() {
        return codeActivationTerminal;
    }

    public void setCodeActivationTerminal(String codeActivationTerminal) {
        this.codeActivationTerminal = codeActivationTerminal;
    }

    public Integer getFkWallet() {
        return fkWallet;
    }

    public void setFkWallet(Integer fkWallet) {
        this.fkWallet = fkWallet;
    }
    
}
