package Components;

import Backend.Room;
import Backend.SecHomeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class InstallAndUninstallDialog extends JDialog{

    SecHomeSystem system = SecHomeSystem.getSingletonSystem();
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
                            380,
                            200
                    )
            );

            JComboBox box1 = new JComboBox();
            box1.addItem("Room");
            box1.addItem("Section");

            JComboBox box2 = new JComboBox();
            box2.addItem("Fire Sensor");
            box2.addItem("Motion Sensor With Camera");
            box2.addItem("Motion Sensor Without Camera");

            JTextArea t = new JTextArea("ID:");
            JTextField input = new JTextField(25);

            this.add(box1);
            this.add(box2);
            this.add(t);
            this.add(input);

            // install action event
            installBtn = new JButton("Install");
            installBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // empty input
                    if (input.getText().equals("")) {
                        ErrorDialog err = new ErrorDialog("Please enter the room or section ID you want to install.");
                    } else {
                        try {
                            UUID id = UUID.fromString(input.getText());
                            int option = box1.getSelectedIndex();

                            switch (option) {
                                // By Room
                                case 0 :
                                    Room room = system.getRoomById(id);
                                    // room id doesnt exist
                                    if (room == null) {
                                        ErrorDialog err = new ErrorDialog("Room id doesn't exist.");
                                    } else {

                                    }

                            }
                        } catch (Exception exception) {
                            ErrorDialog err = new ErrorDialog(exception.getMessage());
                        }

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
