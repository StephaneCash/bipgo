/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Activation;
import one.creas.emalib.hbd.IDaoGeneric;

/**
 *
 * @author Christ Mantima
 */
public class DaoActivation implements IDaoActivation {
    
    public IDaoGeneric dao;

    public DaoActivation() {
    }
    
    

    @Override
    public Activation add(Activation activation) {
        try {
            return (Activation) this.dao.save(activation);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Activation update(Activation activation) {
        try {
            return (Activation) this.dao.modify(activation);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Activation delete(Activation activation) {
        try {
            return (Activation) this.dao.remove(activation);
        } catch (Exception e) {
        }
        return null;
    }
    
}
