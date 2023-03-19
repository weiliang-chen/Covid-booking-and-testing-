package com.fit3077.a2.service;

import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.Facility;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class BookingService extends BaseService implements BookingBaseService {

    private String bookingUrl="https://fit3077.com/api/v2/booking";

    private static BookingService instance;

    private static ArrayList<Booking> bookings = new ArrayList(100);

    private BookingService(){
        getAll();
    }

    // get all the booking object and store them into arraylist
    public void getAll(){
        HttpResponse<String> response = getRequest(bookingUrl);
        JSONArray bookingObj = new JSONArray(response.body());
        bookings = new ArrayList(100);
        for(int i = 0; i < bookingObj.length(); i++){
            bookings.add(new Booking(bookingObj.getJSONObject(i)));
        }
    }

    //Singleton design pattern
    public static BookingBaseService getInstance() {
        if (instance == null){
            instance = new BookingService();
        }
        return instance;
    }

    //create a booking object
    public HttpResponse post(JSONObject obj){
        return postRequest(bookingUrl, obj);
    }

    // get one booking object by its id
    public Object getById(String id){
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId().equals(id)) {
                return bookings.get(i);
            }
        }
        return null;
    }

    // update a specific booking object
    public boolean patch(String id, JSONObject obj){
        String url = bookingUrl + "/" + id;
        return patchRequest(url, obj);

    }

    @Override
    public boolean delete(String id){
        String url = bookingUrl + "/" + id;
        return deleteRequest(url);

    }

    @Override
    public boolean createBooking(String customerId, String siteId, String notes, String type, boolean homeTest, boolean userHasKit){
        Booking booking = new Booking();
        booking.setCustomerId(customerId);
        booking.setSiteId(siteId);
        booking.setNotes(notes);
        booking.setTestType(type);
        booking.setHomeTest(homeTest);
        booking.setUserHasKit(userHasKit);
        booking.setStartTime(java.time.LocalDateTime.now().toString());
        JSONObject jObj = booking.createJObject();
        HttpResponse<String> response = post(jObj);
        if (response.statusCode() == 201){
            JSONObject obj = new JSONObject(response.body());
            booking.setBookingId(obj.getString("id"));
            bookings.add(new Booking());
            return true;
        }
        return false;
    }

    //This method will return found bookings from the query
    public ArrayList getListOfSearchResults(int numOfBookingsToDisplay, String searchQuery)
    {
        ArrayList<Booking> bookings = getList();
        int numberOfFoundBooking = 0;

        ArrayList<Booking> foundBookings = new ArrayList(numOfBookingsToDisplay);
        for(int i = 0; i < bookings.size(); i++)
        {
            if(numberOfFoundBooking == numOfBookingsToDisplay)
            {
                break;
            }

            if(bookings.get(i) != null)
            {
                Booking booking = bookings.get(i);
                String bookingID = booking.getBookingId();
                String bookingTime = booking.getStartTime();
                String bookingPatientName = booking.getCustomerId();
                String bookingFacility = booking.getSiteId();
                String bookingTestType = booking.getTestType();

                if(bookingID.contains(searchQuery) || bookingTime.contains(searchQuery) || bookingPatientName.contains(searchQuery) || bookingFacility.contains(searchQuery)
                        || bookingTestType.contains(searchQuery))
                {
                    foundBookings.add(booking);
                    numberOfFoundBooking++;
                }
            }
        }

        return foundBookings;
    }

    @Override
    public void updateList() {

    }

    public boolean updateBookingStartTime(String bookingId, String startTime){
        JSONObject obj = new JSONObject();
        obj.put("startTime", startTime);
        return patch(bookingId, obj);
    }

    public boolean updateBookingTestingSite(String bookingId, String siteId){
        JSONObject obj = new JSONObject();
        obj.put("siteId", siteId);
        return patch(bookingId, obj);

    }

    public boolean cancelBooking(String bookingId){
        Booking booking = (Booking) getById(bookingId);
        if (booking != null) {
            if (booking.getStatus().equals("INITIATED") || booking.getStatus().equals("PENDING")){
                JSONObject obj = new JSONObject();
                obj.put("status", "CANCEL");
                return patch(bookingId, obj);
            }
        }
        return false;
    }

    public ArrayList getList() {
        return bookings;
    }

    @Override
    public boolean userGetKit(String qrCode, boolean getKit) {
        Booking booking;
        for(int i = 0; i < bookings.size(); i++)
        {
            if (bookings.get(i).getQrCode().equals(qrCode)){
                booking = bookings.get(i);
                booking.setUserHasKit(getKit);
                return getKit;
            }
        }
        return false;
    }

}