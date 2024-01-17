// Signifie le package dans lequel on travail
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.DriverAdapter;
import com.creasmit.bipgo.entity.Driver;
import com.creasmit.bipgo.entity.Structure;
import java.util.List;
import one.creas.emalib.util.QueryParam;

public interface IDaoDriver {

    public List<DriverAdapter> getDrivers(Structure structure);

    public List<DriverAdapter> getDrivers(QueryParam... queryParams);

    public Driver add(Driver driver);

    public Driver update(Driver driver);

    public Driver delete(Driver driver);

    public Long delete(QueryParam... queryParams);

}
