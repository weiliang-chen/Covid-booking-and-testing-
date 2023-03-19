package com.fit3077.a2.model;

import org.json.JSONObject;

import java.util.UUID;

public class Booking {

    private String bookingId;
    private String customerId;
    private String siteId;
    private String smsPin;
    private String qrCode;
    private boolean userHasKit;
    private String startTime;
    private String notes;
    private String testType;
    private boolean homeTest;
    private String status;

    //this constructor will create a new booking object and make a post request to the web service
    public Booking(){
        generateQrCode();
    }
    
    // this constructor is used to create an existed booking object from the web Service
    public Booking (JSONObject obj) {
        this.bookingId = obj.getString("id");
        this.customerId = obj.getJSONObject("customer").getString("id");
        this.siteId = obj.getJSONObject("testingSite").getString("id");
        this.notes = obj.getString("notes");
        this.startTime = obj.getString("startTime");
        this.smsPin = obj.getString("smsPin");
        this.status = obj.getString("status");
        this.qrCode = obj.getJSONObject("additionalInfo").getString("qrCode");
        this.testType = obj.getJSONObject("additionalInfo").getString("testType");
        this.homeTest = obj.getJSONObject("additionalInfo").getBoolean("isHomeTest");
        this.userHasKit = obj.getJSONObject("additionalInfo").getBoolean("userHasKit");
    }

    public void generateQrCode(){
        UUID uuid = UUID.randomUUID();
        this.qrCode = uuid.toString().substring(0,8);
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSmsPin() {
        return smsPin;
    }

    public void setSmsPin(String smsPin) {
        this.smsPin = smsPin;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isUserHasKit() {
        return userHasKit;
    }

    public void setUserHasKit(boolean userHasKit) {
        this.userHasKit = userHasKit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public boolean isHomeTest() {
        return homeTest;
    }

    public void setHomeTest(boolean homeTest) {
        this.homeTest = homeTest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONObject createJObject(){
        JSONObject obj = new JSONObject();
        obj.put("customerId",this.customerId);
        obj.put("testingSiteId",this.siteId);
        obj.put("startTime",this.startTime);
        obj.put("notes", this.notes);
        JSONObject additionalObj = new JSONObject();
        additionalObj.put("qrCode",this.qrCode);
        additionalObj.put("testType",this.testType);
        additionalObj.put("isHomeTest",homeTest);
        additionalObj.put("userHasKit",userHasKit);
        obj.put("additionalInfo", additionalObj);
        return obj;
    }

}
