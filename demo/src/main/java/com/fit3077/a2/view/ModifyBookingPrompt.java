package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyBookingPrompt extends JFrame{
    private JPanel panel1;
    private JCheckBox phoneCheckBox;
    private JCheckBox systemCheckBox;
    private JButton acceptButton;
    private JLabel warningMessage;

    public ModifyBookingPrompt(String title, Application controller) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        warningMessage.setVisible(false);


        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phoneCheckBox.isSelected())
                {
                    controller.UpdateProgressionUIState(ProgressionUIState.MODIFYBOOKINGPHONECALL);
                }
                else if (systemCheckBox.isSelected())
                {
                    controller.UpdateProgressionUIState(ProgressionUIState.MODIFYBOOKINGONSITE);
                }
                else
                {
                    warningMessage.setVisible(true);
                }
            }
        });
    }
}
