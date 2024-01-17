/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.ClientAdapter;
import com.creasmit.bipgo.adapter.CompteAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.entity.Client;
import com.creasmit.bipgo.entity.Identite;
import java.util.List;
import com.creasmit.bipgo.dao.IDaoClient;
import com.creasmit.bipgo.dao.IDaoIdentite;
import com.creasmit.bipgo.dao.IDaoCompte;
import com.creasmit.bipgo.dao.IDaoWallet;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Wallet;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;
import one.creas.emalib.util.Utils;
import one.creas.emalib.util.ValueValidateException;

/**
 *
 * @author CREASMIT_ZEUS
 */
public class ClientController implements IClientController {

    private IDaoClient daoClient;
    private IDaoIdentite daoIdentite;
    private IDaoWallet daoWallet;
    private IDaoCompte daoCompte;

    public ClientController() {
    }

    public void setDaoClient(IDaoClient daoClient) {
        this.daoClient = daoClient;
    }

    public void setDaoIdentite(IDaoIdentite daoIdentite) {
        this.daoIdentite = daoIdentite;
    }

    public IDaoCompte getDaoCompte() {
        return daoCompte;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }

    @Override
    public List<ClientAdapter> listClient() {
        return this.daoClient.getClients();
    }

    @Override
    public ClientAdapter getClient() {
        List<ClientAdapter> clientAdapters = this.daoClient.getClients(new QueryParam());
        if (!clientAdapters.isEmpty()) {
            return clientAdapters.get(0);
        }
        return null;
    }

    @Override
    public ClientAdapter add(ClientAdapter clientAdapter) {

        Client client = clientAdapter.getClient();
        Identite identite = clientAdapter.getIdentite();

        Identite identiteSaved = this.daoIdentite.add(identite);
        Log.i(this, new Gson().toJson(identiteSaved));

        if (identiteSaved != null) {
            client.setFkIdentite(identiteSaved.getId());
            client.setDateCreate(new Date());

            Client clientSaved = this.daoClient.add(client);
            if (clientSaved != null) {
                clientAdapter.setClient(clientSaved);
                clientAdapter.setIdentite(identiteSaved);

                Wallet wallet = new Wallet();
                try {
                    wallet.setBipid(9 + Utils.generatePin(6));
                } catch (ValueValidateException ex) {
                }
                try {
                    wallet.setPin(Utils.generatePin(4));
                } catch (Exception e) {
                    wallet.setPin("1234");
                }

                Wallet walletSaved = this.daoWallet.add(wallet);
                if (walletSaved != null) {
                    
                    Compte compte = new Compte();
                    compte.setMontant(0.0);
                    compte.setFkDevise(1);
                    compte.setFkWallet(walletSaved.getId());
                    Compte compteSaved = this.daoCompte.add(compte);
                    
                    if (compteSaved != null) {
                        WalletAdapter wa=new WalletAdapter();
                        wa.setWallet(wallet);
                        
                        CompteAdapter ca=new CompteAdapter();
                        ca.setCompte(compte);
                        
                        List<CompteAdapter> comptes=new ArrayList<>();
                        comptes.add(ca);
                        wa.setListeDeComptes(comptes);
                        clientAdapter.setWalletAdapter(wa);
                        
                    }
                    clientSaved.setFkWallet(walletSaved.getId());
                    this.daoClient.update(clientSaved);
                }

                return clientAdapter;
            } else {
                Log.i(this, "Not saved");
                this.daoIdentite.delete(identiteSaved);
            }
        }

        return null;
    }

    @Override
    public ClientAdapter update(ClientAdapter clientAdapter) {

        Identite identite = clientAdapter.getIdentite();
        Identite identiteSaved = this.daoIdentite.update(identite);

        Client client = clientAdapter.getClient();
        if (identiteSaved != null) {
            client.setDateModif(new Date());
            Client agentModif = this.daoClient.update(client);

            clientAdapter.setIdentite(identiteSaved);
            clientAdapter.setClient(agentModif);
            return clientAdapter;
        }
        return null;
    }

    @Override
    public ClientAdapter delete(Client client) {
        List<ClientAdapter> ca = daoClient.getClients(new QueryParam("c.id", client.getId()));

        if (!ca.isEmpty()) {
            Client cl = daoClient.delete(ca.get(0).getClient());
            if (cl != null) {
                Identite i = ca.get(0).getIdentite();
                Log.i(this, "Identité id::" + i.getId());
                this.daoIdentite.delete(i);

                WalletAdapter wa = ca.get(0).getWalletAdapter();
                if (wa != null) {
                    this.daoWallet.delete(wa.getWallet());
                    for (CompteAdapter listeDeCompte : wa.getListeDeComptes()) {
                        this.daoCompte.delete(listeDeCompte.getCompte());
                    }

                }

            }
            return ca.get(0);

        } else {
            Log.i(this, "Aucun client trouvé");
        }

        return null;
    }

}
