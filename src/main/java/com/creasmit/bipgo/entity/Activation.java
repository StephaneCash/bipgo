/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

/**
 *
 * @author Christ Mantima
 */
public class Activation implements java.io.Serializable {
    
    private int id;
    private String type;
    private int entiteid;
    private String codeconfirmation;
    private int statut;
    private String email;
    private String numerophone;

    public Activation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEntiteid() {
        return entiteid;
    }

    public void setEntiteid(int entiteid) {
        this.entiteid = entiteid;
    }

    public String getCodeconfirmation() {
        return codeconfirmation;
    }

    public void setCodeconfirmation(String codeconfirmation) {
        this.codeconfirmation = codeconfirmation;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerophone() {
        return numerophone;
    }

    public void setNumerophone(String numerophone) {
        this.numerophone = numerophone;
    }
    
    
    
    
}
