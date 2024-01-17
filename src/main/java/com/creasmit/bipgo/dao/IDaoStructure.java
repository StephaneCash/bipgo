/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.StructureAdapter;
import com.creasmit.bipgo.entity.Structure;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public interface IDaoStructure {

    public List<StructureAdapter> getStructures();

    public List<StructureAdapter> getStructures(QueryParam... queryParams);

    public List<StructureAdapter> find(QueryParam... queryParams);

    public Structure add(Structure structure);

    public Structure update(Structure structure);

    public Structure delete(Structure structure);
}
