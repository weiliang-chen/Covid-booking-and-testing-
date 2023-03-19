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

public class QRURLGeneration extends JFrame {
    private JButton returnToMainMenuButton;
    private JPanel mainPanel;
    private JLabel qrCodeLabel;
    private JLabel bookingNumberLabel;

    public QRURLGeneration(String title, Application controller) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        User user = null;
        user = serviceFacade.getUserById(controller.GetLoginID());

        if(user != null)
        {
            ArrayList<Facility> facilities = serviceFacade.getALlFacilityList();
            if (serviceFacade.createBooking(user.getId(), facilities.get(0).getId(), "a covid test", "RAT", true, false)) {

                ArrayList<Booking> currentBooking = serviceFacade.getUserBookings(user.getId());
                if (currentBooking.size() > 0)
                {
                    //id
                    bookingNumberLabel.setText(currentBooking.get(0).getBookingId());
                    qrCodeLabel.setText(currentBooking.get(0).getQrCode());
                }
            }
        }

        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }

        });
    }

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }

}
