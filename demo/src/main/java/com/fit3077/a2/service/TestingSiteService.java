package com.fit3077.a2.service;

import com.fit3077.a2.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;

public class TestingSiteService extends BaseService implements SearchFacilityService{
    private  String siteUrl= "https://fit3077.com/api/v2/testing-site";

    private static TestingSiteService instance;
    private ArrayList<Facility> sites = new ArrayList(100);
    private ArrayList<FacilityPublisher> sitePublishers = new ArrayList(100);

    private TestingSiteService(){
        getAll();

    }

    //singleton design pattern
    public static SearchFacilityService getInstance(){
        if (instance == null){
            instance = new TestingSiteService();
        }
        return instance;
    }

    @Override
    // get all the facility object and store them into arraylist
    public void getAll(){
        HttpResponse<String> siteResponse = getRequest(siteUrl);
        JSONArray siteObj = new JSONArray(siteResponse.body());
        //get facility data
        for(int i = 0; i < siteObj.length(); i++){
            sites.add(new Facility(siteObj.getJSONObject(i)));
            FacilityPublisher sitePublisher = new FacilityPublisher();
            sitePublisher.setFacilityId(sites.get(i).getId());
            sitePublishers.add(sitePublisher);
        }
    }

    @Override
    // get a facility object by its id
    public Object getById(String id){
        for (int i = 0; i < sites.size(); i++) {
            if (sites.get(i).getId().equals(id)) {
                return sites.get(i);
            }
        }
        return null;
    }

    // We don't create facility in the application
    public HttpResponse post(JSONObject obj){
        return null;
    }

    public boolean patch(String id, JSONObject obj){
        String url = siteUrl + "/" + id;
        return patchRequest(url, obj);

    }

    //we don't delete testing sites
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public ArrayList getList() {
        return sites;
    }

    public ArrayList getListOfSearchResults(int numOfFacilitiesToDisplay, String searchQuery)
    {
        ArrayList<Facility> facilities = getList();
        int numberOfFoundFacilities = 0;
        ArrayList<Facility> foundFacilities = new ArrayList(numOfFacilitiesToDisplay);
        for(int i = 0; i < facilities.size(); i++)
        {
            if(numberOfFoundFacilities == numOfFacilitiesToDisplay)
            {
                break;
            }

            if(facilities.get(i) != null)
            {
                Facility facility = facilities.get(i);
                String facID = facility.getId();
                String facName = facility.getName();
                String facPostCode = facility.getPostcode();
                String facState = facility.getState();
                String facStreet = facility.getStreet();
                String facSuburb = facility.getSuburb();

                if(facID.contains(searchQuery) || facName.contains(searchQuery) || facPostCode.contains(searchQuery) || facState.contains(searchQuery)
                || facStreet.contains(searchQuery) || facSuburb.contains(searchQuery))
                {
                    foundFacilities.add(facility);
                    numberOfFoundFacilities++;
                }
            }
        }

        return foundFacilities;
    }

    public ArrayList<FacilityPublisher> getSitePublishers() {
        return sitePublishers;
    }

    public FacilityPublisher getPublisherById(String id) {
        for (int i = 0; i < sitePublishers.size(); i++) {
            if (sitePublishers.get(i).getFacilityId().equals(id)){
                return sitePublishers.get(i);
            }
        }
        return null;
    }

    public void addSubscribers(ArrayList<Admin> users){
        for (int i = 0; i < sitePublishers.size(); i++) {
            sitePublishers.get(i).setAdminsEmpty();
        }
        for (int i = 0; i < users.size(); i++) {
            Admin admin;
            if (users.get(i).getClass() == Admin.class){

                admin = users.get(i);

                for (int j = 0; j < sitePublishers.size(); j++) {
                    if (sitePublishers.get(j).getFacilityId().equals(admin.getWorkingSiteId())){
                        sitePublishers.get(j).addSubscriber(admin);
                    }
                }
            }
        }
    }
}