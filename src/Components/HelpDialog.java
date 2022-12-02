package Components;

import javax.swing.*;
import java.awt.*;

public class HelpDialog extends JDialog {

    public HelpDialog() {
        this.setTitle("Help");
        this.setLayout(new FlowLayout());
        JTextArea text = new JTextArea();
        text.append("Room colored GREEN means sensor active\n");
        text.append("Room colored YELLOW means sensor inactive\n");
        text.append("Room colored RED means sensor error\n");
        text.append("Room not colored means No Sensor installed in this room");
        this.add(text);
        this.setBounds(200,200,400,100);
        this.setVisible(true);
    }
}
