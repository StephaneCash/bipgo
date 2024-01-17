/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.dao.IDaoPos;
import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.StatutPos;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class PosController implements IPosController {

    private IDaoPos dao;

    public PosController() {
    }

    public void setDao(IDaoPos dao) {
        this.dao = dao;
    }

    @Override
    public List<Pos> listing() {
        return this.dao.getPos();
    }

    @Override
    public List<Pos> getByStatut(StatutPos statutPos) {
        return this.dao.getPos(new QueryParam("fkStatutPos", statutPos.getId()));
    }

    @Override
    public Pos getByCode(String code) {
        List<Pos> poses = this.dao.getPos(new QueryParam("code", code));
        if (!poses.isEmpty()) {
            return poses.get(0);
        }
        return null;
    }

    @Override
    public List<Pos> find(String code) {
        return this.dao.find(new QueryParam("code", code));
    }

    @Override
    public Pos add(Pos pos) {
        return this.dao.add(pos);
    }

    @Override
    public Pos delete(Pos pos) {
        return this.dao.delete(pos);
    }

    @Override
    public Pos EnableAndDisable(Pos pos) {
        return this.dao.update(pos);
    }

}
