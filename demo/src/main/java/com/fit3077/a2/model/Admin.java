package com.fit3077.a2.model;

import org.json.JSONObject;

import java.util.ArrayList;

public class Admin extends User implements Subscriber {

    private String workingSiteId;
    private ArrayList<Message> messages = new ArrayList(100);

    public Admin(JSONObject obj)
    {
        this.id = obj.getString("id");
        this.userName = obj.getString("userName");
        this.givenName = obj.getString("givenName");
        this.familyName = obj.getString("familyName");
        this.workingSiteId = obj.getJSONObject("additionalInfo").getString("workingFacility");
    }

    public String getWorkingSiteId() {
        return workingSiteId;
    }

    public void setWorkingSiteId(String workingSiteId) {
        this.workingSiteId = workingSiteId;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void addMessage(Message message) {
        System.out.println("A message has sent to "+ this.givenName + " " + this.familyName);
        messages.add(message);
    }
}
