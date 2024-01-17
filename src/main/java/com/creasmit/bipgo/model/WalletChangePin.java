/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.model;

import com.creasmit.bipgo.entity.Wallet;

/**
 *
 * @author emmanueltombo
 */
public class WalletChangePin {
    private Wallet oldWallet;
    private Wallet newWallet;

    public Wallet getOldWallet() {
        return oldWallet;
    }

    public void setOldWallet(Wallet oldWallet) {
        this.oldWallet = oldWallet;
    }

    public Wallet getNewWallet() {
        return newWallet;
    }

    public void setNewWallet(Wallet newWallet) {
        this.newWallet = newWallet;
    }
   
}
