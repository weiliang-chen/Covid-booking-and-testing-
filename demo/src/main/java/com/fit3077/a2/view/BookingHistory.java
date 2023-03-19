package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.Application;
import com.fit3077.a2.controller.UserFacade;
import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.Facility;
import com.fit3077.a2.model.User;
import com.fit3077.a2.service.UserService;
import com.fit3077.a2.service.VerifyService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookingHistory extends JFrame
{
    private JPanel mainPanel;
    private JButton backButton;
    private JLabel nomodifiedBookingsMessage;
    private JLabel bookingIDTitle;
    private JLabel bookingPatientNameTitle;
    private JLabel bookingTimeTitle;
    private JLabel bookingFacilityTitle;
    private JLabel booking1IDResult;
    private JLabel booking2IDResult;
    private JLabel booking3IDResult;
    private JLabel booking1PatientNameResult;
    private JLabel booking2PatientNameResult;
    private JLabel booking3PatientNameResult;
    private JLabel booking1TimeResult;
    private JLabel booking2TimeResult;
    private JLabel booking3TimeResult;
    private JLabel booking1FacilityResult;
    private JLabel booking2FacilityResult;
    private JLabel booking3FacilityResult;

    String bookingID = "";
    String bookingTime = "";
    String bookingPatientName = "";
    String bookingFacility = "";
    String bookingTestType = "";

    public BookingHistory(String title, Application application) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        User user = application.GetModified();
        ArrayList<Booking> bookings = null;
        if(user != null)
        {
            bookings = user.getHistoryBooking();
        }
        if(bookings != null)
        {
            nomodifiedBookingsMessage.setVisible(bookings.size() == 0);
            PopulateInformation(bookings);
        }
        else
        {
            nomodifiedBookingsMessage.setVisible(true);
        }


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });
    }

    private void PopulateInformation(ArrayList<Booking> bookings)
    {
        for(int i = 0; i < bookings.size(); i++)
        {
            if(bookings.get(i) != null)
            {
                Booking booking = bookings.get(i);

                bookingID = booking.getBookingId();
                bookingTime = booking.getStartTime();
                User user = (User)(userService.getById(booking.getCustomerId()));
                UserFacade userFacade = UserFacade.getInstance(user);
                bookingPatientName = user.getUserName();
                Facility facility = (Facility)(userFacade.getSearchFacilityService().getById(booking.getSiteId()));
                bookingFacility = facility.getName();

                switch (i)
                {
                    case 0:
                        booking1IDResult.setText(bookingID);
                        booking1TimeResult.setText(bookingTime);
                        booking1PatientNameResult.setText(bookingPatientName);
                        booking1FacilityResult.setText(bookingFacility);
                        break;
                    case 1:
                        booking2IDResult.setText(bookingID);
                        booking2TimeResult.setText(bookingTime);
                        booking2PatientNameResult.setText(bookingPatientName);
                        booking2FacilityResult.setText(bookingFacility);
                        break;
                    case 2:
                        booking3IDResult.setText(bookingID);
                        booking3TimeResult.setText(bookingTime);
                        booking3PatientNameResult.setText(bookingPatientName);
                        booking3FacilityResult.setText(bookingFacility);
                        break;
                }
            }
            else
            {
                bookingID = "";
                bookingTime = "";
                bookingPatientName = "";
                bookingFacility = "";
                bookingTestType = "";
            }
        }
    }

    private VerifyService userService;
    {
        userService = UserService.getInstance();
    }
}
