package com.fit3077.a2.controller;

import com.fit3077.a2.model.*;
import com.fit3077.a2.service.BookingService;
import com.fit3077.a2.service.TestingSiteService;
import com.fit3077.a2.service.UserService;

import java.util.ArrayList;

public class AdminFacade extends UserFacade{
    private Admin admin;
    private String testingSite;
    private ArrayList<Booking> facilityBookings;
    private static AdminFacade instance;

    private AdminFacade(Admin admin){
        super(admin);
        this.admin = admin;
        testingSite = admin.getWorkingSiteId();
        testingSiteService.addSubscribers(userService.getAdmins());
        updateFacilityBookingList();
    }

    public static AdminFacade getInstance(Admin admin) {
        if (instance == null){
            instance = new AdminFacade(admin);
        }
        return instance;
    }

    // This method update the facilityBookings, which contains all bookings that their testing facility is where the admin work at.
    public void updateFacilityBookingList(){
        ArrayList<Booking> l = bookingService.getList();
        facilityBookings = new ArrayList<Booking>(100);
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getSiteId().equals(testingSite)){
                facilityBookings.add(l.get(i));
            }
        }
    }

    // return the facilityBookings arraylist.
    public ArrayList getFacilityBooking() {
        return facilityBookings;
    }
}
