/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.TypeOperation;
import java.util.ArrayList;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;

/**
 *
 * @author georges
 */
public class DaoTypeOperation implements IDaoTypeOperation {

    public IDaoGeneric dao;

    public DaoTypeOperation() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<TypeOperation> getTypeOperation() {
        try {
            return (List<TypeOperation>) this.dao.getList(TypeOperation.class);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }
}
