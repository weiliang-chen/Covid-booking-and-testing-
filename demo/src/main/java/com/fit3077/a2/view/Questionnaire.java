package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Questionnaire  extends JFrame {
    private JCheckBox resultCheckBox;
    private JButton continueButton;
    private JButton backButton;
    private JPanel mainPanel;

    public Questionnaire(String title, Application controller) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.SetHasStrongSymptoms(resultCheckBox.isSelected());
                controller.UpdateProgressionUIState(ProgressionUIState.ONSITETESTING);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
            }
        });
    }
}
