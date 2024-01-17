/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.VehiculeAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.TypeVehicule;
import com.creasmit.bipgo.entity.Vehicule;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import one.creas.emalib.hbd.IDaoGeneric;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author Christ Mantima
 */
public class DaoVehicule implements IDaoVehicule {

    private IDaoGeneric dao;
    private IDaoWallet daoWallet;

    public DaoVehicule() {

    }

    public void setDao(IDaoGeneric dao) {
        this.dao = dao;
    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }

    @Override
    public List<VehiculeAdapter> getVehicule(Structure structure) {
        try {
            String sql = "SELECT "
                    + "v.id idVehicule,"
                    + "v.numero_plaque,"
                    + "v.modele,"
                    + "v.marque,"
                    + "v.carte_rose,"
                    + "v.fkStructure,"
                    + "v.fkPos,"
                    + "v.fkTypeVehicule,"
                    + "s.id idStructure,"
                    + "s.denomination,"
                    + "p.id idPos,"
                    + "p.sn,"
                    + "p.code,"
                    + "t.id tvid,"
                    + "t.libelle tvLibelle, "
                    + "v.codeActivationTerminal "
                    + "FROM vehicule v "
                    + "LEFT JOIN Pos p on p.id=v.fkPos "
                    + "LEFT JOIN TypeVehicule t on t.id=v.fkTypeVehicule "
                    + "JOIN Structure s on s.id=v.fkStructure WHERE v.fkStructure=" + structure.getId();
            Log.i(this, sql);

            List<Object[]> objects = this.dao.selectSQL(sql, new QueryParam(null, null));
            Log.i(this, new Gson().toJson(objects));
            List<VehiculeAdapter> vehiculeAdapter = new ArrayList<>();

            for (Object[] ob : objects) {
                VehiculeAdapter va = new VehiculeAdapter();

                Vehicule v = new Vehicule();
                v.setId(Integer.parseInt(ob[0].toString()));

                try {
                    v.setNumero_plaque(ob[1].toString());
                } catch (Exception e) {
                }

                try {
                    v.setModele(ob[2].toString());
                } catch (Exception e) {

                }

                try {
                    v.setMarque(ob[3].toString());
                } catch (Exception e) {

                }

                try {
                    v.setCarte_rose(ob[4].toString());
                } catch (Exception e) {

                }

                try {
                    v.setFkStructure(Integer.parseInt(ob[5].toString()));
                } catch (Exception e) {

                }

                try {
                    v.setFkPos(Integer.parseInt(ob[6].toString()));
                } catch (Exception e) {

                }

                try {
                    v.setFkTypeVehicule(Integer.parseInt(ob[7].toString()));
                } catch (Exception e) {

                }

                try {
                    v.setCodeActivationTerminal(ob[15].toString());
                } catch (Exception e) {

                }
                va.setVehicule(v);

                Structure s = new Structure();
                try {
                    s.setId(Integer.parseInt(ob[8].toString()));
                } catch (Exception e) {
                }
                try {
                    s.setDenomination(ob[9].toString());
                } catch (Exception e) {
                }
                va.setStructure(s);

                Pos p = new Pos();
                try {
                    p.setId(Integer.parseInt(ob[10].toString()));
                } catch (Exception e) {
                }

                try {
                    p.setSn(ob[11].toString());
                } catch (Exception e) {
                }

                try {
                    p.setCode(ob[12].toString());
                } catch (Exception e) {
                }
                va.setPos(p);

                TypeVehicule tv = new TypeVehicule();
                try {
                    tv.setId(Integer.parseInt(ob[13].toString()));
                } catch (Exception e) {
                }
                try {
                    tv.setLibelle(ob[14].toString());
                } catch (Exception e) {
                }
                va.setTypeVehicule(tv);

                vehiculeAdapter.add(va);
            }

            return vehiculeAdapter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<VehiculeAdapter> getVehicule(QueryParam... queryParams) {
        try {
            String sql = "SELECT "
                    + "v.id idVehicule,"
                    + "v.numero_plaque,"
                    + "v.modele,"
                    + "v.marque,"
                    + "v.carte_rose,"
                    + "v.codeActivationTerminal,"
                    + "v.fkWallet,"
                    + "s.id idStructure,"
                    + "s.denomination,"
                    + "p.id idPos,"
                    + "p.sn,"
                    + "p.code,"
                    + "t.id tid, "
                    + "t.libelle "
                    + "FROM vehicule v "
                    + "JOIN Pos p on v.fkPos=p.id "
                    + "LEFT JOIN TypeVehicule t on v.fkTypeVehicule=t.id "
                    + "JOIN Structure s on s.id=v.fkStructure WHERE ";
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
            List<VehiculeAdapter> vehiculeAdapter = new ArrayList<>();

            for (Object[] ob : objects) {
                VehiculeAdapter va = new VehiculeAdapter();

                Vehicule v = new Vehicule();

                v.setId(Integer.parseInt(ob[0].toString()));
                va.setVehicule(v);

                try {
                    v.setNumero_plaque(ob[1].toString());
                } catch (Exception e) {
                }

                try {
                    v.setModele(ob[2].toString());
                } catch (Exception e) {

                }

                try {
                    v.setMarque(ob[3].toString());
                } catch (Exception e) {

                }

                try {
                    v.setCarte_rose(ob[4].toString());
                } catch (Exception e) {

                }
                try {
                    v.setCodeActivationTerminal(ob[5].toString());
                } catch (Exception e) {

                }
                WalletAdapter wa = null;
                try {
                    v.setFkWallet(Integer.parseInt(ob[6].toString()));
                    Log.i(this, "" + Integer.parseInt(ob[6].toString()));
                    wa = this.daoWallet.get(Integer.parseInt(ob[6].toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                va.setWalletAdapter(wa);

                va.setVehicule(v);

                Structure s = new Structure();
                s.setId(Integer.parseInt(ob[7].toString()));
                try {
                    s.setDenomination(ob[8].toString());
                } catch (Exception e) {
                }
                va.setStructure(s);

                Pos p = new Pos();
                p.setId(Integer.parseInt(ob[9].toString()));
                try {
                    p.setSn(ob[10].toString());
                } catch (Exception e) {
                }

                try {
                    p.setCode(ob[11].toString());
                } catch (Exception e) {
                }

                TypeVehicule tv = new TypeVehicule();
                try {
                    tv.setId(Integer.parseInt(ob[12].toString()));
                } catch (Exception e) {
                }

                try {
                    tv.setLibelle(ob[13].toString());
                } catch (Exception e) {
                }

                va.setPos(p);
                va.setTypeVehicule(tv);
                vehiculeAdapter.add(va);
                return vehiculeAdapter;
            }
        } catch (Exception e) {
            Log.i(this, e.getMessage());
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public Vehicule add(Vehicule vehicule) {
        try {
            return (Vehicule) this.dao.save(vehicule);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Vehicule update(Vehicule vehicule) {
        try {
            return (Vehicule) this.dao.modify(vehicule);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public Vehicule delete(Vehicule vehicule) {
        try {
            return (Vehicule) this.dao.remove(vehicule);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public Long delete(QueryParam... queryParams) {

        try {
            return (Long) this.dao.delete(Vehicule.class, queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
