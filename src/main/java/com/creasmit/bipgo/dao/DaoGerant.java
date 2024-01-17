/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.GerantAdapter;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Gerant;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.model.Profil;
import java.util.ArrayList;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author emmanueltombo
 */
public class DaoGerant implements IDaoGerant {

    private IDaoGeneric dao;

    public DaoGerant() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<GerantAdapter> getGerants(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "i.id idIdentite,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.numTel,"
                    + "i.email,"
                    + "i.photo,"
                    + "s.id idStructure,"
                    + "s.type typeStructure,"
                    + "s.denomination,"
                    + "g.fkProfile "
                    + "FROM Gerant g "
                    + "JOIN Identite i on g.fkIdentite=i.id "
                    + "JOIN Structure s on s.id=g.fkStructure WHERE";

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

            List<GerantAdapter> gerantAdapters = new ArrayList<>();
            for (Object[] ob : objects) {
                Identite i = new Identite();
                i.setId(Integer.parseInt(ob[0].toString()));
                try {
                    i.setNom(ob[1].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPostnom(ob[2].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPrenom(ob[3].toString());
                } catch (Exception e) {
                }

                try {
                    i.setNumTel(ob[4].toString());
                } catch (Exception e) {
                }

                try {
                    i.setEmail(ob[5].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPhoto(ob[6].toString());
                } catch (Exception e) {
                }

                Structure s = new Structure();
                s.setId(Integer.parseInt(ob[7].toString()));
                try {
                    s.setType(ob[8].toString());
                } catch (Exception e) {
                }

                try {
                    s.setDenomination(ob[9].toString());
                } catch (Exception e) {
                }

                Profil p = new Profil();
                try {
                    p.setId(Integer.parseInt(ob[10].toString()));
                } catch (Exception ex) {

                }

                GerantAdapter ga = new GerantAdapter();
                ga.setIdentite(i);
                ga.setStructure(s);
                ga.setProfil(p);

                gerantAdapters.add(ga);

            }

            return gerantAdapters;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<GerantAdapter> getGerants(Structure structure) {
        try {
            String sql = "SELECT "
                    + "i.id idIdentite,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.numTel,"
                    + "i.email,"
                    + "i.photo,"
                    + "s.id idStructure,"
                    + "s.type typeStructure,"
                    + "s.denomination,"
                    + "g.fkProfile,"
                    + "cc.id idCc,"
                    + "cc.pin pinCC "
                    + "FROM Gerant g "
                    + "JOIN Identite i on g.fkIdentite=i.id "
                    + "JOIN Structure s on s.id=g.fkStructure "
                    + "LEFT JOIN ConfirmationCompte cc on cc.fkIdentite=i.id WHERE s.id=:sId";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam("sId", structure.getId()));

            List<GerantAdapter> gerantAdapters = new ArrayList<>();
            for (Object[] ob : objects) {

                //
                Identite i = new Identite();
                i.setId(Integer.parseInt(ob[0].toString()));
                try {
                    i.setNom(ob[1].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPostnom(ob[2].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPrenom(ob[3].toString());
                } catch (Exception e) {
                }

                try {
                    i.setNumTel(ob[4].toString());
                } catch (Exception e) {
                }

                try {
                    i.setEmail(ob[5].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPhoto(ob[6].toString());
                } catch (Exception e) {
                }

                Log.i(this, i.getNom());

                //
                Structure s = new Structure();
                s.setId(Integer.parseInt(ob[7].toString()));
                try {
                    s.setType(ob[8].toString());
                } catch (Exception e) {
                }

                try {
                    s.setDenomination(ob[9].toString());
                } catch (Exception e) {
                }

                Profil p = new Profil();
                try {
                    p.setId(Integer.parseInt(ob[10].toString()));
                } catch (Exception e) {
                }

                ConfirmationCompte cc = new ConfirmationCompte();
                try {
                    cc.setId(Integer.parseInt(ob[11].toString()));
                } catch (Exception e) {
                }

                try {
                    cc.setPin(ob[12].toString());
                } catch (Exception e) {
                }

                GerantAdapter ga = new GerantAdapter();
                ga.setIdentite(i);
                ga.setStructure(s);
                ga.setProfil(p);
                ga.setConfirmationCompte(cc);

                gerantAdapters.add(ga);

                Log.i(this, i.getNom());

            }

            return gerantAdapters;
        } catch (Exception e) {
            Log.i(this, e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Gerant add(Gerant gerant) {
        try {
            return (Gerant) this.dao.save(gerant);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Gerant update(Gerant gerant) {
        try {
            return (Gerant) this.dao.modify(gerant);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Gerant delete(Gerant gerant) {
        try {
            return (Gerant) this.dao.remove(gerant);
        } catch (Exception e) {
        }
        return null;
    }

}
