package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QRURLScanning extends JFrame {
    private JTextField patientQRCodeTextField;
    private JButton administerTestButton;
    private JLabel invalidInformationLabel;
    private JPanel mainPanel;

    private boolean hasTestBeenSent = false;

    public QRURLScanning(String title, Application controller) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        administerTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hasTestBeenSent) {

                    String bookingQRCode = patientQRCodeTextField.getText().toString();

                    if (serviceFacade.ifUserGetKit(bookingQRCode, true)) {
                        hasTestBeenSent = true;
                        administerTestButton.setText("Continue");
                        invalidInformationLabel.setVisible(true);
                        invalidInformationLabel.setText("Test has been sent!");
                    } else {
                        invalidInformationLabel.setVisible(true);
                        invalidInformationLabel.setText("Sent test failed");
                    }

                } else {
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

