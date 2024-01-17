package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.ClientAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.entity.Client;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Wallet;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author CREASMIT_ZEUS
 */
public class DaoClient implements IDaoClient {

    public IDaoGeneric dao;

    public IDaoCompte daoCompte;

    public DaoClient() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }
    
     @Override
    public List<ClientAdapter> getClients() {
        try {
            String sql = "SELECT "
                    + "c.id clientId,"
                    + "c.agentCreate,"
                    + "c.agentModif,"
                    + "c.dateCreate,"
                    + "c.dateModif,"
                    + "i.id identiteId,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.carteIdentite,"
                    + "i.photo,"
                    + "i.numTel,"
                    + "i.email, "
                    + "w.id walletId,"
                    + "w.bipid "
                    + "FROM client c "
                    + "JOIN  Identite i on i.id=c.fkIdentite "
                    + "JOIN Wallet w on w.id=c.fkWallet ";


            Log.i(this, sql);

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            Log.i(this, new Gson().toJson(objects));

            List<ClientAdapter> clientAdapter = new ArrayList<>();
            for (Object[] ob : objects) {
                Client c = new Client();
                c.setId(Integer.parseInt(ob[0].toString()));

                try {
                    c.setAgentCreate(Integer.parseInt(ob[1].toString()));
                } catch (Exception e) {

                }
                
                try {
                    c.setAgentModif(Integer.parseInt(ob[2].toString()));
                } catch (Exception e) {

                }
                
                try {
                    c.setDateCreate((Date) ob[3]);
                } catch (Exception e) {

                }
                
                try {
                    c.setDateModif((Date) ob[4]);
                } catch (Exception e) {

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
                
                 Wallet wallet = new Wallet();
                try {
                    wallet.setId(Integer.parseInt(ob[13].toString()));
                } catch (Exception e) {
                }

                try {
                    wallet.setBipid(ob[14].toString());
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

                ClientAdapter cc = new ClientAdapter();
                cc.setClient(c);
                cc.setIdentite(i);
                
                cc.setWalletAdapter(walletAdapter);

                clientAdapter.add(cc);
            }

            return clientAdapter;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    @Override
    public List<ClientAdapter> getClients(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "c.id clientId,"
                    + "c.agentCreate,"
                    + "c.agentModif,"
                    + "c.dateCreate,"
                    + "c.dateModif,"
                    + "i.id identiteId,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.carteIdentite,"
                    + "i.photo,"
                    + "i.numTel,"
                    + "i.email, "
                    + "w.id walletId,"
                    + "w.bipid "
                    + "FROM client c "
                    + "JOIN  Identite i on i.id=c.fkIdentite "
                    + "LEFT JOIN Wallet w on w.id=c.fkWallet WHERE";

            int comp = 0;
            for (QueryParam queryParam : queryParams) {
                if (comp == 0) {
                    sql += " " + queryParam.getParam() + "=" + queryParam.getValue();
                } else {
                    sql += " and " + queryParam.getParam() + "=" + queryParam.getValue();
                }
                comp++;
            }

            Log.i(this, sql);

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));

            List<ClientAdapter> clientAdapter = new ArrayList<>();
            for (Object[] ob : objects) {
                Client c = new Client();
                c.setId(Integer.parseInt(ob[0].toString()));

                try {
                    c.setAgentCreate(Integer.parseInt(ob[1].toString()));
                } catch (Exception e) {

                }
                
                try {
                    c.setAgentModif(Integer.parseInt(ob[2].toString()));
                } catch (Exception e) {

                }
                
                try {
                    c.setDateCreate((Date) ob[3]);
                } catch (Exception e) {

                }
                
                try {
                    c.setDateModif((Date) ob[4]);
                } catch (Exception e) {

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
                
                 Wallet wallet = new Wallet();
                try {
                    wallet.setId(Integer.parseInt(ob[13].toString()));
                } catch (Exception e) {
                }

                try {
                    wallet.setBipid(ob[14].toString());
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

                ClientAdapter cc = new ClientAdapter();
                cc.setClient(c);
                cc.setIdentite(i);
                cc.setWalletAdapter(walletAdapter);

                clientAdapter.add(cc);
            }

            return clientAdapter;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Client add(Client client) {
        try {
            return (Client) this.dao.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client update(Client client) {
        try {
            return (Client) this.dao.modify(client);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Client delete(Client client) {
        try {
            return (Client) this.dao.remove(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
