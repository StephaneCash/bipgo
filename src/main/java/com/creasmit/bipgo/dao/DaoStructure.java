/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.StructureAdapter;
import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Gerant;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
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
public class DaoStructure implements IDaoStructure {

    private IDaoGeneric dao;

    public DaoStructure() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<StructureAdapter> getStructures() {
        try {
            String sql = "SELECT"
                    + "	s.id,"
                    + "	s.denomination,"
                    + "	s.type,"
                    + "	s.agentCreate sAgentCreate,"
                    + "	s.dateCreate sDateCreate,"
                    + "	s.agentModif sAgentModif,"
                    + "	s.dateModif sDateModif,"
                    + "	g.id gId,"
                    + "	g.agentCreate gAgentCreate,"
                    + "	g.dateCreate gDateCreate,"
                    + "	g.agentModif gAgentModif,"
                    + "	g.dateModif gDateModif,"
                    + " g.fkProfile,"
                    + " g.fkIdentite,"
                    + " g.fkStructure,"
                    + "	i.id iId,"
                    + "	i.nom,"
                    + "	i.postnom,"
                    + "	i.prenom,"
                    + "	i.numTel,"
                    + "	i.email,"
                    + "	i.carteIdentite "
                    + "FROM"
                    + "	Structure s"
                    + "	LEFT JOIN Gerant g ON g.fkStructure = s.id"
                    + "	LEFT JOIN Identite i ON i.id = g.fkIdentite";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            List<StructureAdapter> structureAdapters = new ArrayList<>();
            for (Object[] o : objects) {
                Structure s = new Structure();
                s.setId(Integer.parseInt(o[0].toString()));
                try {
                    s.setDenomination(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    s.setType(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    s.setAgentCreate(Integer.parseInt(o[3].toString()));
                } catch (Exception e) {
                }
                try {
                    s.setDateCreate((Date) o[4]);
                } catch (Exception e) {
                }
                try {
                    s.setAgentModif(Integer.parseInt(o[5].toString()));
                } catch (Exception e) {
                }
                try {
                    s.setDateModif((Date) o[6]);
                } catch (Exception e) {
                }

                Gerant g = new Gerant();
                try {
                    g.setId(Integer.parseInt(o[7].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setAgentCreate(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setDateCreate((Date) o[9]);
                } catch (Exception e) {
                }
                try {
                    g.setAgentModif(Integer.parseInt(o[10].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setDateModif((Date) o[11]);
                } catch (Exception e) {
                }

                try {
                    g.setFkProfile(Integer.parseInt(o[12].toString()));
                } catch (Exception e) {
                }

                try {
                    g.setFkIdentite(Integer.parseInt(o[13].toString()));
                } catch (Exception e) {
                }

                try {
                    g.setFkStructure(Integer.parseInt(o[14].toString()));
                } catch (Exception e) {
                }

                Identite i = new Identite();
                try {
                    i.setId(Integer.parseInt(o[15].toString()));
                } catch (Exception e) {
                }
                try {
                    i.setNom(o[16].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPostnom(o[17].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPrenom(o[18].toString());
                } catch (Exception e) {
                }
                try {
                    i.setNumTel(o[19].toString());
                } catch (Exception e) {
                }
                try {
                    i.setEmail(o[20].toString());
                } catch (Exception e) {
                }
                try {
                    i.setCarteIdentite(o[21].toString());
                } catch (Exception e) {
                }

                ConfirmationCompte cc = new ConfirmationCompte();
                try {
                    cc.setId(Integer.parseInt(o[11].toString()));
                } catch (Exception e) {
                }

                try {
                    cc.setPin(o[12].toString());
                } catch (Exception e) {
                }

                StructureAdapter sa = new StructureAdapter();
                sa.setStructure(s);
                sa.setGerant(g);
                sa.setIdentite(i);
                structureAdapters.add(sa);

            }
            return structureAdapters;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<StructureAdapter> getStructures(QueryParam... queryParams) {
        try {
            String sql = "SELECT"
                    + "	s.id,"
                    + "	s.denomination,"
                    + "	s.type,"
                    + "	s.agentCreate sAgentCreate,"
                    + "	s.dateCreate sDateCreate,"
                    + "	s.agentModif sAgentModif,"
                    + "	s.dateModif sDateModif,"
                    + "	g.id gId,"
                    + "	g.agentCreate gAgentCreate,"
                    + "	g.dateCreate gDateCreate,"
                    + "	g.agentModif gAgentModif,"
                    + "	g.dateModif gDateModif,"
                    + " g.fkProfile,"
                    + " g.fkIdentite,"
                    + " g.fkStructure,"
                    + "	i.id iId,"
                    + "	i.nom,"
                    + "	i.postnom,"
                    + "	i.prenom,"
                    + "	i.numTel,"
                    + "	i.email,"
                    + "	i.carteIdentite,"
                    + "cc.id idCc,"
                    + "cc.pin pinCC "
                    + "FROM"
                    + "	Structure s"
                    + "	LEFT JOIN Gerant g ON g.fkStructure = s.id "
                    + "	LEFT JOIN Identite i ON i.id = g.fkIdentite "
                    + " LEFT JOIN ConfirmationCompte cc on cc.fkIdentite=i.id WHERE";
            int comp = 0;
            for (QueryParam queryParam : queryParams) {
                if (comp == 0) {
                    sql += " " + queryParam.getParam() + "=" + queryParam.getValue();
                } else {
                    sql += " and " + queryParam.getParam() + "=" + queryParam.getValue();
                }
                comp++;
            }
            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            List<StructureAdapter> structureAdapters = new ArrayList<>();
            for (Object[] o : objects) {
                Structure s = new Structure();
                s.setId(Integer.parseInt(o[0].toString()));
                try {
                    s.setDenomination(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    s.setType(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    s.setAgentCreate(Integer.parseInt(o[3].toString()));
                } catch (Exception e) {
                }
                try {
                    s.setDateCreate((Date) o[4]);
                } catch (Exception e) {
                }
                try {
                    s.setAgentModif(Integer.parseInt(o[5].toString()));
                } catch (Exception e) {
                }
                try {
                    s.setDateModif((Date) o[6]);
                } catch (Exception e) {
                }

                Gerant g = new Gerant();
                try {
                    g.setId(Integer.parseInt(o[7].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setAgentCreate(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setDateCreate((Date) o[9]);
                } catch (Exception e) {
                }
                try {
                    g.setAgentModif(Integer.parseInt(o[10].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setDateModif((Date) o[11]);
                } catch (Exception e) {
                }

                try {
                    g.setFkProfile(Integer.parseInt(o[12].toString()));
                } catch (Exception e) {
                }

                try {
                    g.setFkIdentite(Integer.parseInt(o[13].toString()));
                } catch (Exception e) {
                }

                try {
                    g.setFkStructure(Integer.parseInt(o[14].toString()));
                } catch (Exception e) {
                }

                Identite i = new Identite();
                try {
                    i.setId(Integer.parseInt(o[15].toString()));
                } catch (Exception e) {
                }
                try {
                    i.setNom(o[16].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPostnom(o[17].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPrenom(o[18].toString());
                } catch (Exception e) {
                }
                try {
                    i.setNumTel(o[19].toString());
                } catch (Exception e) {
                }
                try {
                    i.setEmail(o[20].toString());
                } catch (Exception e) {
                }
                try {
                    i.setCarteIdentite(o[21].toString());
                } catch (Exception e) {
                }

                ConfirmationCompte cc = new ConfirmationCompte();
                try {
                    cc.setId(Integer.parseInt(o[22].toString()));
                } catch (Exception e) {
                }

                try {
                    cc.setPin(o[23].toString());
                } catch (Exception e) {
                }

                StructureAdapter sa = new StructureAdapter();
                sa.setStructure(s);
                sa.setGerant(g);
                sa.setIdentite(i);
                sa.setConfirmationCompte(cc);
                structureAdapters.add(sa);

            }
            return structureAdapters;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<StructureAdapter> find(QueryParam... queryParams) {
        try {
            String sql = "SELECT"
                    + "	s.id,"
                    + "	s.denomination,"
                    + "	s.type,"
                    + "	s.agentCreate sAgentCreate,"
                    + "	s.dateCreate sDateCreate,"
                    + "	s.agentModif sAgentModif,"
                    + "	s.dateModif sDateModif,"
                    + "	g.id gId,"
                    + "	g.agentCreate gAgentCreate,"
                    + "	g.dateCreate gDateCreate,"
                    + "	g.agentModif gAgentModif,"
                    + "	g.dateModif gDateModif,"
                    + "	i.id iId,"
                    + "	i.nom,"
                    + "	i.postnom,"
                    + "	i.prenom,"
                    + "	i.numTel,"
                    + "	i.email,"
                    + "	i.carteIdentite "
                    + "FROM"
                    + "	Structure s"
                    + "	LEFT JOIN Gerant g ON g.fkStructure = s.id"
                    + "	LEFT JOIN Identite i ON i.id = g.fkIdentite WHERE";
            int comp = 0;
            for (QueryParam queryParam : queryParams) {
                if (comp == 0) {
                    sql += " " + queryParam.getParam() + " like '" + queryParam.getValue() + "%'";
                } else {
                    sql += " and " + queryParam.getParam() + " like '" + queryParam.getValue() + "%'";
                }
                comp++;
            }
            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            List<StructureAdapter> structureAdapters = new ArrayList<>();
            for (Object[] o : objects) {
                Structure s = new Structure();
                s.setId(Integer.parseInt(o[0].toString()));
                try {
                    s.setDenomination(o[1].toString());
                } catch (Exception e) {
                }
                try {
                    s.setType(o[2].toString());
                } catch (Exception e) {
                }
                try {
                    s.setAgentCreate(Integer.parseInt(o[3].toString()));
                } catch (Exception e) {
                }
                try {
                    s.setDateCreate((Date) o[4]);
                } catch (Exception e) {
                }
                try {
                    s.setAgentModif(Integer.parseInt(o[5].toString()));
                } catch (Exception e) {
                }
                try {
                    s.setDateModif((Date) o[6]);
                } catch (Exception e) {
                }

                Gerant g = new Gerant();
                try {
                    g.setId(Integer.parseInt(o[7].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setAgentCreate(Integer.parseInt(o[8].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setDateCreate((Date) o[9]);
                } catch (Exception e) {
                }
                try {
                    g.setAgentModif(Integer.parseInt(o[10].toString()));
                } catch (Exception e) {
                }
                try {
                    g.setDateModif((Date) o[11]);
                } catch (Exception e) {
                }

                Identite i = new Identite();
                try {
                    i.setId(Integer.parseInt(o[12].toString()));
                } catch (Exception e) {
                }
                try {
                    i.setNom(o[13].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPostnom(o[14].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPrenom(o[15].toString());
                } catch (Exception e) {
                }
                try {
                    i.setNumTel(o[16].toString());
                } catch (Exception e) {
                }
                try {
                    i.setEmail(o[17].toString());
                } catch (Exception e) {
                }
                try {
                    i.setCarteIdentite(o[18].toString());
                } catch (Exception e) {
                }

                StructureAdapter sa = new StructureAdapter();
                sa.setStructure(s);
                sa.setGerant(g);
                sa.setIdentite(i);
                structureAdapters.add(sa);

            }
            return structureAdapters;
        } catch (Exception e) {
            Log.i(this, e);
        }
        return new ArrayList<>();
    }

    @Override
    public Structure add(Structure structure) {
        try {
            return (Structure) this.dao.save(structure);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return null;
    }

    @Override
    public Structure update(Structure structure) {
        try {
            return (Structure) this.dao.modify(structure);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return null;
    }

    @Override
    public Structure delete(Structure structure) {
        try {
            return (Structure) this.dao.remove(structure);
        } catch (Exception e) {
            Log.i(this, e);
        }
        return null;
    }

}
