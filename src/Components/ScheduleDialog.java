package Components;

import Backend.SecHomeSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScheduleDialog extends JDialog {

    SecHomeSystem system = SecHomeSystem.getSingletonSystem();
    final String[] systemType = new String[]{"System On","System Off"};
    final String[] dateType = new String[]{"Daily","Weekly","Weekdays","Weekends"};
    final Vector<Integer> hourType = new Vector<>(IntStream.range(0, 23 + 1).boxed().collect(Collectors.toList()));
    final Vector<Integer> minType = new Vector<>(IntStream.range(0, 60 + 1).boxed().collect(Collectors.toList()));

    ScheduleDialog() {
        this.setLayout(new FlowLayout());
        this.setBounds(200,200,450,130);
        this.setTitle("Schedule");
        JComboBox systemBox = new JComboBox(systemType);
        JComboBox dateBox = new JComboBox(dateType);
        JComboBox hourBox = new JComboBox((Vector) hourType);
        JComboBox minBox = new JComboBox((Vector) minType);
        JButton submit = new JButton("Submit");

        this.add(systemBox);
        this.add(dateBox);
        this.add(hourBox);
        this.add(minBox);
        this.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.setVisible(true);

    }

}
