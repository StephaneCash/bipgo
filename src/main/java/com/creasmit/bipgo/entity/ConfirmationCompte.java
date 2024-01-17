/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

import java.util.Date;

/**
 *
 * @author emmanueltombo
 */
public class ConfirmationCompte {

    private int id;
    private String pin;
    private String phone;
    private String email;
    private Date dateCreate;
    private Integer fkIdentite;

    public ConfirmationCompte() {
    }

    public ConfirmationCompte(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getFkIdentite() {
        return fkIdentite;
    }

    public void setFkIdentite(Integer fkIdentite) {
        this.fkIdentite = fkIdentite;
    }

}
