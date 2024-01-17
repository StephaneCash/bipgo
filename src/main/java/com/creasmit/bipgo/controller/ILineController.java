/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.LineAdapter;
import com.creasmit.bipgo.entity.Line;
import com.creasmit.bipgo.entity.TypeVehicule;
import java.util.List;

/**
 *
 * @author emmanueltombo
 */
public interface ILineController {

    public List<LineAdapter> list(TypeVehicule typeVehicule);

    public Line add(Line line);

    public Line update(LineAdapter lineAdapter);

    public Line delete(Line line);
}
