/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.AssistantAdapter;
import com.creasmit.bipgo.entity.Assistant;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;
import java.util.ArrayList;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author Christ Mantima
 */
public class DaoAssistant implements IDaoAssistant {

    public IDaoGeneric dao;

    public DaoAssistant() {

    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<AssistantAdapter> getAssistant(Structure structure) {
        try {
            String sql = "SELECT "
                    + "a.id idAssistant,"
                    + "a.fkStructure,"
                    + "a.fkIdentite,"
                    + "a.fkVehicule,"
                    + "i.id identiteId,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.numTel,"
                    + "i.email,"
                    + "i.photo,"
                    + "s.id idStructure,"
                    + "s.type typeStructure,"
                    + "s.denomination,"
                    + "v.id vehiculeId,"
                    + "v.numero_plaque,"
                    + "v.modele,"
                    + "v.marque,"
                    + "v.carte_rose "
                    + "FROM assistant a "
                    + "JOIN Vehicule v on v.id=a.fkVehicule "
                    + "JOIN Identite i on i.id=a.fkIdentite "
                    + "LEFT JOIN Structure s on s.id=a.fkStructure WHERE a.fkStructure=:structureId";
            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam("structureId", structure.getId()));
            List<AssistantAdapter> assistantAdapters = new ArrayList<>();

            for (Object[] ob : objects) {
                AssistantAdapter ass = new AssistantAdapter();

                Assistant a = new Assistant();

                a.setId(Integer.parseInt(ob[0].toString()));
                ass.setAssistant(a);
                try {
                    a.setFkStructure(Integer.parseInt(ob[1].toString()));
                } catch (Exception e) {
                }

                try {
                    a.setFkIdentite(Integer.parseInt(ob[2].toString()));
                } catch (Exception e) {
                }
                try {
                    a.setFkVehicule(Integer.parseInt(ob[3].toString()));
                } catch (Exception e) {
                }

                Identite i = new Identite();

                i.setId(Integer.parseInt(ob[4].toString()));

                try {
                    i.setNom(ob[5].toString());
                } catch (Exception e) {

                }

                try {
                    i.setPostnom(ob[6].toString());
                } catch (Exception e) {

                }
                try {
                    i.setPrenom(ob[7].toString());
                } catch (Exception e) {

                }

                try {
                    i.setNumTel(ob[8].toString());
                } catch (Exception e) {
                }

                try {
                    i.setEmail(ob[9].toString());
                } catch (Exception e) {
                }

                try {
                    i.setPhoto(ob[10].toString());
                } catch (Exception e) {
                }
                ass.setIdentite(i);

                Structure s = new Structure();

                try {
                    s.setId(Integer.parseInt(ob[11].toString()));
                } catch (Exception e) {
                }

                try {
                    s.setType(ob[12].toString());
                } catch (Exception e) {
                }

                try {
                    s.setDenomination(ob[13].toString());
                } catch (Exception e) {
                }

                Vehicule v = new Vehicule();
                try {
                    v.setId(Integer.parseInt(ob[14].toString()));
                } catch (Exception e) {
                }

                try {
                    v.setNumero_plaque(ob[15].toString());
                } catch (Exception e) {
                }

                try {
                    v.setModele(ob[16].toString());
                } catch (Exception e) {
                }

                try {
                    v.setMarque(ob[17].toString());
                } catch (Exception e) {
                }

                try {
                    v.setCarte_rose(ob[18].toString());
                } catch (Exception e) {
                }
                ass.setVehicule(v);
                ass.setStructure(s);

                assistantAdapters.add(ass);
            }
            return assistantAdapters;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    @Override
    public List<AssistantAdapter> getAssistant(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "a.id idAssistant,"
                    + "i.id idIdentite,"
                    + "i.nom,"
                    + "i.postnom,"
                    + "i.prenom,"
                    + "i.numTel,"
                    + "i.email,"
                    + "i.photo,"
                    + "s.id idStructure,"
                    + "s.type typeStructure,"
                    + "s.denomination "
                    + "FROM assistant a "
                    + "JOIN Vehicule v on v.id=a.fkVehicule "
                    + "JOIN Identite i on i.id=a.fkIdentite "
                    + "JOIN Structure s on s.id=a.fkStructure WHERE";

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
            List<AssistantAdapter> assistantAdapters = new ArrayList<>();

            for (Object[] ob : objects) {
                AssistantAdapter ass = new AssistantAdapter();

                Assistant a = new Assistant();

                a.setId(Integer.parseInt(ob[0].toString()));
                ass.setAssistant(a);

                Identite i = new Identite();

                i.setId(Integer.parseInt(ob[1].toString()));

                try {
                    i.setNom(ob[2].toString());
                } catch (Exception e) {

                }

                ass.setIdentite(i);

                try {
                    i.setPrenom(ob[4].toString());
                } catch (Exception e) {

                }

                ass.setIdentite(i);

                try {
                    i.setNumTel(ob[5].toString());
                } catch (Exception e) {
                }
                ass.setIdentite(i);

                try {
                    i.setEmail(ob[6].toString());
                } catch (Exception e) {
                }
                ass.setIdentite(i);

                try {
                    i.setPhoto(ob[7].toString());
                } catch (Exception e) {
                }
                ass.setIdentite(i);

                Structure s = new Structure();
                s.setId(Integer.parseInt(ob[8].toString()));

                try {
                    s.setType(ob[9].toString());
                } catch (Exception e) {
                }
                ass.setStructure(s);

                try {
                    s.setDenomination(ob[10].toString());
                } catch (Exception e) {
                }
                ass.setStructure(s);

                assistantAdapters.add(ass);
            }
            return assistantAdapters;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Assistant add(Assistant assistant) {
        try {
            return (Assistant) this.dao.save(assistant);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public Assistant update(Assistant assistant) {
        try {
            return (Assistant) this.dao.modify(assistant);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public Assistant delete(Assistant assistant) {
        try {
            return (Assistant) this.dao.remove(assistant);
        } catch (Exception e) {

        }
        return null;
    }

}
