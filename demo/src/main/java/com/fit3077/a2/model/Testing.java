package com.fit3077.a2.model;

import org.json.JSONObject;

public class Testing {
    private String testingId;
    private String type;
    private String patientId;
    private String administererId;
    private String testingSiteId;
    private String bookingId;
    private String result;
    private String status;
    private String notes;


    public Testing() {
    }

    // this constructor is used to create an existed testing object from the web Service
    public Testing(JSONObject obj){
        this.testingId = obj.getString("id");
        this.type = obj.getString("type");
        this.patientId = obj.getJSONObject("patient").getString("id");
        this.administererId = obj.getJSONObject("administerer").getString("id");
        this.bookingId = obj.getJSONObject("booking").getString("id");
        this.testingSiteId = obj.getJSONObject("booking").getJSONObject("testingSite").getString("id");
        this.result = obj.getString("result");
        this.status = obj.getString("status");
        if (!obj.isNull("notes")){
            this.notes = obj.getString("notes");
        }
    }

    public String getTestingId() {
        return testingId;
    }

    public void setTestingId(String testingId) {
        this.testingId = testingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAdministererId() {
        return administererId;
    }

    public void setAdministererId(String administererId) {
        this.administererId = administererId;
    }

    public String getTestingSiteId() {
        return testingSiteId;
    }

    public void setTestingSiteId(String testingSiteId) {
        this.testingSiteId = testingSiteId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Testing{" +
                "Id='" + testingId + '\'' +
                ", type='" + type + '\'' +
                ", patientId='" + patientId + '\'' +
                ", administererId='" + administererId + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", result='" + result + '\'' +
                '}';
    }


    private JSONObject create(){
        JSONObject obj = new JSONObject();
        JSONObject additionalObj = new JSONObject();
        obj.put("type",this.type);
        obj.put("patientId",this.patientId);
        obj.put("administererId",this.administererId);
        obj.put("result", this.result);
        obj.put("status", this.status);
        obj.put("notes", this.notes);
        return obj;
    }

}
