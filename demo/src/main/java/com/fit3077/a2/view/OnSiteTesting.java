package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.Application;
import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OnSiteTesting extends JFrame{
    private JPanel mainPanel;
    private JLabel questionnaireTitle;
    private JLabel questionnaireResult;
    private JButton administerTestButton;
    private boolean hasTestBeenAdministered = false;
    public OnSiteTesting(String title, Application controller) {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        User user = null;
        user = serviceFacade.getUserById(controller.GetLoginID());
        if(user != null)
        {
            if(controller.GetUserHasStrongSymptoms())
            {
                questionnaireResult.setText("PCR");
            }
            else
            {
                questionnaireResult.setText("RAT");
            }
        }

        User finalUser = user;

        administerTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(hasTestBeenAdministered != true)
                {
                    hasTestBeenAdministered = true;
                    questionnaireTitle.setText("Test was successful!");
                    questionnaireResult.setVisible(false);
                    administerTestButton.setText("Click to continue");

                    ArrayList<Booking> currentBooking = serviceFacade.getUserBookings(finalUser.getId());

                    if (currentBooking.size() > 0)
                    {
                        serviceFacade.deleteBookingById(currentBooking.get(0).getBookingId());
                    }
                }
                else
                {
                    controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
                }
            }
        });
    }

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }
}
