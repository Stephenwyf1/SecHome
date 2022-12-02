import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Backend.Room;
import Backend.SecHomeSystem;
import Backend.Sensor;
import Components.*;
import Components.Dialog;

public class Application extends JFrame{

    private static SecHomeSystem system;
    static JFrame frame;
    static JPanel panel = new JPanel(null);
    static JButton btn1 = new JButton("Install");
    static JButton btn2 = new JButton("Uninstall");
    static JButton btn3 = new JButton("Active");
    static JButton btn4 = new JButton("Inactive");
    static JButton btn5 = new JButton("Schedual");
    static JButton btn6 = new JButton("Help");

    public void initialize() {
        frame = new JFrame("Control Panel");
        frame.setSize(1000,800);
        frame.setLocation(100,100);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // initialize basic panel
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.9;
        c.weighty = 1;
        c.gridheight = 6;
        c.gridwidth = 1;
        frame.add(panel,c);

        // initilize bottons
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.1;
        c.gridheight = 1;
        c.gridwidth = 1;
        frame.add(btn1,c);

        c.gridy = 1;
        frame.add(btn2,c);

        c.gridy = 2;
        frame.add(btn3,c);

        c.gridy = 3;
        frame.add(btn4,c);

        c.gridy = 4;
        frame.add(btn5,c);

        c.gridy = 5;
        frame.add(btn6,c);

        // initilize rooms location
        Room[][] layout = system.getRoomLayOut();
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout.length; j++) {
                if (layout[i][j] != null) {
                    paintSingleRoom(layout[i][j]);
                }
            }
        }

        frame.setVisible(true);
    }


    public void paintSingleRoom(Room room) {

        int rate = 80;
        int off = 20;

        String lable = "(" + room.getX() + "," + room.getY() + ")";
        JButton g = new JButton(lable);

        if (room.getSensor() != null) {
            Sensor s = room.getSensor();
            switch (s.getState()) {
                case ON -> g.setForeground(Color.GREEN);
                case OFF -> g.setForeground(Color.RED);
                case ERROR -> g.setForeground(Color.YELLOW);
            }
        }

        g.setBounds(room.getX() * rate + off,room.getY() * rate + off,40,20);
        g.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomInfoDialog dialog = new RoomInfoDialog(room);
            }
        });

        panel.add(g);
    }

    public static void main(String[] args) {

        system = SecHomeSystem.getSingletonSystem();
        Application app = new Application();
        app.initialize();
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog newDialog = new Dialog(frame,"Install");
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog newDialog = new Dialog(frame,"Unistall");
            }
        });

        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpDialog newDialog = new HelpDialog();
            }
        });


    }


}
