/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.TransactionAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.callback.TransactionCallback;
import com.creasmit.bipgo.entity.Devise;
import com.creasmit.bipgo.entity.Transaction;
import com.creasmit.bipgo.entity.TypeOperation;
import com.creasmit.bipgo.entity.Wallet;
import com.creasmit.bipgo.model.AmountReport;
import java.util.List;

/**
 *
 * @author georges
 */
public interface ITransactionController {

    public List<TransactionAdapter> listing();

    public List<TransactionAdapter> listing(String dateFrom, String dateTo);

    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation);

    public List<TransactionAdapter> listing(String dateFrom, String dateTo, Devise devise);

    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation, Devise devise);

    public List<TransactionAdapter> listing(Transaction t);

    public void init(WalletAdapter from, WalletAdapter to, Transaction transaction,TransactionCallback  callback);

    public Transaction update(Transaction transaction);

    public AmountReport amountReport(Devise d, String dateFrom, String dateTo);

    public AmountReport amountReport(TypeOperation typeOperation, Devise d);
    
     public AmountReport amountReport(Wallet wallet,TypeOperation typeOperation, Devise d,String dateFrom,String dateTo);

}
