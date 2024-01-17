/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.entity.Pos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class DaoPos implements IDaoPos {

    public IDaoGeneric dao;

    public DaoPos() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<Pos> getPos() {
        try {
            return (List<Pos>) this.dao.getList(Pos.class);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Pos> getPos(QueryParam... queryParams) {
        try {
            return (List<Pos>)this.dao.findAll(Pos.class, queryParams);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public Pos add(Pos pos) {
        try {
            return (Pos) this.dao.save(pos);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return null;
    }

    @Override
    public Pos update(Pos pos) {
        return (Pos) this.dao.modify(pos);
    }

    @Override
    public Pos delete(Pos pos) {
        return (Pos) this.dao.remove(pos);
    }

    @Override
    public List<Pos> find(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "p.id,"
                    + "p.code,"
                    + "p.sn,"
                    + "p.dateCreate,"
                    + "p.agentCreate,"
                    + "p.fkStatutPos "
                    + "FROM Pos p WHERE";

            int i = 0;
            for (QueryParam queryParam : queryParams) {
                if (i == 0) {
                    sql += " " + queryParam.getParam() + " like ('" + queryParam.getValue()+"%') LIMIT 5";
                } else {
                    sql += " and " + queryParam.getParam() + " like (" + queryParam.getValue()+"%) LIMIT 5";
                }
                i++;
            }

            Log.i(this, sql);

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));

            List<Pos> posList = new ArrayList<>();
            for (Object[] ob : objects) {
                Pos p = new Pos();
                p.setId(Integer.parseInt(ob[0].toString()));
                try {
                    p.setCode(ob[1].toString());
                } catch (Exception e) {
                    Log.i(this, e);
                }

                try {
                    p.setSn(ob[2].toString());
                } catch (Exception e) {
                    Log.i(this, e);
                }

                try {
                    p.setDateCreate((Date) ob[3]);
                } catch (Exception e) {
                    Log.i(this, e);
                }

                try {
                    p.setAgentCreate(Integer.parseInt(ob[4].toString()));
                } catch (Exception e) {
                    Log.i(this, e);
                }

                try {
                    p.setFkStatutPos(Integer.parseInt(ob[5].toString()));
                } catch (Exception e) {
                    Log.i(this, e);
                }

                posList.add(p);
            }

            return posList;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

}
