package com.fit3077.a2.service;

import java.util.ArrayList;

public interface BookingBaseService extends Service
{
    public boolean createBooking(String customerId, String siteId, String notes, String type, boolean homeTest, boolean userHasKit);

    public boolean cancelBooking(String bookingId);

    public boolean userGetKit(String qrCode, boolean getKit);
    public ArrayList getListOfSearchResults(int numOfFacilitiesToDisplay, String searchQuery);
    public void updateList();

    }
