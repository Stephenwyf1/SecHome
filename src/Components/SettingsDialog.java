package Components;

import Backend.SecHomeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog {
    static SecHomeSystem system = SecHomeSystem.getSingletonSystem();
    SettingsDialog() {
        this.setLayout(new FlowLayout());
        this.setBounds(200,200,500,150);
        JLabel contact1 = new JLabel("Contact Number 1: ");
        JTextField input1 = new JTextField(20);
        JLabel contact2 = new JLabel("Contact Number 2: ");
        JTextField input2 = new JTextField(20);
        JLabel pwd = new JLabel("Password: ");
        JTextField input3 = new JTextField(20);
        JButton submit1 = new JButton("Submit");
        JButton submit2 = new JButton("Submit");
        JButton submit3 = new JButton("Submit");

        this.add(contact1);
        this.add(input1);
        this.add(submit1);
        this.add(contact2);
        this.add(input2);
        this.add(submit2);
        this.add(pwd);
        this.add(input3);
        this.add(submit3);

        submit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    system.setContactNumber1(input1.getText());
                } catch (Exception exception) {
                    new ErrorDialog(exception.getMessage());
                }
            }
        });

        submit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    system.setContactNumber2(input2.getText());
                } catch (Exception exception) {
                    new ErrorDialog(exception.getMessage());
                }
            }
        });

        submit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    system.setPassword(input3.getText());
                } catch (Exception exception) {
                    new ErrorDialog(exception.getMessage());
                }
            }
        });

        this.setVisible(true);

    }
}
