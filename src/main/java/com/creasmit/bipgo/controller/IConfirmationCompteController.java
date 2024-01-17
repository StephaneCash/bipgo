/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.entity.ConfirmationCompte;

/**
 *
 * @author emmanueltombo
 */
public interface IConfirmationCompteController {

    public ConfirmationCompte init(ConfirmationCompte c);
    
    public ConfirmationCompte confirm(ConfirmationCompte c);

    public ConfirmationCompte resend(String numTel);
}
