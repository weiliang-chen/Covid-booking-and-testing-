package com.fit3077.a2.service;

import java.util.ArrayList;

public interface SearchFacilityService extends Service{

    public ArrayList getListOfSearchResults(int numOfFacilitiesToDisplay, String searchQuery);
}