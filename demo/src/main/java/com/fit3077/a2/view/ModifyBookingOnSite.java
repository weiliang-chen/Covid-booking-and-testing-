package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyBookingOnSite extends JFrame{
    private JTextField bookingInfoTextField;
    private JLabel bookingInfoTextLabel;
    private JPanel mainPanel;
    private JButton findBookingButton;

    public ModifyBookingOnSite(String title, Application controller) {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        findBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.SetBookingToEditID(bookingInfoTextField.getText());
                controller.UpdateProgressionUIState(ProgressionUIState.MODIFYBOOKINGADJUSTMENT);
            }
        });
    }
}
