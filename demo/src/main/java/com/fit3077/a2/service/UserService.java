package com.fit3077.a2.service;

import com.fit3077.a2.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;


public class UserService extends BaseService implements VerifyService {


    private String userUrl= "https://fit3077.com/api/v2/user";

    private static ArrayList<User> users = new ArrayList(100);
    private static ArrayList<Admin> admins = new ArrayList(100);

    private static UserService instance;

    private UserService(){
        getAll();
    }

    public static VerifyService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

    @Override
    // this method will check the user is existed by checking the username and password
    public boolean verifyUser(String userName, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName) && users.get(i).getUserName().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean ifUserAdmin(String id){
        for(int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    @Override
    // get all the user object and store them into arraylist
    public void getAll() {
        HttpResponse<String> userResponse = getRequest(userUrl);
        JSONArray userObj = new JSONArray(userResponse.body());

        //get user data
        for(int i = 0; i < userObj.length(); i++){
            if (userObj.getJSONObject(i).getBoolean("isReceptionist")) {
                admins.add(new Admin(userObj.getJSONObject(i)));
            }
            users.add(new User(userObj.getJSONObject(i)));
        }
    }

    @Override
    public ArrayList getUserFromUsername(String userName)
    {
        ArrayList<User> currentUser = new ArrayList(1);

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i) != null)
            {
                String name = users.get(i).getUserName();
                if(name.equals(userName))
                {
                    currentUser.add(users.get(i));
                    return currentUser;
                }
            }

        }
        return currentUser;
    }

    @Override
    // get a user object by its id
    public Object getById(String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }

    //create a user object
    public HttpResponse post(JSONObject obj){
        return postRequest(userUrl, obj);
    }

    @Override
    // update a specific user object
    public boolean patch(String id, JSONObject obj) {
        String url = userUrl + "/" + id;
        return patchRequest(url, obj);
    }

    //we don't delete users
    @Override
    public boolean delete(String id)
    {
        return false;
    }

    @Override
    public ArrayList<User> getList() {
        return users;
    }

    public boolean userHasBooking(String id){
        String url = userUrl + "/" + id + "?fields=bookings";
        HttpResponse<String> response = getRequest(url);
        JSONObject obj = new JSONObject(response.body());
        if (obj.getJSONArray("bookings").length() >= 1) {
            return true;
        } else return false;
    }

    //parameter id is user's id
    @Override
    public ArrayList<Booking> getUserBooking(String id){
        ArrayList<Booking> bookings = new ArrayList(10);
        String url = userUrl + "/" + id + "?fields=bookings";
        HttpResponse<String> response = getRequest(url);
        JSONObject obj = new JSONObject(response.body());
        for (int i = 0; i < obj.getJSONArray("bookings").length(); i++) {
            JSONObject booking =  obj.getJSONArray("bookings").getJSONObject(i);
            bookings.add(new Booking(booking));
        }
        return bookings;
    }

    // This method will check if user has home testing.
    public boolean userHasHomeTesting(String id){
        String url = userUrl + "/" + id + "?fields=bookings.covidTests";
        HttpResponse<String> response = getRequest(url);
        JSONObject obj = new JSONObject(response.body());
        if (obj.getJSONArray("bookings").length() == 0) {
            return false;
        }

        for (int i = 0; i < obj.getJSONArray("bookings").length(); i++) {

            if (obj.getJSONArray("bookings").getJSONObject(i).getJSONArray("covidTests").length() != 0){
                for (int j = 0; j < obj.getJSONArray("bookings").getJSONObject(i).getJSONArray("covidTests").length(); j++)
                    if (!obj.getJSONArray("bookings").getJSONObject(i).getJSONArray("covidTests").getJSONObject(j).getJSONObject("additionalInfo").isNull("isHomeTest")) {
                        if (obj.getJSONArray("bookings").getJSONObject(i).getJSONArray("covidTests").getJSONObject(j).getJSONObject("additionalInfo").getBoolean("isHomeTest")){
                            return true;
                        };
                    }
            }
        }
        return false;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }
}