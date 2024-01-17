/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.dao;

import com.creasmit.bipgo.adapter.AgentAdapter;
import com.creasmit.bipgo.entity.Agent;
import com.creasmit.bipgo.entity.TypeAgent;
import java.util.List;
import one.creas.emalib.util.QueryParam;

/**
 *
 * @author georges
 */
public interface IDaoAgent {
    

    public List<AgentAdapter> getAgents(QueryParam... queryParams);
    
     public AgentAdapter getAgentCode(Agent a);

    public Agent add(Agent agent);

    public Agent update(Agent agent);

    public Agent delete(Agent agent);

}



