package com.fit3077.a2.model;

import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    protected String id;
    protected String givenName;
    protected String familyName;
    protected String userName;
    protected boolean hasCovidTest;
    protected boolean hasStrongSymptoms;
    protected ArrayList<Booking> historyBooking = new ArrayList<>(100);

    // this constructor is to create a new user object and post to the web service
    public User(){
    }

    // this constructor is used to create an existed user object from the web Service
    public User(JSONObject obj){
        this.id = obj.getString("id");
        this.userName = obj.getString("userName");
        this.givenName = obj.getString("givenName");
        this.familyName = obj.getString("familyName");
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public boolean hasCovidTest()
    {
        return hasCovidTest;
    }

    public boolean hasStrongSymptoms()
    {
        return hasStrongSymptoms;
    }

    public JSONObject create(){
        JSONObject obj = new JSONObject();
        obj.put("givenName",this.givenName);
        obj.put("familyName",this.familyName);
        return obj;
    }

    public void addHistoryBooking(Booking booking) {
        historyBooking.add(booking);
    }

    public ArrayList<Booking> getHistoryBooking() {
        return historyBooking;
    }
}