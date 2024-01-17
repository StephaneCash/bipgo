/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.CompteAdapter;
import com.creasmit.bipgo.entity.Compte;


/**
 *
 * @author georges
 */
public interface ICompteController {
    
    public Compte add(Compte compte);

    public Compte update(Compte compte);

    public Compte delete(Compte compte);
    
    
}
