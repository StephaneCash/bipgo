/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Devise;
import com.creasmit.bipgo.entity.StatutTransaction;
import com.creasmit.bipgo.entity.Transaction;
import com.creasmit.bipgo.entity.TypeOperation;

/**
 *
 * @author georges
 */
public class TransactionAdapter {

    private Transaction transaction;
    private TypeOperation typeOperation;
    private StatutTransaction statutTransaction;
    private Devise devise;

    public TransactionAdapter() {

    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public StatutTransaction getStatutTransaction() {
        return statutTransaction;
    }

    public void setStatutTransaction(StatutTransaction statutTransaction) {
        this.statutTransaction = statutTransaction;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

}
