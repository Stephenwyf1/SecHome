package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstallAndUninstallDialog extends JDialog{

    JFrame owner;
    String title;
    JButton installBtn;
    JButton uninstallBtn;

    public InstallAndUninstallDialog(JFrame frame, String title) {
        this.owner = frame;
        this.title = title;
        this.setLayout(new FlowLayout());
        this.setBounds(
                new Rectangle(
                        200,
                        200,
                        450,
                        200
                )
        );

        JComboBox box = new JComboBox();
        box.addItem("By Room");
        box.addItem("By Section");

        JTextArea t = new JTextArea("ID:");
        JTextField input = new JTextField(20);

        this.add(box);
        this.add(t);
        this.add(input);

        // install action event
        installBtn = new JButton("Install");
        installBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input.getText().equals("")) {
                    ErrorDialog err = new ErrorDialog("Please enter the room or section ID you want to install.");
                }
            }
        });

        // uninstall action event
        uninstallBtn = new JButton("Uninstall");
        this.add(installBtn);
        this.add(uninstallBtn);
        this.setVisible(true);

    }
}
