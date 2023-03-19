package com.fit3077.a2.service;

import com.fit3077.a2.model.Testing;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class TestingService extends BaseService implements Service{
    private String testUrl="https://fit3077.com/api/v2/covid-test";

    private static TestingService instance;
    private ArrayList<Testing> testings = new ArrayList(100);

    private TestingService(){
        getAll();
    }

    public static Service getInstance(){
        if (instance == null){
            instance = new TestingService();
        }
        return instance;
    }

    @Override
    // get all the testing objects and store them into arraylist
    public void getAll(){
        HttpResponse<String> response = getRequest(testUrl);
        JSONArray testObj = new JSONArray(response.body());
        for(int i = 0; i < testObj.length(); i++){
            testings.add(new Testing(testObj.getJSONObject(i)));
        }
    }

    public boolean createTesting(String type, String patientId, String administererId, String result, String status, String notes){
        Testing testing = new Testing();
        testing.setType(type);
        testing.setPatientId(patientId);
        testing.setAdministererId(administererId);
        testing.setResult(result);
        testing.setStatus(status);
        testing.setNotes(notes);
        return true;
    }

    @Override
    // create a testing object
    public HttpResponse post(JSONObject obj){
        return postRequest(testUrl, obj);
    }

    @Override
    // get a testing object by its id
    public Object getById(String id){
        for (int i = 0; i < testings.size(); i++) {
            if (testings.get(i).getTestingId().equals(id)) {
                return testings.get(i);
            }
        }
        return null;
    }

    @Override
    // update a specific testing object
    public boolean patch(String id, JSONObject obj){
        String url = testUrl + "/" + id;
        return patchRequest(url, obj);
    }

    //we don't delete tests
    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public ArrayList getList() {
        return testings;
    }
}
