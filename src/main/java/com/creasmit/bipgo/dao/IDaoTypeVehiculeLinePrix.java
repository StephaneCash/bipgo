/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.TypeVehiculeLinePrix;

/**
 *
 * @author georges
 */
public interface IDaoTypeVehiculeLinePrix {

    public TypeVehiculeLinePrix add(TypeVehiculeLinePrix typeVehiculeLinePrix);

    public TypeVehiculeLinePrix update(TypeVehiculeLinePrix typeVehiculeLinePrix);

    public TypeVehiculeLinePrix delete(TypeVehiculeLinePrix typeVehiculeLinePrix);
    
}
