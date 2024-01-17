/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.TransactionAdapter;
import com.creasmit.bipgo.adapter.WalletAdapter;
import com.creasmit.bipgo.callback.TransactionCallback;
import com.creasmit.bipgo.entity.Devise;
import com.creasmit.bipgo.entity.Transaction;
import com.creasmit.bipgo.entity.TypeOperation;
import com.creasmit.bipgo.entity.Wallet;
import com.creasmit.bipgo.model.AmountReport;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public interface IDaoTransaction {

    public List<TransactionAdapter> listing();

    public List<TransactionAdapter> listing(QueryParam... queryParams);

    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation);

    public List<TransactionAdapter> listing(String dateFrom, String dateTo, Devise devise);

    public List<TransactionAdapter> listing(String dateFrom, String dateTo, TypeOperation typeOperation, Devise devise);

    public void initTransaction(WalletAdapter from, WalletAdapter to, Transaction transaction,TransactionCallback transactionCallback);

    public Transaction update(Transaction transanction);

    public AmountReport amountReport(Wallet wallet,TypeOperation typeOperation,Devise devise,String date);

}
