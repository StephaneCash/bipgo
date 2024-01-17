/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.adapter.AgentAdapter;
import com.creasmit.bipgo.entity.Agent;
import com.creasmit.bipgo.entity.TypeAgent;
import java.util.List;

/**
 *
 * @author georges
 */
public interface IAgentController {

    public List<AgentAdapter> listAgent(TypeAgent typeAgent);

    public AgentAdapter get(TypeAgent typeAgent);
    
    public AgentAdapter activeCompte(Agent agent);

    public AgentAdapter add(AgentAdapter agentAdapter);

    public AgentAdapter update(AgentAdapter agentAdapter);

    public AgentAdapter delete(Agent agent);

}
