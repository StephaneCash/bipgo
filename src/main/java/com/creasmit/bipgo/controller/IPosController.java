/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.StatutPos;
import java.util.List;

/**
 *
 * @author emmanueltombo
 */
public interface IPosController {

    public List<Pos> listing();

    public List<Pos> getByStatut(StatutPos statutPos);
    
    public Pos getByCode(String code);
    
    public List<Pos> find(String code);
    
    public Pos add(Pos pos);

    public Pos EnableAndDisable(Pos pos);
    
    public Pos delete(Pos pos);
}
