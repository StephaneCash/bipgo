/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.CompteAdapter;
import com.creasmit.bipgo.dao.IDaoCompte;
import com.creasmit.bipgo.dao.IDaoDevise;
import com.creasmit.bipgo.entity.Compte;

/**
 *
 * @author georges
 */
public class CompteController implements ICompteController {

    private IDaoCompte daoCompte;

    public CompteController() {

    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    @Override
    public Compte add(Compte compte) {
        
        return this.daoCompte.add(compte);
    }

    @Override
    public Compte update(Compte compte) {
        return this.daoCompte.update(compte);
    }

    @Override
    public Compte delete(Compte compte) {
        return this.daoCompte.delete(compte);
    }
}
