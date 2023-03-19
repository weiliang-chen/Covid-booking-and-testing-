package com.fit3077.a2.model;

import java.util.ArrayList;

public class FacilityPublisher {

    private String facilityId;
    private ArrayList<Subscriber> admins = new ArrayList(100);

    public FacilityPublisher(){

    }

    public void setAdminsEmpty() {
        this.admins = new ArrayList<Subscriber>(100);
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public boolean addSubscriber(Subscriber admin){
        return admins.add(admin);
    }

    public boolean deleteSubscriber(Subscriber admin) {
        return admins.remove(admin);
    }

    public void notifyAllSubscriber(String message) {
        for (int i = 0; i < admins.size(); i++) {

            admins.get(i).addMessage(new Message(message));
        }
    }

    @Override
    public String toString() {
        return "FacilityPublisher{" +
                "facilityId='" + facilityId + '\'' +
                ", admins=" + admins +
                '}';
    }
}
