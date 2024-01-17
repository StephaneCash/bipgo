/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.entity.Wallet;

/**
 *
 * @author georges
 */
public interface IDaoWallet {

    public WalletAdapter get(Wallet wallet);
    
    public WalletAdapter get(int id);

    public WalletAdapter getWalletCompte(Wallet wallet);

    public Wallet add(Wallet wallet);

    public Wallet update(Wallet wallet);

    public Wallet delete(Wallet wallet);

}
