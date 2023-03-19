package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.Application;
import com.fit3077.a2.controller.UserFacade;
import com.fit3077.a2.model.Booking;
import com.fit3077.a2.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu extends JFrame{
    private JPanel mainMenuPanel;
    private JButton bookCovidTest;
    private JButton searchFacilitiesButton;
    private JButton viewBookingButton;
    private JButton onSiteCovidTestButton;
    private JButton logoutButton;
    private JButton takeOnlineCovidTestButton;
    private JButton handOutHomeTestButton;
    private JButton modifyBookingButton;
    private JButton searchBookingsButton;
    private JButton adminEditBookingsButton;
    private JButton bookingHistoryButton;

    public MainMenu(String title, Application controller)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainMenuPanel);
        this.pack();

        //The assumption here is you only ever create the booking on the account that has logged in

        User loginUser = controller.GetLoginUser();
        UserFacade userFacade = UserFacade.getInstance(loginUser);
        if(userFacade.getUserService().ifUserAdmin(loginUser.getId()))
        {
            adminEditBookingsButton.setVisible(true);
            adminEditBookingsButton.setEnabled(true);
        }
        else
        {
            adminEditBookingsButton.setVisible(false);
            adminEditBookingsButton.setEnabled(false);
        }

        viewBookingButton.setEnabled(serviceFacade.userHasBooking(controller.GetLoginID()));

        ArrayList<User> currentUser = serviceFacade.getUserFromUsername(controller.GetLoginID());
        User user = null;
        boolean doesUserHaveAtHomeTest = false;
        ArrayList<Booking> bookingList = serviceFacade.getAllBookingList();

        if(bookingList.size() > 0)
        {
            for (int i = 0; i < bookingList.size(); i++) {
                Booking queriedBooking = null;
                if (bookingList.get(i) != null) {
                    queriedBooking = bookingList.get(i);
                    if (queriedBooking.isUserHasKit())
                    {
                        doesUserHaveAtHomeTest = true;
                        break;
                    }
                }
            }
        }

        takeOnlineCovidTestButton.setEnabled(doesUserHaveAtHomeTest);


        bookCovidTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.COVIDBOOKING);
            }
        });
        searchFacilitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.UpdateProgressionUIState(ProgressionUIState.SEARCHING);
            }
        });
        viewBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.UpdateProgressionUIState(ProgressionUIState.VIEWBOOKING);
            }
        });
        onSiteCovidTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.UpdateProgressionUIState(ProgressionUIState.QUESTIONARE);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.UpdateProgressionUIState(ProgressionUIState.LOGIN);
            }
        });

        takeOnlineCovidTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.HOMETESTING);
            }
        });

        handOutHomeTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.QRCODESCANNING);
            }
        });

        modifyBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.MODIFYBOOKINGPROMPT);
            }
        });

        adminEditBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.ADMINBOOKINGEDITING);
            }
        });
        bookingHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.BOOKINGHISTORY);
            }
        });

        searchBookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.SEARCHBOOKINGS);
            }
        });
    }

    private ServiceFacade serviceFacade;

    {
        serviceFacade = ServiceFacade.getInstance();
    }
}
