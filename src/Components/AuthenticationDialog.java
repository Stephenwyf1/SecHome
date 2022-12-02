package Components;

import Backend.Room;
import Backend.SecHomeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AuthenticationDialog extends JDialog{

    SecHomeSystem system = SecHomeSystem.getSingletonSystem();

    AuthenticationDialog (String action) {
        this.setLayout(new FlowLayout());
        this.setBounds(200,200,350,100);
        JLabel label = new JLabel("Password: ");
        JTextField input = new JTextField(20);
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (system.verifyPassword(input.getText())) {
                    ArrayList<Room> list;
                    if(action.equals("on")) {
                        list = system.turnOnSystem();
                    } else {
                        list = system.turnOffSystem();
                    }

                    for (Room each : list) {
                        Application.paintSingleRoom(each);
                        Application.panel.updateUI();
                    }
                    new SuccessDialog();
                } else {
                    new ErrorDialog("Invalid Credential");
                }
            }
        });


        this.add(label);
        this.add(input);
        this.add(submit);
        this.setVisible(true);
    }
}
