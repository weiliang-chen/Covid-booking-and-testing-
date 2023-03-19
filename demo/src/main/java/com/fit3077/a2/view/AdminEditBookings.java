package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.AdminFacade;
import com.fit3077.a2.Application;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.controller.UserFacade;
import com.fit3077.a2.model.Admin;
import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.Facility;
import com.fit3077.a2.model.User;
import com.fit3077.a2.service.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminEditBookings extends JFrame {
    private JLabel bookingIDTitle;
    private JLabel bookingFacilityTitle;
    private JLabel bookingPatientNameTitle;
    private JLabel bookingTimeTitle;
    private JLabel bookingCovidTestTypeTitle;
    private JLabel booking1IDResult;
    private JLabel booking1TimeResult;
    private JLabel booking1PatientNameResult;
    private JLabel booking1CovidTestTypeResult;
    private JLabel booking2TimeResult;
    private JLabel booking2PatientNameResult;
    private JLabel booking2FacilityResult;
    private JLabel booking2CovidTestTypeResult;
    private JLabel booking1FacilityResult;
    private JLabel booking2IDResult;
    private JButton backButton;
    private JButton previousButton;
    private JButton nextButton;
    private JCheckBox booking1CheckBox;
    private JCheckBox booking2CheckBox;
    private JButton modifyBookingButton;
    private JPanel mainPanel;

    private Integer startingIndex = 0;
    private Integer amountToDisplay = 2;
    private Integer endingIndex = 2;

    String bookingID = "";
    String bookingTime = "";
    String bookingPatientName = "";
    String bookingFacility = "";
    String bookingTestType = "";


    public AdminEditBookings(String title, Application controller)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        User user = serviceFacade.getUserById(controller.GetLoginID());
        UserFacade userFacade = UserFacade.getInstance(user);

        ArrayList<Admin> admins = userFacade.getUserService().getAdmins();
        ArrayList<Booking> bookings = null;
        Admin admin = null;

        for(int i = 0; i < admins.size(); i++)
        {
            if(admins.get(i).getId().equals(controller.GetLoginID()))
            {
                admin = admins.get(i);
            }
        }

        if(admin != null)
        {
            AdminFacade adminFacade = AdminFacade.getInstance(admin);
            bookings = adminFacade.getFacilityBooking();
            PopulateInformation(startingIndex, endingIndex, amountToDisplay, bookings);
        }
        ArrayList<Booking> finalBookings = bookings;

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TurnPageLeft();
                PopulateInformation(startingIndex,endingIndex,amountToDisplay, finalBookings);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TurnPageRight(finalBookings.size());
                PopulateInformation(startingIndex,endingIndex,amountToDisplay,finalBookings);
            }
        });

        modifyBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                if(booking1CheckBox.isSelected())
                {
                    controller.SetBookingToEditID(booking1IDResult.getText());
                    controller.UpdateProgressionUIState(ProgressionUIState.MODIFYBOOKINGADJUSTMENT);

                }
                else if(booking2CheckBox.isSelected())
                {
                    controller.SetBookingToEditID(booking2IDResult.getText());
                    controller.UpdateProgressionUIState(ProgressionUIState.MODIFYBOOKINGADJUSTMENT);
                }
            }
        });
    }
    private void PopulateInformation(Integer startingIndex, Integer endingIndex, Integer amountToDisplay, ArrayList<Booking> bookings)
    {
        for(int i = startingIndex; i < endingIndex; i++)
        {
            if(bookings.get(i) != null)
            {
                Booking booking = bookings.get(i);

                bookingID = booking.getBookingId();
                bookingTime = booking.getStartTime();
                User user = serviceFacade.getUserById(booking.getCustomerId());
                bookingPatientName = user.getUserName();
                Facility facility = serviceFacade.getFacilityById(booking.getSiteId());
                bookingFacility = facility.getName();
                bookingTestType = booking.getTestType();

                if(i % 2 == 1)
                {
                    booking1IDResult.setText(bookingID);
                    booking1TimeResult.setText(bookingTime);
                    booking1PatientNameResult.setText(bookingPatientName);
                    booking1FacilityResult.setText(bookingFacility);
                    booking1CovidTestTypeResult.setText(bookingTestType);
                }
                else
                {
                    booking2IDResult.setText(bookingID);
                    booking2TimeResult.setText(bookingTime);
                    booking2PatientNameResult.setText(bookingPatientName);
                    booking2FacilityResult.setText(bookingFacility);
                    booking2CovidTestTypeResult.setText(bookingTestType);
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

    private void TurnPageRight(int sizeOfArray)
    {
        if (endingIndex < sizeOfArray)
        {
            startingIndex += amountToDisplay;
            endingIndex += amountToDisplay;
        }
    }

    private void TurnPageLeft()
    {
        if(startingIndex != 0)
        {
            startingIndex-=amountToDisplay;
            endingIndex-=amountToDisplay;
        }
    }

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }
}
