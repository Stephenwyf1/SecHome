package Components;

import javax.swing.*;
import java.awt.*;

public class ErrorDialog extends JDialog {
    ErrorDialog(String err) {
        this.setTitle("Error");
        JLabel lable = new JLabel(err, JLabel.CENTER);
        this.setLayout(new BorderLayout());
        this.add(lable, BorderLayout.CENTER);
        this.setBounds(500,200,500,200);
        this.setVisible(true);
    }
}
