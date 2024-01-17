/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.ClientAdapter;
import com.creasmit.bipgo.entity.Client;
import java.util.List;

/**
 *
 * @author CREASMIT_ZEUS
 */
public interface IClientController {
    public List<ClientAdapter> listClient();

    public ClientAdapter getClient();

    public ClientAdapter add(ClientAdapter clientAdapter);

    public ClientAdapter update(ClientAdapter clientAdapter);

    public ClientAdapter delete(Client client);
}
