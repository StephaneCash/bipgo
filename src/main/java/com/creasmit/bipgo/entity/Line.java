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
 * @author emmanueltombo
 */
public class Line implements Serializable{

    private int id;
    private String description;
    private Date dateCreate;
    private Date dateModif;
    private Integer agentCreate;
    private Integer agentModif;
    

    public Line() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
