package com.creasmit.bipgo.entity;
// Generated Mar 22, 2021 11:34:11 AM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Pos generated by hbm2java
 */
public class Pos implements java.io.Serializable {

    private int id;
    private String sn;
    private Date dateCreate;
    private Integer agentCreate;
    private String code;
    private Integer fkStatutPos;

    public Pos() {
    }

    public Pos(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getAgentCreate() {
        return agentCreate;
    }

    public void setAgentCreate(Integer agentCreate) {
        this.agentCreate = agentCreate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFkStatutPos() {
        return fkStatutPos;
    }

    public void setFkStatutPos(Integer fkStatutPos) {
        this.fkStatutPos = fkStatutPos;
    }
}