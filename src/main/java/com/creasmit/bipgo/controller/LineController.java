/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.LineAdapter;
import com.creasmit.bipgo.dao.IDaoLine;
import com.creasmit.bipgo.entity.Line;
import com.creasmit.bipgo.entity.TypeVehicule;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class LineController implements ILineController{
    
    private IDaoLine daoLine;

    public void setDaoLine(IDaoLine daoLine) {
        this.daoLine = daoLine;
    }

    @Override
    public List<LineAdapter> list(TypeVehicule typeVehicule) {
        return this.daoLine.getLines(new QueryParam("tv.id", typeVehicule.getId()));
      }

    @Override
    public Line add(Line line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Line update(LineAdapter lineAdapter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Line delete(Line line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
