package Components;

import Backend.Room;
import Backend.SecHomeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlertDialog extends JDialog {
    SecHomeSystem system = SecHomeSystem.getSingletonSystem();
    AlertDialog(ArrayList<Room> rooms, ArrayList<String> alert) {
        this.setTitle("Alert");
        this.setLayout(new FlowLayout());
        this.setBounds(300,200,700,1000);
        JLabel res = new JLabel("Response Code :");
        JTextField input = new JTextField(20);
        JButton submit = new JButton("Submit");
        JTextArea text = new JTextArea();

        for (String s : alert) {
            text.append(s + "\n");
        }

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (system.verifyResponseCode(input.getText())) {
                    system.turnOffAlertings();
                    for (Room each : rooms) {
                        Application.paintSingleRoom(each);
                        Application.panel.updateUI();
                    }
                    new SuccessDialog();
                } else {
                    new ErrorDialog("Invalid Credential");
                }
            }
        });

        this.add(res);
        this.add(input);
        this.add(submit);
        this.add(text);
        this.setVisible(true);

    }
}
