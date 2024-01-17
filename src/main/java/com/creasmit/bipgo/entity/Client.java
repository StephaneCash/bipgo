package com.creasmit.bipgo.entity;

import java.util.Date;

/**
 *
 * @author Stéphane
 */
public class Client implements java.io.Serializable {

    private int id;
    private Date dateCreate;
    private Date dateModif;
    private Integer agentCreate;
    private Integer agentModif;
    private Integer fkIdentite;
    private Integer fkWallet;

    public Client() {
    }

    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getFkIdentite() {
        return fkIdentite;
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

}
