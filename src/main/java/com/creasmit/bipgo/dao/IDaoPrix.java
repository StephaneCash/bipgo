/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Prix;

/**
 *
 * @author georges
 */
public interface IDaoPrix {
    
    public Prix add(Prix prix);

    public Prix update(Prix prix);

    public Prix delete(Prix prix);
    
}
