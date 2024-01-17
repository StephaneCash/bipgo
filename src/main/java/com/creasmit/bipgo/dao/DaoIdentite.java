/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Identite;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class DaoIdentite implements IDaoIdentite {

    private IDaoGeneric dao;

    public DaoIdentite() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public Identite getIdentite(Identite identite) {
        try {
            return (Identite) this.dao.find(Identite.class, new QueryParam("id", identite.getId()));
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Identite add(Identite identite) {
        try {
            return (Identite) this.dao.save(identite);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Identite update(Identite identite) {
        try {
            return (Identite) this.dao.modify(identite);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Identite delete(Identite identite) {
        try {
            return (Identite) this.dao.remove(identite);
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return null;
    }

}
