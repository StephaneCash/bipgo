/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.AssistantAdapter;
import com.creasmit.bipgo.adapter.DriverAdapter;
import com.creasmit.bipgo.adapter.StructureAdapter;
import com.creasmit.bipgo.adapter.VehiculeAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.dao.IDaoAssistant;
import com.creasmit.bipgo.dao.IDaoCompte;
import com.creasmit.bipgo.dao.IDaoDriver;
import com.creasmit.bipgo.dao.IDaoGerant;
import com.creasmit.bipgo.dao.IDaoIdentite;
import com.creasmit.bipgo.dao.IDaoStructure;
import com.creasmit.bipgo.dao.IDaoVehicule;
import com.creasmit.bipgo.dao.IDaoWallet;
import com.creasmit.bipgo.entity.Assistant;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Driver;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Wallet;
import java.util.Date;
import java.util.List;
import one.creas.emalib.util.Log;
import one.creas.emalib.util.QueryParam;
import one.creas.emalib.util.Utils;
import one.creas.emalib.util.ValueValidateException;

/**
 *
 * @author georges
 */
public class StructureController implements IStructureController {

    private IDaoStructure daoStructure;
    private IDaoGerant daoGerant;
    private IDaoDriver daoDriver;
    private IDaoAssistant daoAssistant;
    private IDaoVehicule daoVehicule;
    private IDaoIdentite daoIdentite;
    private IDaoWallet daoWallet;
    private IDaoCompte daoCompte;

    public StructureController() {
    }

    public void setDaoStructure(IDaoStructure daoStructure) {
        this.daoStructure = daoStructure;
    }

    public void setDaoGerant(IDaoGerant daoGerant) {
        this.daoGerant = daoGerant;
    }

    public void setDaoDriver(IDaoDriver daoDriver) {
        this.daoDriver = daoDriver;
    }

    public void setDaoAssistant(IDaoAssistant daoAssistant) {
        this.daoAssistant = daoAssistant;
    }

    public void setDaoVehicule(IDaoVehicule daoVehicule) {
        this.daoVehicule = daoVehicule;
    }

    public void setDaoIdentite(IDaoIdentite daoIdentite) {
        this.daoIdentite = daoIdentite;
    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    @Override
    public List<StructureAdapter> listing() {
        return this.daoStructure.getStructures();
    }

    @Override
    public Structure add(Structure structure) {

        //creating wallet for structure
        Wallet w = new Wallet();
        try {
            w.setBipid(5 + Utils.generatePin(6));
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
            structure.setFkWallet(walletSaved.getId());
            Compte compte = new Compte();
            compte.setFkDevise(1);
            compte.setMontant(0.0);
            Compte compteSaved = daoCompte.add(compte);
            if (compteSaved != null) {
                WalletAdapter walletAdapter = new WalletAdapter();
                walletAdapter.setWallet(walletSaved);
                
                structure.setDateCreate(new Date());
                structure.setFkWallet(walletSaved.getId());
                return this.daoStructure.add(structure);
            } else {
                daoWallet.delete(walletSaved);
            }
        } else {
            daoWallet.delete(walletSaved);
        }
        return null;
    }

    @Override
    public Structure update(Structure structure) {
        structure.setDateModif(new Date());
        return this.daoStructure.update(structure);
    }

    @Override
    public Structure delete(Structure structure) {

        List<DriverAdapter> driverAdapters = this.daoDriver.getDrivers(structure);
        for (DriverAdapter driverAdapter : driverAdapters) {
            Driver driver = driverAdapter.getDriver();
            Identite identite = driverAdapter.getIdentite();

            Driver driverDeleted = this.daoDriver.delete(driver);
            if (driverDeleted != null) {
                daoIdentite.delete(identite);
            }
        }

        List<AssistantAdapter> assistantAdapters = this.daoAssistant.getAssistant(structure);
        for (AssistantAdapter assistantAdapter : assistantAdapters) {
            Assistant assistant = assistantAdapter.getAssistant();
            Identite identite = assistantAdapter.getIdentite();

            Assistant assistantDeleted = this.daoAssistant.delete(assistant);
            if (assistantDeleted != null) {
                daoIdentite.delete(identite);
            }
        }

        List<VehiculeAdapter> vehiculeAdapters = this.daoVehicule.getVehicule(structure);
        for (VehiculeAdapter vehiculeAdapter : vehiculeAdapters) {
            this.daoVehicule.delete(vehiculeAdapter.getVehicule());
        }

        return this.daoStructure.delete(structure);
    }

    @Override
    public List<StructureAdapter> listing(String denomination) {
        return this.daoStructure.find(new QueryParam("s.denomination", denomination));
    }

    @Override
    public List<StructureAdapter> getStructure(int id) {
        return this.daoStructure.getStructures(new QueryParam("s.id", id));
    }

}
