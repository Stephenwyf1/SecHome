package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivateAndDeActivateDialog extends JDialog{

    JFrame owner;
    String title;
    JButton installBtn;
    JButton uninstallBtn;

    public ActivateAndDeActivateDialog(JFrame frame, String title) {
        this.owner = frame;
        this.title = title;
        this.setLayout(new FlowLayout());
        this.setBounds(
                new Rectangle(
                        200,
                        200,
                        350,
                        200
                )
        );

        JRadioButton roomOption = new JRadioButton("Install By Room");
        JRadioButton secOption = new JRadioButton("Install By Section");
        JTextArea t = new JTextArea("ID:");
        JTextField input = new JTextField(20);

        this.add(roomOption);
        this.add(secOption);
        this.add(t);
        this.add(input);

        installBtn = new JButton("Install");
        installBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        uninstallBtn = new JButton("Uninstall");
        this.add(installBtn);
        this.add(uninstallBtn);
        this.setVisible(true);

    }
}

