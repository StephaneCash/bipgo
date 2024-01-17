/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.GerantAdapter;
import com.creasmit.bipgo.entity.Gerant;
import com.creasmit.bipgo.entity.Structure;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
 public interface IDaoGerant {

    public List<GerantAdapter> getGerants(QueryParam...queryParam);
    
    public List<GerantAdapter> getGerants(Structure structure);

    public Gerant add(Gerant gerant);

    public Gerant update(Gerant gerant);

    public Gerant delete(Gerant gerant);
}
