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

public class ViewCovidBooking extends JFrame
{

    private JPanel mainPanel;
    private JButton backButton;
    private JLabel patientBookingNoTitle;
    private JLabel patientQRCodeTitle;
    private JLabel invalidBookingWarning;
    private JLabel patientNameTitle;
    private JLabel patientNameResult;
    private JLabel patientBookingNumberResult;
    private JLabel patientQRCodeResult;
    private JLabel patientHasTestTitle;
    private JLabel patientHasTestResult;
    private JLabel bookingErrorMessage;

    public ViewCovidBooking(String title, Application controller)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //The assumption here is you only ever create the booking on the account that has logged in
        if(serviceFacade.userHasBooking(controller.GetLoginID()))
        {
            User user = null;
            user = serviceFacade.getUserById(controller.GetLoginID());

            if(user != null)
            {
                patientNameResult.setText(user.getUserName());

                ArrayList<Booking> currentBooking = serviceFacade.getUserBookings(user.getId());
                if(currentBooking.size() > 0)
                {
                    //id
                    patientBookingNumberResult.setText(currentBooking.get(0).getBookingId());
                    patientHasTestResult.setText(GetStringResultFromBoolean(currentBooking.get(0).isUserHasKit()));
                    patientQRCodeResult.setText(currentBooking.get(0).getQrCode());
                }
                else
                {
                    patientBookingNumberResult.setText("Invalid");
                    patientHasTestResult.setText("Invalid");
                    patientQRCodeResult.setText("Invalid");
                }
            }
            else
            {
                bookingErrorMessage.setText("Error! Invalid Login");
            }
        }
        else
       {
            bookingErrorMessage.setText("You do not currently have a booking");
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });
    }

    private String GetStringResultFromBoolean(boolean bool)
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

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }
}
