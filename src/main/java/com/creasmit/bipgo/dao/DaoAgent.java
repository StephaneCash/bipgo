/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.AgentAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.entity.Agent;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.TypeAgent;
import com.creasmit.bipgo.entity.Wallet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public class DaoAgent implements IDaoAgent {

    public IDaoGeneric dao;
    private IDaoCompte daoCompte;

    public DaoAgent() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }
    
     @Override
    public List<AgentAdapter> getAgents(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "a.id agentId,"
                    + "a.agentCreate,"
                    + "a.dateCreate,"
                    + "a.dateModif,"
                    + "a.agentModif,"
                    + "a.codeActivation,"
                    + "i.id identiteId,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.carteIdentite,"
                    + "i.photo,"
                    + "i.numTel,"
                    + "i.email, "
                    + "ta.id typeAgentId, "
                    + "w.id walletId,"
                    + "w.bipid "
                    + "FROM Agent a "
                    + "JOIN  Identite i on i.id=a.fkIdentite "
                    + "LEFT JOIN Wallet w on w.id=a.fkWallet "
                    + "JOIN TypeAgent ta on ta.id=a.fkTypeAgent "
                    + "LEFT JOIN ConfirmationCompte cc on cc.fkIdentite=i.id WHERE";

            int comp = 0;
            for (QueryParam queryParam : queryParams) {
                if (comp == 0) {
                    sql += " " + queryParam.getParam() + "=:" + queryParam.getParam();
                } else {
                    sql += " and " + queryParam.getParam() + "=:" + queryParam.getParam();
                }
                comp++;
            }

            Log.i(this, sql);

            List<Object[]> objects = this.dao.selectSQL(sql,queryParams);
            Log.i(this, objects.toString());

            List<AgentAdapter> agentAdapter = new ArrayList<>();
            for (Object[] ob : objects) {
                Agent a = new Agent();
                a.setId(Integer.parseInt(ob[0].toString()));

                try {
                    a.setAgentCreate(Integer.parseInt(ob[1].toString()));
                } catch (Exception e) {

                }
                try {
                    a.setDateCreate((Date) ob[2]);
                } catch (Exception e) {

                }
                
                try {
                    a.setDateModif((Date) ob[3]);
                } catch (Exception e) {

                }
                
                try {
                    a.setAgentModif(Integer.parseInt(ob[4].toString()));
                } catch (Exception e) {

                }
                
                try {
                    a.setCodeActivation(ob[5].toString());
                } catch (Exception e) {

                }
                //
                Identite i = new Identite();
                try {
                    i.setId(Integer.parseInt(ob[6].toString()));
                } catch (Exception e) {
                }
                try {
                    i.setNom(ob[7].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPostnom(ob[8].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPrenom(ob[9].toString());
                } catch (Exception e) {
                }

                try {
                    i.setCarteIdentite(ob[10].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPhoto(ob[11].toString());
                } catch (Exception e) {
                }

                try {
                    i.setNumTel(ob[12].toString());
                } catch (Exception e) {
                }

                try {
                    i.setEmail(ob[13].toString());
                } catch (Exception e) {
                }

                //
                TypeAgent ta = new TypeAgent();
                try {
                     ta.setId(Integer.parseInt(ob[14].toString()));
                } catch (Exception e) {
                }

                Wallet wallet = new Wallet();
                try {
                    wallet.setId(Integer.parseInt(ob[15].toString()));
                } catch (Exception e) {
                }

                try {
                    wallet.setBipid(ob[16].toString());
                } catch (Exception e) {
                }

                WalletAdapter walletAdapter = new WalletAdapter();
                walletAdapter.setWallet(wallet);
                try {
                    walletAdapter.setListeDeComptes(daoCompte.getCompte(new QueryParam("c.fkWallet", wallet.getId())));
                } catch (Exception e) {
                    Log.i(this, e);
                    e.printStackTrace();
                }

                AgentAdapter aa = new AgentAdapter();
                aa.setAgent(a);
                aa.setIdentite(i);
                aa.setTypeAgent(ta);
                aa.setWalletAdapter(walletAdapter);

                agentAdapter.add(aa);

            }
            return agentAdapter;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public AgentAdapter getAgentCode(Agent ag) {
        try {
            String sql = "SELECT "
                    + "a.id agentId,"
                    + "a.agentCreate,"
                    + "a.dateCreate,"
                    + "a.dateModif,"
                    + "a.agentModif,"
                    + "a.codeActivation,"
                    + "i.id identiteId,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.carteIdentite,"
                    + "i.photo,"
                    + "i.numTel,"
                    + "i.email, "
                    + "ta.id typeAgentId, "
                    + "w.id walletId,"
                    + "w.bipid "
                    + "FROM Agent a "
                    + "LEFT JOIN  Identite i on i.id=a.fkIdentite "
                    + "LEFT JOIN Wallet w on w.id=a.fkWallet "
                    + "LEFT JOIN TypeAgent ta on ta.id=a.fkTypeAgent "
                    + "LEFT JOIN ConfirmationCompte cc on cc.fkIdentite=i.id WHERE a.codeActivation='"+ag.getCodeActivation()+"'";

         
            Log.i(this, sql);

            List<Object[]> objects = this.dao.selectSQL(sql,new QueryParam(null, null));
            Log.i(this, objects.toString());

              AgentAdapter aa = new AgentAdapter();
            for (Object[] ob : objects) {
                Agent a = new Agent();
                a.setId(Integer.parseInt(ob[0].toString()));

                try {
                    a.setAgentCreate(Integer.parseInt(ob[1].toString()));
                } catch (Exception e) {

                }
                try {
                    a.setDateCreate((Date) ob[2]);
                } catch (Exception e) {

                }
                try {
                    a.setAgentModif(Integer.parseInt(ob[3].toString()));
                } catch (Exception e) {

                }
                try {
                    a.setDateModif((Date) ob[4]);
                } catch (Exception e) {

                }
                try{
                    a.setCodeActivation(ob[5].toString());
                } catch (Exception e){
                    
                }
                //
                Identite i = new Identite();
                i.setId(Integer.parseInt(ob[5].toString()));
                try {
                    i.setNom(ob[6].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPostnom(ob[7].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPrenom(ob[8].toString());
                } catch (Exception e) {
                }

                try {
                    i.setCarteIdentite(ob[9].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPhoto(ob[10].toString());
                } catch (Exception e) {
                }

                try {
                    i.setNumTel(ob[11].toString());
                } catch (Exception e) {
                }

                try {
                    i.setEmail(ob[12].toString());
                } catch (Exception e) {
                }

                //
                TypeAgent ta = new TypeAgent();
                ta.setId(Integer.parseInt(ob[13].toString()));

                Wallet wallet = new Wallet();
                try {
                    wallet.setId(Integer.parseInt(ob[14].toString()));
                } catch (Exception e) {
                }

                try {
                    wallet.setBipid(ob[15].toString());
                } catch (Exception e) {
                }

                WalletAdapter walletAdapter = new WalletAdapter();
                walletAdapter.setWallet(wallet);
                try {
                    walletAdapter.setListeDeComptes(daoCompte.getCompte(new QueryParam("c.fkWallet", wallet.getId())));
                } catch (Exception e) {
                    Log.i(this, e);
                    e.printStackTrace();
                }

              
                aa.setAgent(a);
                aa.setIdentite(i);
                aa.setTypeAgent(ta);
                aa.setWalletAdapter(walletAdapter);

            }

            return aa;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Agent add(Agent agent) {
        try {
            return (Agent) this.dao.save(agent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Agent update(Agent agent) {
        try {
            return (Agent) this.dao.modify(agent);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Agent delete(Agent agent) {
        try {
            return (Agent) this.dao.remove(agent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
