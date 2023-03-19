package com.fit3077.a2.controller;

import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.FacilityPublisher;
import com.fit3077.a2.model.User;
import com.fit3077.a2.service.*;

import java.util.ArrayList;

public class UserFacade {
    private User user;
    private User modifiedUser;
    private ArrayList userBookings;
    private static UserFacade instance;

    protected UserService userService = (UserService) UserService.getInstance();
    protected BookingService bookingService = (BookingService) BookingService.getInstance();
    protected TestingSiteService testingSiteService = (TestingSiteService) TestingSiteService.getInstance();
    protected SearchFacilityService searchFacilityService = (SearchFacilityService) TestingSiteService.getInstance();

    protected UserFacade(User user){
        this.user = user;
        testingSiteService.addSubscribers(userService.getAdmins());
        updateUserBookingList();
    }


    public static UserFacade getInstance(User user) {
        if (instance == null){
            instance = new UserFacade(user);
        }
        return instance;
    }
    // This method will notify all the subscribers in the facilityPublisher with facility ID.
    public void notifyAllAdmin(String facilityId, String message){
        FacilityPublisher facilityPublisher = testingSiteService.getPublisherById(facilityId);
        if(facilityPublisher != null)
        {
            facilityPublisher.notifyAllSubscriber(message);
        }
    }

    // This method will change the booked testing site of a booking.
    public boolean updateTestingSiteBooking(String bookingId, String siteId) {
        Booking booking = (Booking) bookingService.getById(bookingId);
        User user = (User) userService.getById(booking.getCustomerId());
        user.addHistoryBooking(booking);
        modifiedUser = user;
        String previousSiteId = booking.getSiteId();
        boolean success = bookingService.updateBookingTestingSite(bookingId, siteId);
        bookingService.getAll();
        String message = "The Testing facility site of booking " + bookingId + " is changed.";
        notifyAllAdmin(previousSiteId, message);
        notifyAllAdmin(siteId, message);
        return success;
    }

    //This method will change the start time of a booking
    public boolean changeBookingStartTime(String bookingId, String startTime) {
        Booking booking = (Booking) bookingService.getById(bookingId);
        User user = (User) userService.getById(booking.getCustomerId());
        user.addHistoryBooking(booking);
        modifiedUser = user;
        boolean success = bookingService.updateBookingStartTime(bookingId, startTime);
        bookingService.getAll();
        String message = "The StartTime of booking " + bookingId + " is changed.";
        notifyAllAdmin(booking.getSiteId(), message);
        return success;
    }

    //This method set a booking status to CANCEL
    public boolean cancelBooking(String bookingId) {
        boolean success = bookingService.cancelBooking(bookingId);
        bookingService.getAll();
        Booking booking = (Booking) bookingService.getById(bookingId);
        String message = "The booking " + booking.getBookingId() + "is canceled at.";
        notifyAllAdmin(booking.getSiteId(), message);
        return success;
    }

    // This method update the userBooking, which contains all booking that the user books for.
    public void updateUserBookingList(){
        ArrayList<Booking> l = bookingService.getList();
        userBookings = new ArrayList<Booking>(100);
        for (int i = 0; i < l.size(); i++) {
            if(user != null) {
                if (l.get(i).getCustomerId().equals(user.getId())) {
                    // if the booking is canceled, then it should not be displayed to the user.
                    if (!l.get(i).getStatus().equals("CANCEL"))
                        userBookings.add(l.get(i));
                }
            }
        }
    }


    //This method will get the userBooking List.
    public ArrayList getUserBookings() {
        return userBookings;
    }

    public UserService getUserService() {return userService;}
    public BookingService getBookingService() {return bookingService;}
    public TestingSiteService getTestingSiteService() {return testingSiteService;}
    public SearchFacilityService getSearchFacilityService() {return searchFacilityService;}
    public User getUser() {return user;}
    public User getModifiedUser() {return modifiedUser;}

}
