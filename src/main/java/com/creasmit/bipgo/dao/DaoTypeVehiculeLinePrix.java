/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.TypeVehiculeLinePrix;
import one.creas.emalib.hbd.IDaoGeneric;

/**
 *
 * @author georges
 */
public class DaoTypeVehiculeLinePrix implements IDaoTypeVehiculeLinePrix {
    
    
    public IDaoGeneric dao;

    public DaoTypeVehiculeLinePrix() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public TypeVehiculeLinePrix add(TypeVehiculeLinePrix typeVehiculeLinePrix) {
        try {
            return (TypeVehiculeLinePrix) this.dao.save(typeVehiculeLinePrix);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public TypeVehiculeLinePrix update(TypeVehiculeLinePrix typeVehiculeLinePrix) {
        try {
            return (TypeVehiculeLinePrix) this.dao.modify(typeVehiculeLinePrix);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public TypeVehiculeLinePrix delete(TypeVehiculeLinePrix typeVehiculeLinePrix) {
        try {
            return (TypeVehiculeLinePrix) this.dao.remove(typeVehiculeLinePrix);
        } catch (Exception e) {

        }
        return null;
    }

}
