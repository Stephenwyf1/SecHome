package Components;

import Backend.SecHomeSystem;

import javax.swing.*;

public class BuildBillDialog {
    SecHomeSystem system = SecHomeSystem.getSingletonSystem();

    BuildBillDialog() {
        JComboBox box = new JComboBox();
        box.addItem("Fire Alarm System");
        box.addItem("Secure Alarm System");

    }
}
