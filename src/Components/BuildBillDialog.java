package Components;

import Backend.SecHomeSystem;
import Backend.SensorType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildBillDialog extends JDialog{

    SecHomeSystem system = SecHomeSystem.getSingletonSystem();

    BuildBillDialog() {
        this.setLayout(new FlowLayout());
        JComboBox box = new JComboBox();
        box.addItem("Fire Alarm System");
        box.addItem("Secure Alarm System");
        JButton get = new JButton("Get");
        JTextArea text= new JTextArea();
        this.add(box);
        this.add(get);
        this.add(text);

        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = box.getSelectedIndex();
                SensorType type = option == 0? SensorType.FIRE : SensorType.SEC;
                String[] results = system.buildBill(type).split(System.lineSeparator());
                for (String result : results) {
                    text.append(result + "\n");
                }
            }
        });

        this.setBounds(500,200,400,300);
        this.setVisible(true);
    }
}
