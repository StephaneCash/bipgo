/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.dao.IDaoPrix;
import com.creasmit.bipgo.entity.Prix;

/**
 *
 * @author georges
 */
public class PrixController implements IPrixController {
    
    private IDaoPrix daoPrix;

    public PrixController() {
    }

    public void setDaoPrix(IDaoPrix daoPrix) {
        this.daoPrix = daoPrix;
    }

   
    @Override
    public Prix add(Prix prix) {
        return this.daoPrix.add(prix);
    }

    @Override
    public Prix update(Prix prix) {
        return this.daoPrix.update(prix);
    }

    @Override
    public Prix delete(Prix prix) {
        return this.daoPrix.delete(prix);
    }
    
}
