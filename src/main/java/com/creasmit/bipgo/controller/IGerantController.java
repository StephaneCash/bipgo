/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.GerantAdapter;
import com.creasmit.bipgo.entity.Gerant;
import com.creasmit.bipgo.entity.Structure;

/**
 *
 * @author emmanueltombo
 */
public interface IGerantController {

    public GerantAdapter get(Structure structure);

    public GerantAdapter add(GerantAdapter gerantAdapter);

    public GerantAdapter update(GerantAdapter gerantAdapter);

    public GerantAdapter delete(Gerant gerant);
}
