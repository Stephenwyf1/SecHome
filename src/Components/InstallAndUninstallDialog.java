package Components;

import Backend.Building;
import Backend.Room;
import Backend.SecHomeSystem;
import Backend.SensorType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class InstallAndUninstallDialog extends JDialog{

    SecHomeSystem system = SecHomeSystem.getSingletonSystem();

    JButton installBtn;
    JButton uninstallBtn;

    public InstallAndUninstallDialog() {

            this.setTitle("Install and Uninstall");
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
                            Building building;
                            building = switch (option) {
                                // By Room
                                case 0 -> system.getRoomById(id);
                                case 1 -> system.getSectionById(id);
                                default -> null;
                            };

                            // id doesn't exist
                            if (building == null) {
                                ErrorDialog err = new ErrorDialog("Room id doesn't exist.");
                            } else {
                                SensorType type;
                                boolean needCam = false;
                                if (box2.getSelectedIndex() == 0) {
                                    type = SensorType.FIRE;
                                } else {
                                    type = SensorType.SEC;
                                    if (box2.getSelectedIndex() == 1) {
                                        needCam = true;
                                    }
                                }

                                ArrayList<Room> list = building.installSensor(type,needCam);
                                for (Room each : list) {
                                    Application.paintSingleRoom(each);
                                    Application.panel.updateUI();
                                }
                                new SuccessDialog();
                            }
                        } catch (Exception exception) {
                            ErrorDialog err = new ErrorDialog(exception.getMessage());
                        }

                    }
                }
            });

            // uninstall action event
            uninstallBtn = new JButton("Uninstall");
            uninstallBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        if (input.getText().equals("")) {
                            new ErrorDialog("Please enter the room or section ID you want to uninstall.");
                        }

                        int option = box1.getSelectedIndex();
                        UUID id = UUID.fromString(input.getText());
                        Building building = switch (option) {
                            // By Room
                            case 0 -> system.getRoomById(id);
                            case 1 -> system.getSectionById(id);
                            default -> null;
                        };

                        ArrayList<Room> list = building.uninstallSensor();
                        for (Room each : list) {
                            Application.paintSingleRoom(each);
                            Application.panel.updateUI();
                        }
                        new SuccessDialog();

                    } catch (Exception exception) {
                        new ErrorDialog(exception.getMessage());
                    }
                }
            });


            this.add(installBtn);
            this.add(uninstallBtn);
            this.setVisible(true);


    }
}
