/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;
import com.creasmit.bipgo.entity.Agent;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.TypeAgent;

/**
 *
 * @author georges
 */
public class AgentAdapter {
    private Agent agent;
    private Identite identite;
    private TypeAgent typeAgent;
    private WalletAdapter walletAdapter;
    private ConfirmationCompte confirmationCompte;

    public AgentAdapter() {
    }
    
    public Agent getAgent() {
        return agent;
    }
    
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    public TypeAgent getTypeAgent() {
        return typeAgent;
    }

    public void setTypeAgent(TypeAgent typeAgent) {
        this.typeAgent = typeAgent;
    }

    public WalletAdapter getWalletAdapter() {
        return walletAdapter;
    }

    public void setWalletAdapter(WalletAdapter walletAdapter) {
        this.walletAdapter = walletAdapter;
    }

    public ConfirmationCompte getConfirmationCompte() {
        return confirmationCompte;
    }

    public void setConfirmationCompte(ConfirmationCompte confirmationCompte) {
        this.confirmationCompte = confirmationCompte;
    }
    
}
