package Components;

import javax.swing.*;
import java.awt.*;

public class Dialog extends JDialog{

    JFrame owner;
    String title;
    JButton btn;

    public Dialog(JFrame frame, String title) {
        this.owner = frame;
        this.title = title;
        this.setLayout(new FlowLayout());
        this.setBounds(
                new Rectangle(
                        200,
                        200,
                        350,
                        200
                )
        );

        JTextArea t1 = new JTextArea("Room ID:");
        JTextField input1 = new JTextField(20);
        JTextArea t2 = new JTextArea("Section ID:");
        JTextField input2 = new JTextField(20);
        this.add(t1);
        this.add(input1);
        this.add(t2);
        this.add(input2);

        btn = new JButton(title);
        this.add(btn);
        this.setVisible(true);

    }
}
