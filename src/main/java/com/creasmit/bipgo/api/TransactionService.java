/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import com.creasmit.bipgo.adapter.InitTransactionAdapter;
import com.creasmit.bipgo.adapter.TransactionAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.callback.TransactionCallback;
import com.creasmit.bipgo.controller.ITransactionController;
import com.creasmit.bipgo.controller.IWalletController;
import com.creasmit.bipgo.entity.Devise;
import com.creasmit.bipgo.entity.Transaction;
import com.creasmit.bipgo.entity.TypeOperation;
import com.creasmit.bipgo.entity.Wallet;
import com.creasmit.bipgo.model.AmountReport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import one.creas.emalib.http.HttpResponse;
import one.creas.emalib.util.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author georges
 */
@Path("/transaction")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionService {

    private ITransactionController controller;
    private IWalletController walletController;
    private String transactionResponse;

    public TransactionService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.controller = (ITransactionController) context.getBean("transactionController");
        this.walletController = (IWalletController) context.getBean("walletController");
    }

    // listing des Transaction
    @GET
    public String listing() {
        List<TransactionAdapter> transaction = this.controller.listing();
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(transaction)
                .toJSON();
        return reponse;
    }

    // listing des Transaction  dateFrom,  dateTo
    @GET
    @Path("/{dateFrom}/{dateTo}")
    public String listing(@PathParam("dateFrom") String dateFrom, @PathParam("dateTo") String dateTo) {

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            date = sdf.parse(dateFrom);
        } catch (ParseException ex) {

        }

        try {
            SimpleDateFormat da = new SimpleDateFormat("dd/MM/yy");
            date = da.parse(dateTo);
        } catch (ParseException ex) {

        }
        List<TransactionAdapter> ta = this.controller.listing(dateFrom, dateTo);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(ta)
                .toJSON();
        return reponse;
    }

    // listing des Transaction dateFrom, dateTo, typeOperation
    @GET
    @Path("/byTypeOperation/{dateFrom}/{dateTo}/{typeOperation}")
    public String listingByTypeOperation(@PathParam("dateFrom") String dateFrom, @PathParam("dateTo") String dateTo, @PathParam("typeOperation") String typeOperation) {

        TypeOperation to = new TypeOperation();
        to.setDescription(typeOperation);

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            date = sdf.parse(dateFrom);
        } catch (ParseException ex) {

        }

        try {
            SimpleDateFormat da = new SimpleDateFormat("dd/MM/yy");
            date = da.parse(dateTo);
        } catch (ParseException ex) {

        }

        List<TransactionAdapter> tra = this.controller.listing(dateFrom, dateTo, to);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(tra)
                .toJSON();
        return reponse;
    }

    @GET
    @Path("/byDevise/{dateFrom}/{dateTo}/{devise}")
    public String listingByDevise(@PathParam("dateFrom") String dateFrom, @PathParam("dateTo") String dateTo, @PathParam("devise") String d) {

        Devise devise = new Devise();
        devise.setLibelle(d);

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            date = sdf.parse(dateFrom);
        } catch (ParseException ex) {

        }

        try {
            SimpleDateFormat da = new SimpleDateFormat("dd/MM/yy");
            date = da.parse(dateTo);
        } catch (ParseException ex) {

        }

        List<TransactionAdapter> tra = this.controller.listing(dateFrom, dateTo, devise);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(tra)
                .toJSON();
        return reponse;
    }

    // listing des Transaction dateFrom, dateTo,typeOperation, devise
    @GET
    @Path("/byAll/{dateFrom}/{dateTo}/{typeOperation}/{devise}")
    public String listingByAll(@PathParam("dateFrom") String dateFrom, @PathParam("dateTo") String dateTo, @PathParam("typeOperation") String to, @PathParam("devise") String d) {

        TypeOperation typeOperation = new TypeOperation();
        typeOperation.setDescription(to);

        Devise devise = new Devise();
        devise.setLibelle(d);

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            date = sdf.parse(dateFrom);
        } catch (ParseException ex) {

        }

        try {
            SimpleDateFormat da = new SimpleDateFormat("dd/MM/yy");
            date = da.parse(dateTo);
        } catch (ParseException ex) {

        }

        List<TransactionAdapter> tra = this.controller.listing(dateFrom, dateTo, typeOperation, devise);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(tra)
                .toJSON();
        return reponse;
    }

    // listing of Transaction 
    @GET
    @Path("/byTransaction/{transactionId}")
    public String listingbyTransaction(@PathParam("transaction") int transactionId) {

        Transaction ref = new Transaction();
        ref.setFkTypeOperation(transactionId);

        List<TransactionAdapter> transaction = this.controller.listing(ref);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(transaction)
                .toJSON();
        return reponse;
    }

    //amountReport of critere typeOperation devise
    @GET
    @Path("/report/{typeOperation}/{devise}")
    public String amountReport(@PathParam("devise") String d, @PathParam("typeOperation") int typeOperationid) {
        Devise devise = new Devise();
        devise.setLibelle(d);
        TypeOperation typeOperation = new TypeOperation();
        typeOperation.setId(typeOperationid);
        AmountReport a = this.controller.amountReport(typeOperation, devise);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(a)
                .toJSON();
        return reponse;
    }

    //amountReport of critere  devise,dateFrom,dateTo
    @GET
    @Path("/report/{devise}/{dateFrom}/{dateTo}")
    public String amountReport(@PathParam("devise") String d, @PathParam("dateFrom") String dateFrom, @PathParam("dateTo") String dateTo) {
        Devise devise = new Devise();
        devise.setLibelle(d);

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            date = sdf.parse(dateFrom);
        } catch (ParseException ex) {

        }

        try {
            SimpleDateFormat da = new SimpleDateFormat("dd/MM/yy");
            date = da.parse(dateTo);
        } catch (ParseException ex) {

        }
        AmountReport ta = this.controller.amountReport(devise, dateFrom, dateTo);
        String reponse = HttpResponse.build()
                .setStatus("200")
                .setResponse(ta)
                .toJSON();
        return reponse;
    }

    // Create Transaction
    @POST
    public String add(String data) {

        Log.i(this, data);
        if (data != null) {

            InitTransactionAdapter ita = new Gson().fromJson(data, new TypeToken<InitTransactionAdapter>() {
            }.getType());

            Transaction t = ita.getTransaction();
            t.setFkTypeOperation(2);
            Wallet walletExp = ita.getWallet();

            /**
             * getting access from sender account with its credentials
             */
            WalletAdapter wallet = this.walletController.get(walletExp);
            if (wallet != null) {

                //checking recipient account whether is valid 
                WalletAdapter walletBen = this.walletController.get(t.getBen());
                if (walletBen != null) {

                    /**
                     * Processing transaction
                     */
                    this.controller.init(wallet, walletBen, t, new TransactionCallback() {
                        @Override
                        public void onSucceed(Object object) {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("200")
                                    .setMessage("Success")
                                    .toJSON();
                        }

                        @Override
                        public void onInsufficientAmount() {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("400")
                                    .setMessage("Impossible d'exécuter la transaction, montant insuffisant")
                                    .toJSON();
                        }

                        @Override
                        public void onFailed() {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("400")
                                    .setMessage("Echec d'opération")
                                    .toJSON();
                        }
                    });

                    return transactionResponse;
                } else {
                    Log.i(this, "benef invalid");
                    return HttpResponse.build()
                            .setStatus("400")
                            .setMessage("Bénéficiaire invalide")
                            .toJSON();
                }

            } else {
                return HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Pin invalide")
                        .toJSON();
            }

        }
        return HttpResponse.build()
                .setStatus("400")
                .setMessage("Erreur d'iniialisation de la Transaction")
                .toJSON();
    }

    // Create Transaction
    @POST
    @Path("/payement")
    public String payement(String data) {

        Log.i(this, data);
        if (data != null) {

            InitTransactionAdapter ita = new Gson().fromJson(data, new TypeToken<InitTransactionAdapter>() {
            }.getType());

            Transaction t = ita.getTransaction();
            t.setFkTypeOperation(4);
            Wallet walletExp = ita.getWallet();

            /**
             * getting access from sender account with its credentials
             */
            WalletAdapter wallet = this.walletController.get(walletExp.getBipid());
            Log.i(this, new Gson().toJson(wallet));
            if (wallet != null) {

                //checking recipient account whether is valid 
                WalletAdapter walletBen = this.walletController.get(t.getBen());
                if (walletBen != null) {

                    /**
                     * Processing transaction
                     */
                    this.controller.init(wallet, walletBen, t, new TransactionCallback() {
                        @Override
                        public void onSucceed(Object object) {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("200")
                                    .setMessage("Paiement effectué")
                                    .toJSON();
                        }

                        @Override
                        public void onInsufficientAmount() {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("400")
                                    .setMessage("Paiement impossible, montant insuffisant")
                                    .toJSON();
                        }

                        @Override
                        public void onFailed() {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("400")
                                    .setMessage("Echec d'opération")
                                    .toJSON();
                        }
                    });

                    return transactionResponse;
                } else {
                    Log.i(this, "benef invalid");
                    return HttpResponse.build()
                            .setStatus("400")
                            .setMessage("Bénéficiaire invalide")
                            .toJSON();
                }

            } else {
                return HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Carte invalide")
                        .toJSON();
            }
        }
        return HttpResponse.build()
                .setStatus("400")
                .setMessage("Erreur d'iniialisation de la Transaction")
                .toJSON();
    }

    //Pay back
    @POST
    @Path("/payementBack")
    public String payementBack(String data) {

        Log.i(this, data);
        if (data != null) {

            InitTransactionAdapter ita = new Gson().fromJson(data, new TypeToken<InitTransactionAdapter>() {
            }.getType());

            Transaction t = ita.getTransaction();
            t.setFkTypeOperation(3);
            Wallet walletExp = ita.getWallet();

            /**
             * getting access from sender account with its credentials
             */
            WalletAdapter wallet = this.walletController.get(walletExp);
            Log.i(this, new Gson().toJson(wallet));
            if (wallet != null) {

                //checking recipient account whether is valid 
                WalletAdapter walletBen = this.walletController.get(t.getBen());
                if (walletBen != null) {

                    /**
                     * Processing transaction
                     */
                    this.controller.init(wallet, walletBen, t, new TransactionCallback() {
                        @Override
                        public void onSucceed(Object object) {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("200")
                                    .setMessage("Remoursement effectué")
                                    .toJSON();
                        }

                        @Override
                        public void onInsufficientAmount() {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("400")
                                    .setMessage("Paiement impossible, montant insuffisant")
                                    .toJSON();
                        }

                        @Override
                        public void onFailed() {
                            transactionResponse = HttpResponse.build()
                                    .setStatus("400")
                                    .setMessage("Echec d'opération")
                                    .toJSON();
                        }
                    });

                    return transactionResponse;
                } else {
                    Log.i(this, "benef invalid");
                    return HttpResponse.build()
                            .setStatus("400")
                            .setMessage("Bénéficiaire invalide")
                            .toJSON();
                }

            } else {
                return HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Pin invalide")
                        .toJSON();
            }
        }
        return HttpResponse.build()
                .setStatus("400")
                .setMessage("Erreur d'iniialisation de la Transaction")
                .toJSON();
    }

    //Pay back
    @POST
    @Path("/payementReport")
    public String payementReport(String data) {
        Log.i(this, data);
        Wallet wallet = new Gson().fromJson(data, new TypeToken<Wallet>() {
        }.getType());
        
        String dateFrom=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Log.i(this, dateFrom);
        
        AmountReport amountReport=this.controller.amountReport(wallet,new TypeOperation(4),new Devise(1), dateFrom,dateFrom);
        
        return HttpResponse.build()
                .setStatus("200")
                .setMessage("")
                .setResponse(amountReport)
                .toJSON();
    }

    //update the Price
    @PUT
    public String update(String data) {
        String response = null;
        Log.i(this, data);
        if (!data.isEmpty()) {
            Transaction transaction = new Gson().fromJson(data, new TypeToken<Transaction>() {
            }.getType());
            Transaction t = this.controller.update(transaction);
            if (t != null) {
                response = HttpResponse.build()
                        .setStatus("200")
                        .setResponse(t)
                        .toJSON();
            } else {
                response = HttpResponse.build()
                        .setStatus("400")
                        .setMessage("Erreur de modification de Transaction ")
                        .toJSON();
            }
        } else {
            response = HttpResponse.build()
                    .setStatus("400")
                    .setMessage("Erreur de modification Transaction avec la valeur null")
                    .toJSON();
        }
        return response;

    }
}
