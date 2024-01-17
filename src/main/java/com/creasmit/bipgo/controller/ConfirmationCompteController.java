/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.dao.IDaoConfirmationCompte;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class ConfirmationCompteController implements IConfirmationCompteController{

    private IDaoConfirmationCompte daoConfirmationCompte;

    public void setDaoConfirmationCompte(IDaoConfirmationCompte daoConfirmationCompte) {
        this.daoConfirmationCompte = daoConfirmationCompte;
    }
    
    @Override
    public ConfirmationCompte init(ConfirmationCompte c) {
        return this.daoConfirmationCompte.add(c);
    }

    @Override
    public ConfirmationCompte confirm(ConfirmationCompte c) {
        Log.i(this,  c.getPin());
        return this.daoConfirmationCompte.get(new QueryParam("pin", c.getPin()));
    }

    @Override
    public ConfirmationCompte resend(String numTel) {
        return this.daoConfirmationCompte.get(new QueryParam("phone", numTel));
    }
    
}
