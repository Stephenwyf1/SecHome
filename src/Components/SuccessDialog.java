package Components;

import javax.swing.*;
import java.awt.*;

public class SuccessDialog extends JDialog {
    SuccessDialog() {
        this.setTitle("Success!");
        JLabel label = new JLabel("Operation Success!", JLabel.CENTER);
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);
        this.setBounds(300,200,500,200);
        this.setVisible(true);
    }
}
