/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Activation;

/**
 *
 * @author Christ Mantima
 */
public interface IDaoActivation {
    
    public Activation add(Activation activation);
    
    public Activation update(Activation activation);
    
    public Activation delete(Activation acitivation);
    
    
    
}
