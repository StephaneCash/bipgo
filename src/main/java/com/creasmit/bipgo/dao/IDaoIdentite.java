/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Identite;
import java.util.List;

/**
 *
 * @author emmanueltombo
 */
public interface IDaoIdentite {

    public Identite getIdentite(Identite inIdentite);

    public Identite add(Identite identite);

    public Identite update(Identite identite);

    public Identite delete(Identite identite);
}
