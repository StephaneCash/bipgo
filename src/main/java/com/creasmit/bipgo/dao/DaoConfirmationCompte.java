/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.ConfirmationCompte;
import one.creas.emalib.hbd.DaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class DaoConfirmationCompte implements IDaoConfirmationCompte {

    private DaoGeneric dao;

    public DaoConfirmationCompte() {
    }

    public void setDao(DaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public ConfirmationCompte add(ConfirmationCompte c) {
        try {
            return (ConfirmationCompte)this.dao.save(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ConfirmationCompte get(QueryParam... q) {
        try {
            return (ConfirmationCompte)this.dao.find(ConfirmationCompte.class, q);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return null;
    }

    @Override
    public ConfirmationCompte update(ConfirmationCompte c) {
        try {
            return (ConfirmationCompte)this.dao.modify(c);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return null;
    }

    @Override
    public ConfirmationCompte delete(ConfirmationCompte c) {
       try {
            return (ConfirmationCompte)this.dao.remove(c);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return null;
    }

}
