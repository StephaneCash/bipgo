/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Wallet;
import java.util.List;

/**
 *
 * @author georges
 */
public class WalletAdapter {
    private Wallet wallet;
    private List<CompteAdapter> ListeDeComptes;
    
    public WalletAdapter(){
        
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<CompteAdapter> getListeDeComptes() {
        return ListeDeComptes;
    }

    public void setListeDeComptes(List<CompteAdapter> ListeDeComptes) {
        this.ListeDeComptes = ListeDeComptes;
    }
    
    
}
