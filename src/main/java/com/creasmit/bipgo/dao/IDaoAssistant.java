/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.AssistantAdapter;
import com.creasmit.bipgo.entity.Assistant;
import com.creasmit.bipgo.entity.Structure;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author Christ Mantima
 */
public interface IDaoAssistant {
    
    public List<AssistantAdapter> getAssistant (Structure structure);
    
    public List<AssistantAdapter> getAssistant (QueryParam... queryParams);
    
    public Assistant add(Assistant assistant);
    
    public Assistant update(Assistant assistant);
    
    public Assistant delete (Assistant assistant);
     
}
