/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Wallet;
import java.util.List;

/**
 *
 * @author georges
 */
public interface IWalletController {

    public List<WalletAdapter> getByBipid(String bipid);

    public WalletAdapter get(String bipid);

    public WalletAdapter get(Wallet wallet);

    public Wallet add(Wallet wallet);

    public Wallet update(Wallet wallet);

    public WalletAdapter checkMainAccount(Wallet wallet);

    public WalletAdapter rechargeMainAccount(double montant, WalletAdapter walletAdapter);

}
