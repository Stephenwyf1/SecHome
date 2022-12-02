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

public class ActiveAndInactiveDialog extends JDialog{

    SecHomeSystem system = SecHomeSystem.getSingletonSystem();

    JButton activeBtn;
    JButton inactiveBtn;

    public ActiveAndInactiveDialog () {

        this.setTitle("Active And Inactive");
        this.setLayout(new FlowLayout());
        this.setBounds(
                new Rectangle(
                        200,
                        200,
                        500,
                        150
                )
        );

        JComboBox box = new JComboBox();
        box.addItem("Room");
        box.addItem("Section");

        JTextArea t = new JTextArea("ID:");
        JTextField input = new JTextField(25);

        this.add(box);
        this.add(t);
        this.add(input);

        // active
        activeBtn = new JButton("Active");
        activeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // empty input
                if (input.getText().equals("")) {
                    ErrorDialog err = new ErrorDialog("Please enter the room or section ID you want to active.");
                } else {
                    try {
                        UUID id = UUID.fromString(input.getText());
                        int option = box.getSelectedIndex();
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
                            building.turnOnSensor();
                            new SuccessDialog();
                        }

                    } catch (Exception exception) {
                        ErrorDialog err = new ErrorDialog(exception.getMessage());
                    }

                }
            }
        });

        // inactive
        inactiveBtn = new JButton("Inactive");
        inactiveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (input.getText().equals("")) {
                        new ErrorDialog("Please enter the room or section ID you want to uninstall.");
                    }

                    int option = box.getSelectedIndex();
                    UUID id = UUID.fromString(input.getText());
                    Building building = switch (option) {
                        // By Room
                        case 0 -> system.getRoomById(id);
                        case 1 -> system.getSectionById(id);
                        default -> null;
                    };

                    building.turnOffSensor();
                    new SuccessDialog();

                } catch (Exception exception) {
                    new ErrorDialog(exception.getMessage());
                }
            }
        });


        this.add(activeBtn);
        this.add(inactiveBtn);
        this.setVisible(true);


    }
}
