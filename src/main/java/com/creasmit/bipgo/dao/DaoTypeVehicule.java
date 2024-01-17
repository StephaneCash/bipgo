/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.TypeVehicule;
import java.util.ArrayList;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;

/**
 *
 * @author georges
 */
public class DaoTypeVehicule implements IDaoTypeVehicule {
    
    private IDaoGeneric dao;

    public DaoTypeVehicule() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<TypeVehicule> getTypeVehicule() {
        try {
            return (List<TypeVehicule>) this.dao.getList(TypeVehicule.class);
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
}
