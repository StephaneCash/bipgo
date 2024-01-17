/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.model.Profil;
import one.creas.emalib.http.HttpResponse;

/**
 *
 * @author emmanueltombo
 */
public interface IAuthController {

    public HttpResponse getProfils();

    public HttpResponse login(Profil profil);
    
    public HttpResponse verifyToken(String token);

    public HttpResponse signup(Profil profil);
    
    public HttpResponse confirm_signup(String pin);

    public HttpResponse editProfil(Profil profil);

    public HttpResponse enableProfil(Profil profil);

    public HttpResponse disableProfil(Profil profil);

    public HttpResponse deleteProfil(Profil profil);
}
