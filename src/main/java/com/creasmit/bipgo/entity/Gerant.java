package com.creasmit.bipgo.entity;
// Generated Mar 22, 2021 11:34:11 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Gerant generated by hbm2java
 */
public class Gerant implements java.io.Serializable {

    private int id;
    private Integer fkIdentite;
    private Integer fkStructure;
    private Integer fkProfile;
    private Date dateCreate;
    private Date dateModif;
    private Integer agentCreate;
    private Integer agentModif;

    public Gerant() {
    }

    public Gerant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFkIdentite() {
        return fkIdentite;
    }

    public void setFkIdentite(Integer fkIdentite) {
        this.fkIdentite = fkIdentite;
    }

    public Integer getFkStructure() {
        return fkStructure;
    }

    public void setFkStructure(Integer fkStructure) {
        this.fkStructure = fkStructure;
    }

    public Integer getFkProfile() {
        return fkProfile;
    }

    public void setFkProfile(Integer fkProfile) {
        this.fkProfile = fkProfile;
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

}
