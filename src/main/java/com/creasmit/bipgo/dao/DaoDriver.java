package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.DriverAdapter;
import com.creasmit.bipgo.entity.Driver;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.QueryParam;

public class DaoDriver implements IDaoDriver {

    public IDaoGeneric dao;

    public DaoDriver() {
    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    @Override
    public List<DriverAdapter> getDrivers(Structure structure) {
        try {
            String sql = "SELECT "
                    + "d.id idDriver,"
                    + "d.fkStructure,"
                    + "d.fkIdentite,"
                    + "d.fkVehicule,"
                    + "d.dateCreate,"
                    + "d.agentCreate,"
                    + "d.dateModif,"
                    + "d.agentModif,"
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
                    + "v.id vehiculeId,"
                    + "v.numero_plaque,"
                    + "v.modele,"
                    + "v.marque,"
                    + "v.carte_rose,"
                    + "d.modePaiement,"
                    + "d.adresse "
                    + "FROM driver d "
                    + "JOIN Vehicule v on v.id=d.fkVehicule "
                    + "JOIN Identite i on i.id=d.fkIdentite "
                    + "JOIN Structure s on s.id=d.fkStructure WHERE s.id=:structureId";

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam("structureId", structure.getId()));
            List<DriverAdapter> driverAdapters = new ArrayList<>();

            for (Object[] ob : objects) {
                DriverAdapter da = new DriverAdapter();

                Driver d = new Driver();

                d.setId(Integer.parseInt(ob[0].toString()));
                da.setDriver(d);

                try {
                    d.setFkStructure(Integer.parseInt(ob[1].toString()));

                } catch (Exception e) {
                }

                try {
                    d.setFkIdentite(Integer.parseInt(ob[2].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setFkVehicule(Integer.parseInt(ob[3].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setDateCreate((Date) ob[4]);
                } catch (Exception e) {
                }

                try {
                    d.setAgentCreate(Integer.parseInt(ob[5].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setDateModif((Date) ob[6]);
                } catch (Exception e) {
                }

                try {
                    d.setAgentModif(Integer.parseInt(ob[7].toString()));
                } catch (Exception e) {
                }
                try {
                    d.setModePaiement(ob[23].toString());
                } catch (Exception e) {
                }

                try {
                    d.setFkIdentite(Integer.parseInt(ob[2].toString()));

                    d.setAdresse(ob[24].toString());

                } catch (Exception e) {
                }

                try {
                    d.setFkVehicule(Integer.parseInt(ob[3].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setDateCreate((Date) ob[4]);
                } catch (Exception e) {
                }

                try {
                    d.setAgentCreate(Integer.parseInt(ob[5].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setDateModif((Date) ob[6]);
                } catch (Exception e) {
                }

                try {
                    d.setAgentModif(Integer.parseInt(ob[7].toString()));
                } catch (Exception e) {
                }
                try {
                    d.setModePaiement(ob[23].toString());
                } catch (Exception e) {
                }

                Identite i = new Identite();
                i.setId(Integer.parseInt(ob[8].toString()));

                try {
                    i.setNom(ob[9].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPostnom(ob[10].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPrenom(ob[11].toString());
                } catch (Exception e) {
                }

                try {
                    i.setNumTel(ob[12].toString());
                } catch (Exception e) {
                }
                try {
                    i.setEmail(ob[13].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPhoto(ob[14].toString());
                } catch (Exception e) {
                }
                da.setIdentite(i);

                Structure s = new Structure();
                try {
                    s.setId(Integer.parseInt(ob[15].toString()));
                } catch (Exception e) {
                }

                try {
                    s.setType(ob[16].toString());
                } catch (Exception e) {
                }

                try {
                    s.setDenomination(ob[17].toString());
                } catch (Exception e) {
                }

                Vehicule v = new Vehicule();
                try {
                    v.setId(Integer.parseInt(ob[18].toString()));
                } catch (Exception e) {
                }

                try {
                    v.setNumero_plaque(ob[19].toString());
                } catch (Exception e) {
                }

                try {
                    v.setModele(ob[20].toString());
                } catch (Exception e) {
                }

                try {
                    v.setMarque(ob[21].toString());
                } catch (Exception e) {
                }

                try {
                    v.setCarte_rose(ob[22].toString());
                } catch (Exception e) {
                }
                da.setVehicule(v);

                da.setStructure(s);
                driverAdapters.add(da);
            }
            return driverAdapters;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<DriverAdapter> getDrivers(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "d.id idDriver,"
                    + "d.fkStructure,"
                    + "d.fkIdentite,"
                    + "d.fkVehicule,"
                    + "d.dateCreate,"
                    + "d.agentCreate,"
                    + "d.dateModif,"
                    + "d.agentModif,"
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
                    + "v.id vehiculeId,"
                    + "v.numero_plaque,"
                    + "v.modele,"
                    + "v.marque,"
                    + "v.carte_rose,"
                    + "d.modePaiement "
                    + "FROM driver d "
                    + "JOIN Vehicule v on v.id=d.fkVehicule "
                    + "JOIN Identite i on i.id=d.fkIdentite "
                    + "JOIN Structure s on s.id=d.fkStructure WHERE";

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
            List<DriverAdapter> driverAdapters = new ArrayList<>();

            for (Object[] ob : objects) {
                DriverAdapter da = new DriverAdapter();

                Driver d = new Driver();

                d.setId(Integer.parseInt(ob[0].toString()));
                da.setDriver(d);

                try {
                    d.setFkStructure(Integer.parseInt(ob[1].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setFkIdentite(Integer.parseInt(ob[2].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setFkVehicule(Integer.parseInt(ob[3].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setDateCreate((Date) ob[4]);
                } catch (Exception e) {
                }

                try {
                    d.setAgentCreate(Integer.parseInt(ob[5].toString()));
                } catch (Exception e) {
                }

                try {
                    d.setDateModif((Date) ob[6]);
                } catch (Exception e) {
                }

                try {
                    d.setAgentModif(Integer.parseInt(ob[7].toString()));
                } catch (Exception e) {
                }
                try {
                    d.setModePaiement(ob[23].toString());
                } catch (Exception e) {
                }

                Identite i = new Identite();
                i.setId(Integer.parseInt(ob[8].toString()));

                try {
                    i.setNom(ob[9].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPostnom(ob[10].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPrenom(ob[11].toString());
                } catch (Exception e) {
                }

                try {
                    i.setNumTel(ob[12].toString());
                } catch (Exception e) {
                }
                try {
                    i.setEmail(ob[13].toString());
                } catch (Exception e) {
                }
                try {
                    i.setPhoto(ob[14].toString());
                } catch (Exception e) {
                }
                da.setIdentite(i);

                Structure s = new Structure();
                try {
                    s.setId(Integer.parseInt(ob[15].toString()));
                } catch (Exception e) {
                }

                try {
                    s.setType(ob[16].toString());
                } catch (Exception e) {
                }

                try {
                    s.setDenomination(ob[17].toString());
                } catch (Exception e) {
                }

                Vehicule v = new Vehicule();
                try {
                    v.setId(Integer.parseInt(ob[18].toString()));
                } catch (Exception e) {
                }

                try {
                    v.setNumero_plaque(ob[19].toString());
                } catch (Exception e) {
                }

                try {
                    v.setModele(ob[20].toString());
                } catch (Exception e) {
                }

                try {
                    v.setMarque(ob[21].toString());
                } catch (Exception e) {
                }

                try {
                    v.setCarte_rose(ob[22].toString());
                } catch (Exception e) {
                }
                da.setVehicule(v);

                da.setStructure(s);
                driverAdapters.add(da);
            }
            return driverAdapters;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Driver add(Driver driver) {
        try {
            return (Driver) this.dao.save(driver);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Driver update(Driver driver) {
        try {
            return (Driver) this.dao.modify(driver);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Driver delete(Driver driver) {
        try {
            return (Driver) this.dao.remove(driver);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Long delete(QueryParam... queryParams) {

        try {
            return (Long) this.dao.delete(Driver.class, queryParams);
        } catch (Exception e) {
        }
        return null;
    }
}
