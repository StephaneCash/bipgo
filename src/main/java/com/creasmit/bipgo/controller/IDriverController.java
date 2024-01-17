
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.DriverAdapter;
import com.creasmit.bipgo.entity.Driver;
import com.creasmit.bipgo.entity.Structure;
import java.util.List;

public interface IDriverController {
    
    public List<DriverAdapter> getDriverByStructure(Structure structure);
    
    public DriverAdapter add(DriverAdapter driverAdapter);
    
    public DriverAdapter update(DriverAdapter DriverAdapter);
    
    public DriverAdapter delete(Driver driver);
}
