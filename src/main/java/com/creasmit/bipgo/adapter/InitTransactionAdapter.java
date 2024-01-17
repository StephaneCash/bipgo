/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Transaction;
import com.creasmit.bipgo.entity.Wallet;

/**
 *
 * @author emmanueltombo
 */
public class InitTransactionAdapter {

    private Transaction transaction;
    private Wallet wallet;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

}
