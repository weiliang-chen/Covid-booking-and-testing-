package com.fit3077.a2.model;

import org.json.JSONObject;

public class Facility {

    private String id;
    private String name;
    private String websiteUrl;
    private String phoneNumber;
    private String street;
    private String suburb;
    private String state;
    private String postcode;

    private int waitTime;
    private int facilityType;
    private boolean provideOnSiteBooking;
    private boolean provideOnSiteTesting;
    private boolean isFacilityOpen;

    public Facility(JSONObject obj){
        this.id = obj.getString("id");
        this.name = obj.getString("name");
        if(!obj.isNull("websiteUrl")){
            this.websiteUrl = obj.getString("websiteUrl");
        }
        this.phoneNumber = obj.getString("phoneNumber");
        this.street = obj.getJSONObject("address").getString("street");
        this.suburb = obj.getJSONObject("address").getString("suburb");
        this.state = obj.getJSONObject("address").getString("state");
        this.postcode = obj.getJSONObject("address").getString("postcode");
        this.waitTime = obj.getJSONObject("additionalInfo").getInt("waitTime");
        this.facilityType = obj.getJSONObject("additionalInfo").getInt("facilityType");
        this.isFacilityOpen = obj.getJSONObject("additionalInfo").getBoolean("isFacilityOpen");
        this.provideOnSiteBooking = obj.getJSONObject("additionalInfo").getBoolean("provideOnSiteBooking");
        this.provideOnSiteTesting = obj.getJSONObject("additionalInfo").getBoolean("provideOnSiteTesting");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWaitingTime() {
        return waitTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitTime = waitingTime;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public int getFacilityType() { return facilityType; }

    public boolean getHasOnSiteBooking() { return provideOnSiteBooking; }

    public boolean getHasOnSiteTesting() { return provideOnSiteTesting; }

    public boolean isOpen() { return isFacilityOpen; }

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
