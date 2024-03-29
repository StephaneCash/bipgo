package com.creasmit.bipgo.entity;
// Generated Mar 22, 2021 11:34:11 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Structure generated by hbm2java
 */
public class Structure implements java.io.Serializable {

    private int id;
    private String type;
    private String denomination;
    private Date dateCreate;
    private Date dateModif;
    private Integer agentCreate;
    private Integer agentModif;
    private Integer fkWallet;

    public Structure() {
    }

    public Structure(int id) {
        this.id = id;
    }

    public Structure(int id, String type, String denomination) {
        this.id = id;
        this.type = type;
        this.denomination = denomination;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDenomination() {
        return this.denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public Integer getAgentCreate() {
        return agentCreate;
    }

    public void setAgentCreate(Integer agentCreate) {
        this.agentCreate = agentCreate;
    }

    public Integer getAgentModif() {
        return agentModif;
    }

    public void setAgentModif(Integer agentModif) {
        this.agentModif = agentModif;
    }

    public Integer getFkWallet() {
        return fkWallet;
    }

    public void setFkWallet(Integer fkWallet) {
        this.fkWallet = fkWallet;
    }

}
