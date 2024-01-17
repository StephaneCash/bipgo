/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

/**
 *
 * @author georges
 */
public class Wallet {

    private int id;
    private String bipid;
    private String pin;

    public Wallet() {

    }

    public Wallet(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBipid() {
        return bipid;
    }

    public void setBipid(String bipid) {
        this.bipid = bipid;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
