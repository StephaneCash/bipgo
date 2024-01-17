package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.DriverAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.dao.IDaoCompte;
import com.creasmit.bipgo.dao.IDaoDriver;
import com.creasmit.bipgo.dao.IDaoIdentite;
import com.creasmit.bipgo.dao.IDaoVehicule;
import com.creasmit.bipgo.dao.IDaoWallet;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Driver;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;
import com.creasmit.bipgo.entity.Wallet;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;
import one.creas.emalib.util.Utils;
import one.creas.emalib.util.ValueValidateException;

public class DriverController implements IDriverController {

    private IDaoDriver daoDriver;
    private IDaoIdentite daoIdentite;
    private IDaoVehicule daoVehicule;
    private IDaoWallet daoWallet;
    private IDaoCompte daoCompte;

    public DriverController() {

    }

    public IDaoDriver getDaoDriver() {
        return daoDriver;
    }

    public void setDaoDriver(IDaoDriver daoDriver) {
        this.daoDriver = daoDriver;
    }

    public IDaoIdentite getDaoIdentite() {
        return daoIdentite;
    }

    public void setDaoIdentite(IDaoIdentite daoIdentite) {
        this.daoIdentite = daoIdentite;
    }

    public IDaoVehicule getDaoVehicule() {
        return daoVehicule;
    }

    public void setDaoVehicule(IDaoVehicule daoVehicule) {
        this.daoVehicule = daoVehicule;
    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    @Override
    public List<DriverAdapter> getDriverByStructure(Structure structure) {
        return this.daoDriver.getDrivers(structure);
    }

    @Override
    public DriverAdapter add(DriverAdapter driverAdapter) {

        Structure structure = driverAdapter.getStructure();
        Identite identite = driverAdapter.getIdentite();
        Vehicule vehicule = driverAdapter.getVehicule();
        Driver d = driverAdapter.getDriver();
        Log.i(this, new Gson().toJson(d));

        Identite i = this.daoIdentite.add(identite);
        if (i != null) {

            if (d.getModePaiement().trim().equals("bySystem")) {

                Wallet w = new Wallet();
                try {
                    w.setBipid(1 + identite.getId() + Utils.generatePin(6));
                } catch (ValueValidateException ex) {
                    Log.i(this, ex.getMessage());
                }
                try {
                    w.setPin(Utils.generatePin(6));
                } catch (ValueValidateException ex) {
                    w.setPin("123456");
                }

                Wallet walletSaved = daoWallet.add(w);
                if (walletSaved != null) {
                    d.setFkWallet(walletSaved.getId());
                    Compte compte = new Compte();
                    compte.setFkDevise(1);
                    compte.setMontant(0.0);
                    Compte compteSaved = daoCompte.add(compte);
                    if (compteSaved != null) {

                        WalletAdapter walletAdapter = new WalletAdapter();
                        walletAdapter.setWallet(walletSaved);
                        driverAdapter.setWalletAdapter(walletAdapter);

                    } else {
                        daoWallet.delete(walletSaved);
                    }
                } else {
                    daoWallet.delete(walletSaved);
                }

            }

            d.setFkStructure(structure.getId());
            d.setFkIdentite(i.getId());
            d.setFkVehicule(vehicule.getId());
            d.setDateCreate(new Date());

            Driver driverSaved = this.daoDriver.add(d);
            if (driverSaved != null) {
                driverAdapter.setIdentite(identite);
                driverAdapter.setDriver(driverSaved);
                return driverAdapter;
            } else {
                this.daoIdentite.delete(i);
                return null;
            }

        }

        return null;
    }

    @Override
    public DriverAdapter update(DriverAdapter driverAdapter) {
        Vehicule vehicule = driverAdapter.getVehicule();
        Identite i = this.daoIdentite.update(driverAdapter.getIdentite());
        
        if (i != null) {
            Driver d = driverAdapter.getDriver();
            d.setFkVehicule(vehicule.getId());
            d.setDateModif(new Date());

            Driver driverModif = this.daoDriver.update(d);
            driverAdapter.setDriver(driverModif);
            return driverAdapter;
        }
        return null;
    }

    @Override
    public DriverAdapter delete(Driver driver) {

        List<DriverAdapter> driverAdapters = this.daoDriver.getDrivers(new QueryParam("d.id", driver.getId()));

        if (driverAdapters.size() > 0) {
            Identite i = driverAdapters.get(0).getIdentite();
            Identite identiteDeleted = this.daoIdentite.delete(i);

            if (identiteDeleted != null) {
                Driver d = this.daoDriver.delete(driver);

                DriverAdapter da = new DriverAdapter();
                da.setDriver(d);
                da.setIdentite(identiteDeleted);
                return da;
            }
        }

        return null;
    }

}
