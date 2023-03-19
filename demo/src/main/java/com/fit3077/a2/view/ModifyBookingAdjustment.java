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

public class ModifyBookingAdjustment extends  JFrame{
    private JButton deleteBookingButton;
    private JButton modifyBookingButton;
    private JTextField timeOfBookingTextField;
    private JTextField FacilityOfBookingTextField;
    private JPanel mainPanel;
    private JLabel bookingUserNameResult;
    private JLabel bookingIDResult;
    private JLabel bookingTimeResult;
    private JLabel bookingFacilityResult;
    private JButton mainMenuButton;
    private JLabel bookingModResult;
    private JButton cancelBookingButton;
    private boolean hasBookingBeenDeleted = false;


    public ModifyBookingAdjustment(String title, Application controller)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        Booking booking = serviceFacade.getBookingById(controller.GetBookingToEditID());

        bookingIDResult.setText(booking.getBookingId());
        bookingTimeResult.setText(booking.getStartTime());
        User bookingUser = serviceFacade.getUserById(booking.getCustomerId());
        bookingUserNameResult.setText(bookingUser.getUserName());
        Facility facility = serviceFacade.getFacilityById(booking.getSiteId());
        bookingFacilityResult.setText(facility.getName());


        User user = serviceFacade.getUserById(controller.GetLoginID());
        UserFacade userFacade = UserFacade.getInstance(user);

        ArrayList<Admin> admins = userFacade.getUserService().getAdmins();
        Admin admin = null;

        for(int i = 0; i < admins.size(); i++)
        {
            if(admins.get(i).getId().equals(controller.GetLoginID()))
            {
                admin = admins.get(i);
            }
        }

        Admin finalAdmin = admin;


        if(userFacade.getUserService().ifUserAdmin(user.getId()))
        {
            deleteBookingButton.setVisible(true);
            deleteBookingButton.setEnabled(true);
        }
        else
        {
            deleteBookingButton.setVisible(false);
            deleteBookingButton.setEnabled(false);
        }

        deleteBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(hasBookingBeenDeleted != true)
                {
                    if(finalAdmin != null)
                    {
                        serviceFacade.deleteBookingById(booking.getBookingId());
                        bookingModResult.setText("Booking Has Been Deleted");
                        hasBookingBeenDeleted = true;
                    }
                }
            }
        });

        modifyBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTestingSite = FacilityOfBookingTextField.getText().toString();
                String newBookingStartTime = timeOfBookingTextField.getText().toString();
                //Don't modify a booking that has been deleted
                if (hasBookingBeenDeleted != true) {

                    if (timeOfBookingTextField.getText().length() > 0) {
                        if (userFacade.getUserService().ifUserAdmin(controller.GetLoginID())) {
                            AdminFacade adminFacade = AdminFacade.getInstance(finalAdmin);
                            adminFacade.changeBookingStartTime(booking.getBookingId(), newBookingStartTime);
                            controller.SetModifiedUser(adminFacade.getModifiedUser());

                        } else {
                            userFacade.changeBookingStartTime(booking.getBookingId(), newBookingStartTime);
                            controller.SetModifiedUser(userFacade.getModifiedUser());
                        }
                    }
                }
                if (FacilityOfBookingTextField.getText().length() > 0) {
                    if (serviceFacade.ifUserIsAdmin(controller.GetLoginID())) {
                        AdminFacade adminFacade = AdminFacade.getInstance(finalAdmin);
                        adminFacade.updateTestingSiteBooking(booking.getBookingId(), newTestingSite);
                        controller.SetModifiedUser(adminFacade.getModifiedUser());
                    } else {
                        userFacade.updateTestingSiteBooking(booking.getBookingId(), newTestingSite);
                        controller.SetModifiedUser(userFacade.getModifiedUser());
                    }
                }
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });

        cancelBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(hasBookingBeenDeleted != true)
                {
                    if(userFacade != null)
                    {
                        userFacade.cancelBooking(booking.getBookingId());
                    }
                }
            }
        });
    }

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }
}
