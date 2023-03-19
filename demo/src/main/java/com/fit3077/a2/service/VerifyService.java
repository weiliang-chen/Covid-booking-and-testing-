package com.fit3077.a2.service;

import java.util.ArrayList;

public interface VerifyService extends Service{

    public boolean verifyUser(String username, String password);

    public boolean userHasBooking(String id);

    public ArrayList getUserBooking(String id);

    public ArrayList getUserFromUsername(String userName);

    public boolean userHasHomeTesting(String id);

    public boolean ifUserAdmin(String id);
}