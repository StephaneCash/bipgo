/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Prix;
import one.creas.emalib.hbd.IDaoGeneric;

/**
 *
 * @author georges
 */
public class DaoPrix implements IDaoPrix {

    public IDaoGeneric dao;

    public DaoPrix() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public Prix add(Prix prix) {
        try {
            return (Prix) this.dao.save(prix);
        } catch (Exception e) {
            
        }
        return null;
    }

    @Override
    public Prix update(Prix prix) {
        try {
            return (Prix) this.dao.modify(prix);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Prix delete(Prix prix) {
        try {
            return (Prix) this.dao.remove(prix);
        } catch (Exception e) {
        }
        return null;
    }

}
