/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.LineAdapter;
import com.creasmit.bipgo.entity.Line;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public interface IDaoLine {
    
    public List<LineAdapter> getLines(QueryParam... queryParams);

    public Line add(Line line);

    public Line update(Line line);

    public Line delete(Line line);
    
}
