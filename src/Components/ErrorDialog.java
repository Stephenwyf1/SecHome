package Components;

import javax.swing.*;
import java.awt.*;

public class ErrorDialog extends JDialog {
    ErrorDialog(String err) {
        this.setTitle("Error");
        JLabel label = new JLabel("<html><font color='red'>" + err + "</font></html>", JLabel.CENTER);
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
        this.setBounds(500,200,500,200);
        this.setVisible(true);
    }
}
