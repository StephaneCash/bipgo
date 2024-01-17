/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creasmit.bipgo.api;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import one.creas.emalib.util.Log;

/**
 *
 * @author emmanueltombo
 */
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    public static String token;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String xAuth = requestContext.getHeaderString("Authorization");
        if (xAuth != null) {
            String token = xAuth.split(" ")[0];
            Log.i(this, token);
        }
        
        

        String subject = requestContext.getHeaderString("Subject");
        if (subject != null) {
            Log.i(this, subject);
        }

    }

}
