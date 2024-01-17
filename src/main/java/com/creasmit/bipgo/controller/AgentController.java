/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.AgentAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.dao.IDaoAgent;
import com.creasmit.bipgo.dao.IDaoCompte;
import com.creasmit.bipgo.dao.IDaoIdentite;
import com.creasmit.bipgo.dao.IDaoWallet;
import com.creasmit.bipgo.entity.Agent;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.TypeAgent;
import com.creasmit.bipgo.entity.Wallet;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;
import one.creas.emalib.util.Utils;
import one.creas.emalib.util.ValueValidateException;

/**
 *
 * @author georges
 */
public class AgentController implements IAgentController {

    private IDaoAgent daoAgent;
    private IDaoIdentite daoIdentite;
    private IDaoWallet daoWallet;
    private IDaoCompte daoCompte;

    public AgentController() {

    }

    public void setDaoAgent(IDaoAgent daoAgent) {
        this.daoAgent = daoAgent;
    }

    public void setDaoIdentite(IDaoIdentite daoIdentite) {
        this.daoIdentite = daoIdentite;
    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    @Override
    public List<AgentAdapter> listAgent(TypeAgent typeAgent) {
        return this.daoAgent.getAgents(new QueryParam("a.fkTypeAgent", typeAgent.getId()));
    }

    @Override
    public AgentAdapter get(TypeAgent typeAgent) {
        List<AgentAdapter> agentAdapters = this.daoAgent.getAgents(new QueryParam("fkTypeAgent", typeAgent.getId()));
        if (!agentAdapters.isEmpty()) {
            return agentAdapters.get(0);
        }
        return null;
    }

    @Override
    public AgentAdapter add(AgentAdapter agentAdapter) {
        Agent agent = agentAdapter.getAgent();
        Identite identite = agentAdapter.getIdentite();
        Log.i(this, new Gson().toJson(identite));

        Identite identiteSaved = this.daoIdentite.add(identite);

        if (identiteSaved != null) {

            if (agent.getFkTypeAgent() == 2) {

                Wallet w = new Wallet();
                try {
                    w.setBipid(3 + identite.getId() + Utils.generatePin(6));
                } catch (ValueValidateException ex) {
                    Log.i(this, ex.getMessage());
                }
                try {
                    w.setPin(Utils.generatePin(6));
                } catch (ValueValidateException ex) {
                    w.setPin("123456");
                }

                try {
                    Wallet walletSaved = daoWallet.add(w);
                    Log.i(this, new Gson().toJson(walletSaved));
                    if (walletSaved != null) {
                        agent.setFkWallet(walletSaved.getId());
                        Compte compte = new Compte();
                        compte.setFkDevise(1);
                        compte.setMontant(0.0);
                        compte.setFkWallet(walletSaved.getId());
                        Compte compteSaved = daoCompte.add(compte);
                        if (compteSaved != null) {
                            String code = agent.getId() + "" + Utils.generatePin(6);
                            agent.setCodeActivation(code);
                            WalletAdapter walletAdapter = new WalletAdapter();
                            walletAdapter.setWallet(walletSaved);
                            agentAdapter.setWalletAdapter(walletAdapter);

                        } else {
                            daoWallet.delete(walletSaved);
                        }
                    } else {
                        daoWallet.delete(walletSaved);
                    }
                } catch (Exception e) {
                    Log.i(this, e);
                    e.printStackTrace();
                }
            }
            try {
                agent.setCodeActivation(identiteSaved.getId() + Utils.generatePin(5));
            } catch (ValueValidateException ex) {
            }
            agent.setFkIdentite(identiteSaved.getId());
            agent.setDateCreate(new Date());

            Agent agentSaved = this.daoAgent.add(agent);
            if (agentSaved != null) {

                agentAdapter.setAgent(agentSaved);
                agentAdapter.setIdentite(identiteSaved);
                return agentAdapter;
            } else {
                Log.i(this, "Not saved");
                this.daoIdentite.delete(identiteSaved);
            }
        }

        return null;
    }

    @Override
    public AgentAdapter update(AgentAdapter agentAdapter) {
        Identite identite = agentAdapter.getIdentite();
        Identite identiteSaved = this.daoIdentite.update(identite);

        Agent agent = agentAdapter.getAgent();
        agent.setFkWallet(agentAdapter.getWalletAdapter().getWallet().getId());
        if (identiteSaved != null) {
            agent.setDateModif(new Date());
            Agent agentModif = this.daoAgent.update(agent);

            agentAdapter.setIdentite(identiteSaved);
            agentAdapter.setAgent(agentModif);
            return agentAdapter;
        }
        return null;

    }

    @Override
    public AgentAdapter delete(Agent agent) {

        List<AgentAdapter> aa = daoAgent.getAgents(new QueryParam("a.id", agent.getId()));

        if (!aa.isEmpty()) {
            Identite i = daoAgent.getAgents(new QueryParam("a.id", agent.getId())).get(0).getIdentite();

            Log.i(this, "Identité id::" + i.getId());

            Identite identiteDeleted = this.daoIdentite.delete(i);
            if (identiteDeleted != null) {
                this.daoAgent.delete(agent);

                AgentAdapter a = new AgentAdapter();
                a.setIdentite(identiteDeleted);
                return a;
            }
        } else {
            Log.i(this, "Aucun agent trouvé");
        }

        return null;
    }

    @Override
    public AgentAdapter activeCompte(Agent agent) {
        List<AgentAdapter> aa = this.daoAgent.getAgents(new QueryParam("a.codeActivation", agent.getCodeActivation()));

        if (!aa.isEmpty()) {
            return aa.get(0);
        }
        return null;
    }

}
