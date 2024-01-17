/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.ConfirmationCompte;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public interface IDaoConfirmationCompte {

    public ConfirmationCompte add(ConfirmationCompte c);

    public ConfirmationCompte get(QueryParam... q);

    public ConfirmationCompte update(ConfirmationCompte c);

    public ConfirmationCompte delete(ConfirmationCompte c);
}
