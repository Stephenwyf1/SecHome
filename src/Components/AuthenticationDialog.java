package Components;

import Backend.SecHomeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    if(action.equals("on")) {
                        system.turnOnSystem();
                    } else {
                        system.turnOffSystem();
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
