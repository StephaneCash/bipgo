package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.VehiculeAdapter;
import com.creasmit.bipgo.dao.IDaoCompte;
import com.creasmit.bipgo.dao.IDaoVehicule;
import com.creasmit.bipgo.dao.IDaoWallet;
import com.creasmit.bipgo.entity.Compte;
import com.creasmit.bipgo.entity.Pos;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;
import com.creasmit.bipgo.entity.Wallet;
import java.util.List;
import one.creas.emalib.util.QueryParam;
import one.creas.emalib.util.Utils;
import one.creas.emalib.util.ValueValidateException;

/**
 *
 * @author Christ Mantima
 */
public class VehiculeController implements IVehiculeController {

    private IDaoVehicule daoVehicule;
    private IDaoWallet daoWallet;
    private IDaoCompte daoCompte;

    public VehiculeController() {
    }

    public void setDaoVehicule(IDaoVehicule daoVehicule) {
        this.daoVehicule = daoVehicule;
    }

    public IDaoWallet getDaoWallet() {
        return daoWallet;
    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }

    public IDaoCompte getDaoCompte() {
        return daoCompte;
    }

    public void setDaoCompte(IDaoCompte daoCompte) {
        this.daoCompte = daoCompte;
    }

    @Override
    public List<VehiculeAdapter> getVehiculeByStructure(Structure structure) {
        return this.daoVehicule.getVehicule(structure);
    }

    @Override
    public VehiculeAdapter getVehiculeByIdPos(Pos pos) {

        List<VehiculeAdapter> vas = this.daoVehicule.getVehicule(new QueryParam("v.fkPos", pos.getId()));

        if (!vas.isEmpty()) {
            return vas.get(0);
        }
        return null;
    }

    @Override
    public Vehicule add(Vehicule vehicule) {
        Vehicule v = this.daoVehicule.add(vehicule);
        try {

            if (v != null) {
                Wallet wallet = new Wallet();
                wallet.setBipid(7 + Utils.generatePin(6));
                try {
                    wallet.setPin(Utils.generatePin(6));
                } catch (ValueValidateException ex) {
                    wallet.setPin("123456");
                }

                Wallet walletCreated = this.daoWallet.add(wallet);
                if (walletCreated != null) {
                    Compte compte = new Compte();
                    compte.setFkDevise(1);
                    compte.setMontant(0.0);
                    compte.setFkWallet(walletCreated.getId());
                    Compte compteCreated = this.daoCompte.add(compte);
                    if (compteCreated != null) {
                        String code = v.getId() + "" + Utils.generatePin(4);
                        v.setCodeActivationTerminal(code);
                        v.setFkWallet(walletCreated.getId());
                        Vehicule vm = this.daoVehicule.update(v);
                        return vm;
                    } else {
                        this.daoWallet.delete(walletCreated);
                        this.daoVehicule.delete(v);
                    }
                }

            }
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    public Vehicule update(Vehicule vehicule) {
        return this.daoVehicule.update(vehicule);
    }

    @Override
    public Vehicule delete(Vehicule vehicule) {
        return this.daoVehicule.delete(vehicule);
    }

    @Override
    public VehiculeAdapter activeCompte(Vehicule vehicule) {
        List<VehiculeAdapter> vas = this.daoVehicule.getVehicule(new QueryParam("v.codeActivationTerminal", vehicule.getCodeActivationTerminal()));

        if (!vas.isEmpty()) {
            return vas.get(0);
        }
        return null;
    }

}
