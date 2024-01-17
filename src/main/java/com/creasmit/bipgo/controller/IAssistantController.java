/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.AssistantAdapter;
import com.creasmit.bipgo.entity.Assistant;
import com.creasmit.bipgo.entity.Structure;
import java.util.List;

/**
 *
 * @author Christ Mantima
 */
public interface IAssistantController {
    
    public List<AssistantAdapter> getAssistantByStructure (Structure structure);
    
    public AssistantAdapter add(AssistantAdapter assistantAdapter);
    
    public AssistantAdapter update (AssistantAdapter assitantAdapter);
    
    public AssistantAdapter delete (Assistant assistant);
    
}
