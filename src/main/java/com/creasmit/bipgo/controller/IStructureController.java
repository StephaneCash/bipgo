/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.StructureAdapter;
import com.creasmit.bipgo.entity.Structure;
import java.util.List;

/**
 *
 * @author georges
 */
public interface IStructureController {
    
    public List<StructureAdapter> listing();
    
    public List<StructureAdapter> getStructure(int id);
    
    public List<StructureAdapter> listing(String denom);

    public Structure add(Structure structure);

    public Structure update(Structure structure);

    public Structure delete(Structure structure);
    
}
