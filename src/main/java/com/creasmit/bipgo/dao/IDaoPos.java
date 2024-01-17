/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Pos;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public interface IDaoPos {

    public List<Pos> getPos();

    public List<Pos> getPos(QueryParam... queryParams);
    
    public List<Pos> find(QueryParam... queryParams);

    public Pos add(Pos pos);

    public Pos update(Pos pos);

    public Pos delete(Pos pos);
}
