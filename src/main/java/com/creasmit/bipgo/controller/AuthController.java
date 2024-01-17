package com.creasmit.bipgo.controller;

import com.creasmit.bipgo.model.Profil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import one.creas.emalib.http.HttpResponse;
import one.creas.emalib.util.Log;

/**
 *
 * @author emmanueltombo
 */
public class AuthController implements IAuthController {

    private String url = "http://localhost:8080/auth0/";
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;

    public AuthController() {
          client = new OkHttpClient();
          client.setReadTimeout(60, TimeUnit.SECONDS);
          client.setConnectTimeout(60, TimeUnit.SECONDS);
          client.setWriteTimeout(60, TimeUnit.SECONDS);

    }
    
    

    @Override
    public HttpResponse getProfils() {
        Request request = new Request.Builder()
                .url(url + "/profiles")
                .build();
        try {
            Response response = client.newCall(request).execute();
            HttpResponse hr = new Gson().fromJson(response.body().string(), new TypeToken<HttpResponse>() {
            }.getType());
            return hr;
        } catch (IOException ex) {
            Log.i(this, ex);
        }
        return null;
    }

    @Override
    public HttpResponse verifyToken(String token) {

        JsonObject tokenJson = new JsonObject();
        tokenJson.addProperty("token", token);

        RequestBody body = RequestBody.create(JSON, tokenJson.getAsString());
        Request request = new Request.Builder()
                .url(url + "/verifyToken")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            HttpResponse hr = new Gson().fromJson(response.body().string(), new TypeToken<HttpResponse>() {
            }.getType());
            return hr;
        } catch (IOException ex) {
            Log.i(this, ex);
        }
        return null;
    }

    @Override
    public HttpResponse login(Profil profil) {
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(profil));
        Request request = new Request.Builder()
                .url(url + "auth/login")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            HttpResponse hr = new Gson().fromJson(response.body().string(), new TypeToken<HttpResponse>() {
            }.getType());
            return hr;
        } catch (IOException ex) {
            Log.i(this, ex);
        }
        return null;
    }

    @Override
    public HttpResponse signup(Profil profil) {
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(profil));
        Request request = new Request.Builder()
                .url(url + "auth/signup")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            HttpResponse<Profil> hr = new Gson().fromJson(responseData, new TypeToken<HttpResponse<Profil>>() {
            }.getType());
            return hr;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public HttpResponse editProfil(Profil profil) {
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(profil));
        Request request = new Request.Builder()
                .url(url + "/editProfil")
                .put(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            HttpResponse hr = new Gson().fromJson(response.body().string(), new TypeToken<HttpResponse>() {
            }.getType());
            return hr;
        } catch (IOException ex) {
            Log.i(this, ex);
        }
        return null;
    }

    @Override
    public HttpResponse enableProfil(Profil profil) {
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(profil));
        Request request = new Request.Builder()
                .url(url + "/enableProfil")
                .put(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            HttpResponse hr = new Gson().fromJson(response.body().string(), new TypeToken<HttpResponse>() {
            }.getType());
            return hr;
        } catch (IOException ex) {
            Log.i(this, ex);
        }
        return null;
    }

    @Override
    public HttpResponse disableProfil(Profil profil) {
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(profil));
        Request request = new Request.Builder()
                .url(url + "/disableProfil")
                .put(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            HttpResponse hr = new Gson().fromJson(response.body().string(), new TypeToken<HttpResponse>() {
            }.getType());
            return hr;
        } catch (IOException ex) {
            Log.i(this, ex);
        }
        return null;
    }

    @Override
    public HttpResponse deleteProfil(Profil profil) {
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(profil));
        Request request = new Request.Builder()
                .url(url + "/disableProfil")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            HttpResponse hr = new Gson().fromJson(response.body().string(), new TypeToken<HttpResponse>() {
            }.getType());
            return hr;
        } catch (IOException ex) {
            Log.i(this, ex);
        }
        return null;
    }

    @Override
    public HttpResponse confirm_signup(String pin) {
        RequestBody body = RequestBody.create(null, new byte[0]);
        Request request = new Request.Builder()
                .url(url + "auth/confirm_signup/"+pin)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.i(this, responseData);
            HttpResponse<Profil> hr = new Gson().fromJson(responseData, new TypeToken<HttpResponse<Profil>>() {
            }.getType());
            return hr;
        } catch (Exception ex) {
            Log.i(this, ex);
            ex.printStackTrace();
        }
        return null;
    }

}
