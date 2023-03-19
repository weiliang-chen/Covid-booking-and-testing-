package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.Application;
import com.fit3077.a2.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnSiteBooking extends JFrame{
    private JPanel mainPanel;
    private JButton backButton;
    private JButton createBookingButton;
    private JTextField patientNameTextField;
    private JTextField patientIDTextField;
    private JLabel patientNameText;
    private JLabel bookingTypeTitle;
    private JCheckBox testTypeCheckBoxResult;
    private JCheckBox userOwnsTestCheckBoxResult;
    private JLabel patientIDLabel;

    public OnSiteBooking(String title, Application controller) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        User user = null;
        user = serviceFacade.getUserById(controller.GetLoginID());

        if(user != null)
        {
            patientNameTextField.setText(user.getUserName());
            patientIDTextField.setText(user.getId());
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });

        createBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (testTypeCheckBoxResult.isSelected())
                {
                    if(userOwnsTestCheckBoxResult.isSelected())
                    {
                        controller.UpdateProgressionUIState(ProgressionUIState.HOMETESTING);
                    }
                    else
                    {
                        controller.UpdateProgressionUIState(ProgressionUIState.QRURLGENERATION);
                    }
                }

                else
                {
                    controller.UpdateProgressionUIState(ProgressionUIState.PINCODEGENERATION);
                }
            }
        });
    }

    private ServiceFacade serviceFacade;
    {
        serviceFacade = ServiceFacade.getInstance();
    }
}
