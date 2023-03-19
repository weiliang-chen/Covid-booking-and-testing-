package com.fit3077.a2.view;

import com.fit3077.a2.ProgressionUIState;
import com.fit3077.a2.controller.ServiceFacade;
import com.fit3077.a2.Application;
import com.fit3077.a2.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CovidSystem extends JFrame{
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JLabel usernameText;
    private JLabel passwordText;
    private JButton loginButton;

    public CovidSystem(String title)
    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(serviceFacade.verifyUser(usernameTextField.getText().toString(),passwordTextField.getText().toString()))
                {
                    Application controller = new Application();
                    ArrayList<User> currentUser = serviceFacade.getUserFromUsername(usernameTextField.getText());
                    User user = null;
                    if(currentUser.size() > 0)
                    {
                        user = currentUser.get(0);
                    }
                    if(user != null)
                    {
                        controller.SetLoginID(user.getId());
                        controller.SetLoginUser(user);
                        controller.UpdateProgressionUIState(ProgressionUIState.MAINMENU);
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
