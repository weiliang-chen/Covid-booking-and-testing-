package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeCovidTest extends JFrame{
    private JButton administerCovidTestButton;
    private JLabel testTakenTitle;
    private JPanel mainPanel;
    private boolean hasTestBeenAdministered = false;

    public HomeCovidTest(String title, Application application)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        Application controller = new Application();

        administerCovidTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(hasTestBeenAdministered != true)
                {
                    hasTestBeenAdministered = true;
                    testTakenTitle.setText("Test was successful!");
                    administerCovidTestButton.setText("Click to continue");
                }
                else
                {
                    controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
                }
            }
        });
    }
}
