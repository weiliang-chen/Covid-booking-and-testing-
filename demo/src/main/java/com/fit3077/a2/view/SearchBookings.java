package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.Application;
import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.Facility;
import com.fit3077.a2.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchBookings extends JFrame{
    private JLabel bookingIDTitle;
    private JLabel bookingPatientNameTitle;
    private JLabel bookingFacilityTitle;
    private JLabel bookingTimeTitle;
    private JLabel bookingCovidTestTypeTitle;
    private JLabel booking1IDResult;
    private JLabel booking1TimeResult;
    private JLabel booking1PatientNameResult;
    private JLabel booking1FacilityResult;
    private JLabel booking1CovidTestTypeResult;
    private JLabel booking2IDResult;
    private JLabel booking2TimeResult;
    private JLabel booking2PatientNameResult;
    private JLabel booking2FacilityResult;
    private JLabel booking2CovidTestTypeResult;
    private JLabel booking3IDResult;
    private JLabel booking3TimeResult;
    private JLabel booking3PatientNameResult;
    private JLabel booking3FacilityResult;
    private JLabel booking3CovidTestTypeResult;
    private JTextField searchTextField;
    private JButton backButton;
    private JButton searchButton;
    private JPanel mainPanel;

    public SearchBookings(String title, Application controller)
    {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CleanBookingInformation();
                ArrayList<Booking> list = serviceFacade.getBookingListOfSearchResult(3, searchTextField.getText().toString());

                String bookingID = "";
                String bookingTime = "";
                String bookingPatientName = "";
                String bookingFacility = "";
                String bookingTestType = "";

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null && list.get(i).getCustomerId().toString().equals(controller.GetLoginID().toString()))
                    {
                        Booking booking = list.get(i);

                        bookingID = booking.getBookingId();
                        bookingTime = booking.getStartTime();
                        User user = serviceFacade.getUserById(booking.getCustomerId());
                        bookingPatientName = user.getUserName();
                        Facility facility = serviceFacade.getFacilityById(booking.getSiteId());
                        bookingFacility = facility.getName();
                        bookingTestType = booking.getTestType();
                    }
                    else
                    {
                        bookingID = "";
                        bookingTime = "";
                        bookingPatientName = "";
                        bookingFacility = "";
                        bookingTestType = "";
                    }

                    switch (i)
                    {
                        case 0:
                            booking1IDResult.setText(bookingID);
                            booking1TimeResult.setText(bookingTime);
                            booking1PatientNameResult.setText(bookingPatientName);
                            booking1FacilityResult.setText(bookingFacility);
                            booking1CovidTestTypeResult.setText(bookingTestType);
                            break;
                        case 1:
                            booking2IDResult.setText(bookingID);
                            booking2TimeResult.setText(bookingTime);
                            booking2PatientNameResult.setText(bookingPatientName);
                            booking2FacilityResult.setText(bookingFacility);
                            booking2CovidTestTypeResult.setText(bookingTestType);
                            break;
                        case 2:
                            booking3IDResult.setText(bookingID);
                            booking3TimeResult.setText(bookingTime);
                            booking3PatientNameResult.setText(bookingPatientName);
                            booking3FacilityResult.setText(bookingFacility);
                            booking3CovidTestTypeResult.setText(bookingTestType);
                            break;
                    }
                }
            }
        });
    }

    private void CleanBookingInformation()
    {
        booking1IDResult.setText(" ");
        booking1TimeResult.setText(" ");
        booking1PatientNameResult.setText(" ");
        booking1FacilityResult.setText(" ");
        booking1CovidTestTypeResult.setText(" ");

        booking2IDResult.setText(" ");
        booking2TimeResult.setText(" ");
        booking2PatientNameResult.setText(" ");
        booking2FacilityResult.setText(" ");
        booking2CovidTestTypeResult.setText(" ");

        booking3IDResult.setText(" ");
        booking3TimeResult.setText(" ");
        booking3PatientNameResult.setText(" ");
        booking3FacilityResult.setText(" ");
        booking3CovidTestTypeResult.setText(" ");
    }

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }
}
