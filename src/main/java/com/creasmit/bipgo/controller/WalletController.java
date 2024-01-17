/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.dao.IDaoCompte;
import com.creasmit.bipgo.dao.IDaoWallet;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Wallet;
import com.google.gson.Gson;
import java.util.List;
import one.creas.emalib.util.Log;

/**
 *
 * @author georges
 */
public class WalletController implements IWalletController {

    private IDaoWallet daoWallet;
    private IDaoCompte daoCompte;

    public WalletController() {

    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    @Override
    public List<WalletAdapter> getByBipid(String bipid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WalletAdapter get(String bipid) {
        Wallet wallet = new Wallet();
        wallet.setBipid(bipid);
        try {
            return this.daoWallet.get(wallet);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public WalletAdapter get(Wallet wallet) {
        try {
            WalletAdapter wa = this.daoWallet.get(wallet);
            Log.i(this, new Gson().toJson(wa));
            return wa;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Wallet add(Wallet wallet) {
        return this.daoWallet.add(wallet);
    }

    @Override
    public Wallet update(Wallet wallet) {
        return this.daoWallet.update(wallet);
    }

    @Override
    public WalletAdapter checkMainAccount(Wallet wallet) {
        try {
            Log.i(this, new Gson().toJson(wallet));
            return this.daoWallet.getWalletCompte(wallet);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public WalletAdapter rechargeMainAccount(double montant, WalletAdapter walletAdapter) {

        Compte compte = new Compte();

        double moneyRecharge = montant + walletAdapter.getListeDeComptes().get(0).getCompte().getMontant();
        compte.setMontant(moneyRecharge);
        compte.setId(walletAdapter.getListeDeComptes().get(0).getCompte().getId());
        compte.setFkDevise(1);
        compte.setFkWallet(walletAdapter.getWallet().getId());

        Compte compteRecharged = this.daoCompte.update(compte);
        if (compteRecharged != null) {
            return walletAdapter;
        }
        return null;
    }

}
