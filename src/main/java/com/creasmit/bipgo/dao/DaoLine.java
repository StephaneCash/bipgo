/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.LineAdapter;
import com.creasmit.bipgo.entity.Line;
import com.creasmit.bipgo.entity.Prix;
import com.creasmit.bipgo.entity.TypeVehicule;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public class DaoLine implements IDaoLine {

    public IDaoGeneric dao;

    public DaoLine() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<LineAdapter> getLines(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "l.id lineId,"
                    + "l.description,"
                    + "l.agentCreate,"
                    + "l.dateCreate,"
                    + "l.agentModif,"
                    + "l.dateModif,"
                    + "tv.id tvId,"
                    + "tv.libelle,"
                    + "p.id prixId,"
                    + "p.montant,"
                    + "p.devise "
                    + "FROM Line l "
                    + "JOIN TypeVehiculeLinePrix tpl on tpl.lineId=l.id "
                    + "JOIN TypeVehicule tv ON tv.id=tpl.typeVehiculeId  "
                    + "JOIN Prix p ON p.id=tpl.prixId WHERE";

            int comp = 0;
            for (QueryParam queryParam : queryParams) {
                if (comp == 0) {
                    sql += " " + queryParam.getParam() + "=" + queryParam.getValue();
                } else {
                    sql += " and " + queryParam.getParam() + "=" + queryParam.getValue();
                }
                comp++;
            }

            Log.i(this, sql);

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));

            List<LineAdapter> lineAdapter = new ArrayList<>();
            for (Object[] ob : objects) {
                Line a = new Line();
                a.setId(Integer.parseInt(ob[0].toString()));

                try {
                    a.setDescription(ob[1].toString());
                } catch (Exception e) {

                }
                try {
                    a.setAgentCreate(Integer.parseInt(ob[2].toString()));
                } catch (Exception e) {

                }

                try {
                    a.setDateCreate((Date) ob[3]);
                } catch (Exception e) {

                }
                try {
                    a.setAgentModif(Integer.parseInt(ob[4].toString()));
                } catch (Exception e) {

                }
                try {
                    a.setDateModif((Date) ob[5]);
                } catch (Exception e) {

                }
                //
                TypeVehicule tv = new TypeVehicule();
                tv.setId(Integer.parseInt(ob[6].toString()));
                try {
                    tv.setLibelle(ob[7].toString());
                } catch (Exception e) {
                }

                Prix p = new Prix();
                p.setId(Integer.parseInt(ob[8].toString()));
                try {
                    p.setMontant(Double.parseDouble(ob[9].toString()));
                } catch (Exception e) {
                }

                try {
                    p.setDevise(ob[10].toString());
                } catch (Exception e) {
                }

                LineAdapter li = new LineAdapter();
                li.setLine(a);
                li.setPrix(p);
                li.setTypeVehicule(tv);

                lineAdapter.add(li);

            }

            return lineAdapter;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Line add(Line line) {
        try {
            return (Line) this.dao.save(line);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Line update(Line line) {
        try {
            return (Line) this.dao.modify(line);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Line delete(Line line) {
        try {
            return (Line) this.dao.remove(line);
        } catch (Exception e) {
        }
        return null;
    }

}
