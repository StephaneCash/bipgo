package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Client;
import com.creasmit.bipgo.entity.Identite;

/**
 *
 * @author CREASMIT_ZEUS
 */
public class ClientAdapter {

    private Client client;
    private Identite identite;
    private WalletAdapter walletAdapter;

    public ClientAdapter() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    public WalletAdapter getWalletAdapter() {
        return walletAdapter;
    }

    public void setWalletAdapter(WalletAdapter walletAdapter) {
        this.walletAdapter = walletAdapter;
    }
}
