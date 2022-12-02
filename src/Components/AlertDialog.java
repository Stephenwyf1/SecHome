package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertDialog extends JDialog {
    AlertDialog(String err) {
        this.setLayout(new FlowLayout());
        this.setBounds(200,200,200,200);
        JLabel label = new JLabel(err);
        JLabel res = new JLabel("Response Code :");
        JTextField input = new JTextField(20);
        JButton submit = new JButton("Submit");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
