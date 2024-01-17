package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.CompteAdapter;
import com.creasmit.bipgo.entity.Compte;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public interface IDaoCompte {
    
    public List<CompteAdapter> getCompte(QueryParam... queryParams);

    public Compte add(Compte compte);

    public Compte update(Compte compte);

    public Compte delete(Compte compte);

    
    
}
