/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.CompteAdapter;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Devise;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public class DaoCompte implements IDaoCompte {

    public IDaoGeneric dao;

    public DaoCompte() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<CompteAdapter> getCompte(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "c.id cid,"
                    + "c.montant,"
                    + "d.id did,"
                    + "d.libelle,"
                    + "c.fkWallet "
                    + "FROM Compte c "
                    + "JOIN Devise d on d.id=c.fkDevise WHERE";

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
            Log.i(this, objects.toString());

            List<CompteAdapter> compteAdapter = new ArrayList<>();
            for (Object[] ob : objects) {
                Compte c = new Compte();
                c.setId(Integer.parseInt(ob[0].toString()));

                try {
                    c.setMontant(Double.parseDouble(ob[1].toString()));
                } catch (Exception e) {
                }

                try {
                    c.setFkDevise(Integer.parseInt(ob[2].toString()));
                } catch (Exception e) {
                }

                try {
                    c.setFkWallet(Integer.parseInt(ob[4].toString()));
                } catch (Exception e) {
                }

                //
                Devise d = new Devise();
                d.setId(Integer.parseInt(ob[2].toString()));
                try {
                    d.setLibelle(ob[3].toString());
                } catch (Exception e) {
                }

                CompteAdapter aa = new CompteAdapter();
                aa.setDevise(d);
                aa.setCompte(c);
                compteAdapter.add(aa);

            }

            return compteAdapter;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    @Override
    public Compte add(Compte compte) {
        try {
            return (Compte) this.dao.save(compte);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Compte update(Compte compte) {
        try {
            return (Compte) this.dao.modify(compte);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Compte delete(Compte compte) {
        try {
            return (Compte) this.dao.remove(compte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
