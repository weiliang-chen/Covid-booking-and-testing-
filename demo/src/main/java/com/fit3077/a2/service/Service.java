package com.fit3077.a2.service;

import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public interface Service {

    public void getAll();

    public Object getById(String id);

    public HttpResponse post(JSONObject obj);

    public boolean patch(String id, JSONObject obj);

    public boolean delete(String id);

    public ArrayList getList();

}
