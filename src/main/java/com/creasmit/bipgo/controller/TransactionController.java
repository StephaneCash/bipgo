
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.TransactionAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.callback.TransactionCallback;
import com.creasmit.bipgo.dao.IDaoTransaction;
import com.creasmit.bipgo.dao.IDaoWallet;
import com.creasmit.bipgo.entity.Devise;
import com.creasmit.bipgo.entity.Transaction;
import com.creasmit.bipgo.entity.TypeOperation;
import com.creasmit.bipgo.entity.Wallet;
import com.creasmit.bipgo.model.AmountReport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import one.creas.emalib.util.EnDecrypt;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public class TransactionController implements ITransactionController {

    private IDaoTransaction daoTransaction;
    private IDaoWallet daoWallet;

    public TransactionController() {
    }

    public void setDaoTransaction(IDaoTransaction daoTransaction) {
        this.daoTransaction = daoTransaction;
    }

    public void setDaoWallet(IDaoWallet daoWallet) {
        this.daoWallet = daoWallet;
    }
    
    @Override
    public List<TransactionAdapter> listing() {
        return this.daoTransaction.listing();
    }

    @Override
    public List<TransactionAdapter> listing(String dateFrom, String dateTo) {
        return daoTransaction.listing(new QueryParam("dateFrom", dateFrom), new QueryParam("dateTo", dateTo));

    }

    @Override
    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation) {
        return daoTransaction.listing(dateFrom, dateTo, typeOperation);
    }

    @Override
    public List<TransactionAdapter> listing(String dateFrom, String dateTo, Devise devise) {
        return daoTransaction.listing(dateFrom, dateTo, devise);

    }

    @Override
    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation, Devise devise) {
        return daoTransaction.listing(dateFrom, dateTo, typeOperation, devise);
    }

    @Override
    public List<TransactionAdapter> listing(Transaction t) {
        return daoTransaction.listing();
    }

    @Override
    public void init(WalletAdapter from,WalletAdapter to,Transaction transaction,TransactionCallback transactionCallback) {
        //setup ref number
        SimpleDateFormat format = new SimpleDateFormat("DDMMYYYYHHmmss");
        String str = format.format(new Date());
        String ref = EnDecrypt.encrypt(str).replace(":", "").replace(".", "");
        
        transaction.setRef(ref);
        transaction.setDate(new Date());
        transaction.setFkDevise(1);
        transaction.setFkStatutTransaction(1);
        this.daoTransaction.initTransaction(from, to, transaction, transactionCallback);

    }

    @Override
    public Transaction update(Transaction transaction) {
        return this.daoTransaction.update(transaction);
    }

    @Override
    public AmountReport amountReport(Devise d, String dateFrom, String dateTo) {
        return null;
    }

    @Override
    public AmountReport amountReport(TypeOperation typeOperation, Devise d) {
        return null;
    }

    @Override
    public AmountReport amountReport(Wallet wallet,TypeOperation typeOperation, Devise d, String dateFrom, String dateTo) {
         return daoTransaction.amountReport(wallet,typeOperation,d,dateFrom);
    }

}
