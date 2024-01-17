/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.dao.IDaoIdentite;
import com.creasmit.bipgo.entity.Identite;

/**
 *
 * @author emmanueltombo
 */
public class IdentiteController implements IIdentiteController {

    private IDaoIdentite dao;

    public IdentiteController() {
    }

    public void setDao(IDaoIdentite dao) {
        this.dao = dao;
    }

    @Override
    public Identite get(Identite identite) {
        return this.dao.getIdentite(identite);
    }

    @Override
    public Identite add(Identite identite) {
        return this.dao.add(identite);
    }

    @Override
    public Identite update(Identite identite) {
        return this.dao.update(identite);
    }

    @Override
    public Identite delete(Identite identite) {
        return this.dao.delete(identite);
    }

}
