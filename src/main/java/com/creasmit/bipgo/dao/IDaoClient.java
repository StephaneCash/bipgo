package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.ClientAdapter;
import com.creasmit.bipgo.entity.Client;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author CREASMIT_ZEUS
 */
public interface IDaoClient {

    public List<ClientAdapter> getClients(QueryParam... queryParams);
    
    public List<ClientAdapter> getClients();

    public Client add(Client client);

    public Client update(Client client);

    public Client delete(Client client);

}
