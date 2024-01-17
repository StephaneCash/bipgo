/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.dao.IDaoTypeVehicule;
import com.creasmit.bipgo.entity.TypeVehicule;
import java.util.List;

/**
 *
 * @author georges
 */
public class TypeVehiculeController implements ITypeVehiculeController {

    private IDaoTypeVehicule daoTypeVehicule;

    public TypeVehiculeController() {
    }

    public void setDaoTypeVehicule(IDaoTypeVehicule daoTypeVehicule) {
        this.daoTypeVehicule = daoTypeVehicule;
    }

 
    @Override
    public List<TypeVehicule> listing() {
        return this.daoTypeVehicule.getTypeVehicule();
    }

}
