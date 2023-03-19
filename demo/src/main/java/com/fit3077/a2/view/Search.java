package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.Application;
import com.fit3077.a2.model.Facility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Search extends JFrame {
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel facilityOneSuburb;
    private JLabel facilityTwoSuburb;
    private JLabel facilityThreeSuburb;
    private JLabel facilityFourSuburb;
    private JLabel typeOfFacilityOne;
    private JLabel typeOfFacilityTwo;
    private JLabel typeOfFacilityThree;
    private JLabel typeOfFacilityFour;
    private JLabel onsiteBookingFacilityOne;
    private JLabel onsiteBookingFacilityTwo;
    private JLabel onsiteBookingFacilityThree;
    private JLabel onsiteBookingFacilityFour;
    private JLabel isOpenFacilityOne;
    private JLabel isOpenFacilityTwo;
    private JLabel isOpenFacilityThree;
    private JLabel isOpenFacilityFour;
    private JLabel waitingTimesFacilityOne;
    private JLabel waitingTimesFacilityTwo;
    private JLabel waitingTimesFacilityThree;
    private JLabel waitingTimesFacilityFour;
    private JButton backButton;
    private JLabel facilityOneName;
    private JLabel facilityTwoName;
    private JLabel facilityThreeName;
    private JLabel facilityFourName;
    private JLabel facilityOneOnSiteTesting;
    private JLabel facilityTwoOnSiteTesting;
    private JLabel facilityThreeOnSiteTesting;
    private JLabel facilityFourOnSiteTesting;

    public Search(String title, Application controller)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        CleanFacilityInformation();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CleanFacilityInformation();
                ArrayList<Facility> list = serviceFacade.getFacilityListOfSearchResult(4, searchTextField.getText().toString());

                String facilityName = "";
                String facilitySuburb = "";
                String facilityType = "";
                String facilityOnSiteBooking = "";
                String facilityOnSiteTesting = "";
                String facilityIsOpen = "";
                String facilityWaitingTimes = "";

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null)
                    {
                        Facility facility = list.get(i);

                        facilityName = facility.getName();
                        facilitySuburb = facility.getSuburb();
                        facilityType = convertFacilityTypeIntToStringResult(facility.getFacilityType());
                        facilityOnSiteBooking = convertBoolToStringResult(facility.getHasOnSiteBooking());
                        facilityOnSiteTesting = convertBoolToStringResult(facility.getHasOnSiteTesting());
                        facilityIsOpen = convertBoolToStringResult(facility.isOpen());
                        facilityWaitingTimes = Integer.toString(facility.getWaitingTime());
                    }
                    else
                    {
                        facilityName = "";
                        facilitySuburb = "";
                        facilityType = "";
                        facilityOnSiteBooking = "";
                        facilityOnSiteTesting = "";
                        facilityIsOpen = "";
                        facilityWaitingTimes = "";
                    }

                    switch (i) {
                        case 0:
                            facilityOneName.setText(facilityName);
                            facilityOneSuburb.setText(facilitySuburb);
                            typeOfFacilityOne.setText(facilityType);
                            onsiteBookingFacilityOne.setText(facilityOnSiteBooking);
                            facilityOneOnSiteTesting.setText(facilityOnSiteTesting);
                            isOpenFacilityOne.setText(facilityIsOpen);
                            waitingTimesFacilityOne.setText(facilityWaitingTimes);
                            break;
                        case 1:
                            facilityTwoName.setText(facilityName);
                            facilityTwoSuburb.setText(facilitySuburb);
                            typeOfFacilityTwo.setText(facilityType);
                            onsiteBookingFacilityTwo.setText(facilityOnSiteBooking);
                            facilityTwoOnSiteTesting.setText(facilityOnSiteTesting);
                            isOpenFacilityTwo.setText(facilityIsOpen);
                            waitingTimesFacilityTwo.setText(facilityWaitingTimes);
                            break;
                        case 2:
                            facilityThreeName.setText(facilityName);
                            facilityThreeSuburb.setText(facilitySuburb);
                            typeOfFacilityThree.setText(facilityType);
                            onsiteBookingFacilityThree.setText(facilityOnSiteBooking);
                            facilityThreeOnSiteTesting.setText(facilityOnSiteTesting);
                            isOpenFacilityThree.setText(facilityIsOpen);
                            waitingTimesFacilityThree.setText(facilityWaitingTimes);
                            break;
                        case 3:
                            facilityFourName.setText(facilityName);
                            facilityFourSuburb.setText(facilitySuburb);
                            typeOfFacilityFour.setText(facilityType);
                            onsiteBookingFacilityFour.setText(facilityOnSiteBooking);
                            facilityFourOnSiteTesting.setText(facilityOnSiteTesting);
                            isOpenFacilityFour.setText(facilityIsOpen);
                            waitingTimesFacilityFour.setText(facilityWaitingTimes);
                            break;
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });
    }

    private void CleanFacilityInformation()
    {
        facilityOneName.setText(" ");
        facilityOneSuburb.setText(" ");
        typeOfFacilityOne.setText(" ");
        onsiteBookingFacilityOne.setText(" ");
        facilityOneOnSiteTesting.setText(" ");
        isOpenFacilityOne.setText(" ");
        waitingTimesFacilityOne.setText(" ");

        facilityTwoName.setText(" ");
        facilityTwoSuburb.setText(" ");
        typeOfFacilityTwo.setText(" ");
        onsiteBookingFacilityTwo.setText(" ");
        facilityTwoOnSiteTesting.setText(" ");
        isOpenFacilityTwo.setText(" ");
        waitingTimesFacilityTwo.setText(" ");

        facilityThreeName.setText(" ");
        facilityThreeSuburb.setText(" ");
        typeOfFacilityThree.setText(" ");
        onsiteBookingFacilityThree.setText(" ");
        facilityThreeOnSiteTesting.setText(" ");
        isOpenFacilityThree.setText(" ");
        waitingTimesFacilityThree.setText(" ");

        facilityFourName.setText(" ");
        facilityFourSuburb.setText(" ");
        typeOfFacilityFour.setText(" ");
        onsiteBookingFacilityFour.setText(" ");
        facilityFourOnSiteTesting.setText(" ");
        isOpenFacilityFour.setText(" ");
        waitingTimesFacilityFour.setText(" ");
    }

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }

    private String convertBoolToStringResult(boolean bool)
    {
        if(bool)
        {
            return "Yes";
        }
        else
        {
            return "No";
        }
    }

    private String convertFacilityTypeIntToStringResult(int facilityTypeInt)
    {
        String result = "";
        switch (facilityTypeInt)
        {
            case 1:
                result = "Drive Through";
                break;
            case 2:
                result = "Walk-in";
                break;
            case 3:
                result = "Clinics";
                break;
            case 4:
                result = "GP";
                break;
            case 5:
                result = "Hospital";
                break;

        }
        return result;
    }
}
