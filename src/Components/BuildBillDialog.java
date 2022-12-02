package Components;

import Backend.SecHomeSystem;
import Backend.SensorType;

import javax.swing.*;

public class BuildBillDialog extends JDialog{
    SecHomeSystem system = SecHomeSystem.getSingletonSystem();

    BuildBillDialog() {
        JComboBox box = new JComboBox();
        box.addItem("Fire Alarm System");
        box.addItem("Secure Alarm System");
        int option = box.getSelectedIndex();
        SensorType type = option == 0? SensorType.FIRE : SensorType.SEC;
        String result = system.buildBill(type);
        JLabel label = new JLabel("Total Amount: " + result);
        this.add(box);
        this.add(label);
        this.setBounds(500,200,200,200);
        this.setVisible(true);
    }
}
