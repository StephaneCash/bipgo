/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Devise;

/**
 *
 * @author georges
 */
public class CompteAdapter {
    private Compte compte;
    private Devise devise;
    
    public CompteAdapter(){
        
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }
    
    
    
}
