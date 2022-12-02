package Components;

import Backend.BuildingSection;
import Backend.SecHomeSystem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HelpDialog extends JDialog {

    public HelpDialog() {
        this.setTitle("Help");
        this.setLayout(new FlowLayout());
        JTextArea text = new JTextArea();
        text.append("Room colored GREEN means sensor active\n");
        text.append("Room colored YELLOW means sensor inactive\n");
        text.append("Room colored RED means sensor error\n");
        text.append("Room not colored means No Sensor installed in this room\n");

        text.append("There are 3 sections in total: \n");
        HashMap<UUID, BuildingSection> map = SecHomeSystem.getSingletonSystem().getSectionMap();
        int count = 0;
        for(Map.Entry<UUID,BuildingSection> entry : map.entrySet()) {
            text.append("Section " + count + ": " + entry.getKey().toString() + "\n");
            count++;
        }
        this.add(text);
        this.setBounds(200,200,600,200);
        this.setVisible(true);
    }
}
