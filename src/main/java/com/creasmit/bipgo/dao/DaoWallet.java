/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.entity.Wallet;
import com.google.gson.Gson;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public class DaoWallet implements IDaoWallet {

    private IDaoGeneric dao;
    private IDaoCompte daoCompte;

    public DaoWallet() {

    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    @Override
    public WalletAdapter get(Wallet wallet) {

        try {

            String sql = "SELECT "
                    + "w.id wid,"
                    + "w.bipid "
                    + " FROM Wallet w WHERE w.bipid='" + wallet.getBipid() + "'";
            Log.i(this, sql);
            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            Log.i(this, new Gson().toJson(objects));

            WalletAdapter wa = null;
            for (Object[] o : objects) {
                wa = new WalletAdapter();
                Wallet w = new Wallet();
                try {
                    Log.i(this, o[0].toString());
                    w.setId(Integer.parseInt(o[0].toString()));
                } catch (Exception e) {
                    Log.i(this, e);
                }
                try {
                    w.setBipid(o[1].toString());
                } catch (Exception e) {
                }

                wa.setWallet(w);
                wa.setListeDeComptes(this.daoCompte.getCompte(new QueryParam("c.fkWallet", w.getId())));
            }
            return wa;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public WalletAdapter get(int id) {

        try {

            String sql = "SELECT "
                    + "w.id wid,"
                    + "w.bipid "
                    + " FROM Wallet w WHERE w.id=" + id;

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            Log.i(this, new Gson().toJson(objects));

            WalletAdapter wa = null;
            for (Object[] o : objects) {
                wa = new WalletAdapter();
                Wallet w = new Wallet();
                try {
                    Log.i(this, o[0].toString());
                    w.setId(Integer.parseInt(o[0].toString()));
                } catch (Exception e) {
                    Log.i(this, e);
                }
                try {
                    w.setBipid(o[1].toString());
                } catch (Exception e) {
                }

                wa.setWallet(w);
                wa.setListeDeComptes(this.daoCompte.getCompte(new QueryParam("c.fkWallet", w.getId())));
            }
            return wa;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public WalletAdapter getWalletCompte(Wallet wallet) {
        try {

            String sql = "SELECT "
                    + "w.id wid,"
                    + "w.bipid "
                    + " FROM Wallet w WHERE w.bipid='" + wallet.getBipid() + "' and w.pin='" + wallet.getPin() + "'";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));

            WalletAdapter wa = null;
            for (Object[] o : objects) {
                wa = new WalletAdapter();
                Wallet w = new Wallet();
                try {
                    w.setId(Integer.parseInt(o[0].toString()));
                } catch (Exception e) {
                    Log.i(this, e);
                }
                try {
                    w.setBipid(o[1].toString());
                } catch (Exception e) {
                }

                wa.setWallet(w);
                wa.setListeDeComptes(this.daoCompte.getCompte(new QueryParam("c.fkWallet", w.getId())));
                Log.i(this, "DataWallet");
                Log.i(this, new Gson().toJson(wa));
            }
            return wa;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Wallet add(Wallet wallet) {
        try {
            return (Wallet) this.dao.save(wallet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Wallet update(Wallet wallet) {
        try {
            return (Wallet) this.dao.modify(wallet);
        } catch (Exception e) {
        }
        return null;

    }

    @Override
    public Wallet delete(Wallet wallet) {
        try {
            return (Wallet) this.dao.remove(wallet);
        } catch (Exception e) {
        }
        return null;

    }

}
