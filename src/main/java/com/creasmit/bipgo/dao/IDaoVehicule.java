/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.VehiculeAdapter;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author Christ Mantima
 */
public interface IDaoVehicule {

    public List<VehiculeAdapter> getVehicule(Structure structure);

    public List<VehiculeAdapter> getVehicule(QueryParam... queryParams);

    public Vehicule add(Vehicule vehicule);

    public Vehicule update(Vehicule vehicule);

    public Vehicule delete(Vehicule vehicule);

    public Long delete(QueryParam... queryParams);

}
