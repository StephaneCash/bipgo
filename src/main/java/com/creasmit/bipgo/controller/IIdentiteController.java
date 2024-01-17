/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.entity.Identite;

/**
 *
 * @author emmanueltombo
 */
public interface IIdentiteController {

    public Identite get(Identite identite);

    public Identite add(Identite identite);

    public Identite update(Identite identite);

    public Identite delete(Identite identite);
}
