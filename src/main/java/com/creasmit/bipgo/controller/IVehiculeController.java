/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.VehiculeAdapter;
import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;
import java.util.List;

/**
 *
 * @author Christ Mantima
 */
public interface IVehiculeController {
    
    public List<VehiculeAdapter> getVehiculeByStructure(Structure structure);
    
    public VehiculeAdapter getVehiculeByIdPos(Pos pos);
    
    public VehiculeAdapter activeCompte(Vehicule vehicule);
    
    public Vehicule add(Vehicule vehicule);
    
    public Vehicule update(Vehicule vehicule);
    
    public Vehicule delete(Vehicule vehicule);

}
