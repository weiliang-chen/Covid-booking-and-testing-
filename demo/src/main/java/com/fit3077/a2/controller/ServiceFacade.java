package com.fit3077.a2.controller;

import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.Facility;
import com.fit3077.a2.model.User;
import com.fit3077.a2.service.*;

import java.util.ArrayList;

public class ServiceFacade {

    private static ServiceFacade instance;
    private VerifyService userService = UserService.getInstance();
    private BookingBaseService bookingService = BookingService.getInstance();
    private TestingSiteService testingSiteService = (TestingSiteService) TestingSiteService.getInstance();
    private Service testingService = TestingService.getInstance();

    private ServiceFacade(){

    }

    //Singleton design pattern
    public static ServiceFacade getInstance() {
        if (instance == null){
            instance = new ServiceFacade();
        }
        return instance;
    }

    public boolean verifyUser(String userName, String password){
        return userService.verifyUser(userName, password);
    }

    public boolean ifUserIsAdmin(String userId){
        return userService.ifUserAdmin(userId);
    }

    public ArrayList<User> getUserFromUsername(String username){
        return userService.getUserFromUsername(username);
    }

    public ArrayList<Booking> getAllBookingList(){
        return bookingService.getList();
    }

    public ArrayList<Facility> getALlFacilityList(){
        return testingService.getList();
    }

    public boolean userHasBooking(String userId){
        return userService.userHasBooking(userId);
    }

    public User getUserById(String userId){
        return (User) userService.getById(userId);
    }

    public Booking getBookingById(String bookingId){
        return (Booking) bookingService.getById(bookingId);
    }

    public Facility getFacilityById(String siteId){
        return (Facility) testingSiteService.getById(siteId);
    }

    public boolean deleteBookingById(String bookingId){
        return bookingService.delete(bookingId);
    }

    public ArrayList<Booking> getUserBookings(String userId){
        ArrayList<Booking> l = getAllBookingList();
        ArrayList<Booking> userBookings = new ArrayList(100);
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getCustomerId().equals(userId)){
               userBookings.add(l.get(i));
            }
        }
        return userBookings;
    }

    public boolean createBooking(String customerId, String siteId, String notes, String type, boolean homeTest, boolean userHasKit){
        return bookingService.createBooking(customerId,siteId,notes,type,homeTest,userHasKit);
    }

    public boolean ifUserGetKit(String qrCode, boolean getKit){
        return bookingService.userGetKit(qrCode, getKit);
    }

    public ArrayList<Facility> getFacilityListOfSearchResult(int num, String searchQuery){
        return testingSiteService.getListOfSearchResults(num, searchQuery);
    }

    public ArrayList<Booking> getBookingListOfSearchResult(int num, String searchQuery){
        return bookingService.getListOfSearchResults(num, searchQuery);
    }
}
