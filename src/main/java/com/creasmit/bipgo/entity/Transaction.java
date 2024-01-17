/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.entity;

import java.util.Date;

/**
 *
 * @author georges
 */
public class Transaction {

    private int id;
    private String exp;
    private String ben;
    private Double montant;
    private Date date;
    private Integer fkTypeOperation;
    private Integer fkStatutTransaction;
    private Integer fkDevise;
    private String ref;

    

    public Transaction() {

    }

    public Transaction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getBen() {
        return ben;
    }

    public void setBen(String ben) {
        this.ben = ben;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFkTypeOperation() {
        return fkTypeOperation;
    }

    public void setFkTypeOperation(Integer fkTypeOperation) {
        this.fkTypeOperation = fkTypeOperation;
    }

    public Integer getFkStatutTransaction() {
        return fkStatutTransaction;
    }

    public void setFkStatutTransaction(Integer fkStatutTransaction) {
        this.fkStatutTransaction = fkStatutTransaction;
    }

    public Integer getFkDevise() {
        return fkDevise;
    }

    public void setFkDevise(Integer fkDevise) {
        this.fkDevise = fkDevise;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    
   

}
