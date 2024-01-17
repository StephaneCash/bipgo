package com.creasmit.bipgo.adapter;

import com.creasmit.bipgo.entity.ConfirmationCompte;
import com.creasmit.bipgo.entity.Driver;
import com.creasmit.bipgo.entity.Identite;
import com.creasmit.bipgo.entity.Structure;
import com.creasmit.bipgo.entity.Vehicule;

/**
 *
 * @author Stéphane_ZEUS
 */
public class DriverAdapter {

    private Driver driver;
    private Identite identite;
    private Structure structure;
    private Vehicule vehicule;
    private WalletAdapter walletAdapter;
    private ConfirmationCompte confirmationCompte;

    public DriverAdapter() {
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Identite getIdentite() {
        return identite;
    }

    public void setIdentite(Identite identite) {
        this.identite = identite;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public WalletAdapter getWalletAdapter() {
        return walletAdapter;
    }

    public void setWalletAdapter(WalletAdapter walletAdapter) {
        this.walletAdapter = walletAdapter;
    }

    public ConfirmationCompte getConfirmationCompte() {
        return confirmationCompte;
    }

    public void setConfirmationCompte(ConfirmationCompte confirmationCompte) {
        this.confirmationCompte = confirmationCompte;
    }

}
