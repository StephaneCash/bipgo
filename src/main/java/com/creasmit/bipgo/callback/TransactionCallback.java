/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.callback;

/**
 *
 * @author emmanueltombo
 */
public interface TransactionCallback {
    public void onSucceed(Object object);
    public void onInsufficientAmount();
    public void onFailed();
}
