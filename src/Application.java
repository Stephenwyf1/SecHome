import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class Application extends JFrame{

    private static SecHomeSystem system;
    JFrame frame;
    JPanel panel = new JPanel(null);
    JButton btn1 = new JButton("Install");
    JButton btn2 = new JButton("Uninstall");
    JButton btn3 = new JButton("Active");
    JButton btn4 = new JButton("Inactive");
    JButton btn5 = new JButton("Schedual");
    JButton btn6 = new JButton("Help");

    public static void main(String[] args) {

        system = SecHomeSystem.getSingletonSystem();
        Application app = new Application();
        app.initialize();

    }


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
        Room[][] layout = system.roomLayOut;
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
        TextArea g = new TextArea(lable);

        if (room.getSensor() != null) {
            Sensor s = room.getSensor();
            switch (s.state) {
                case ON -> g.setBackground(Color.GREEN);
                case OFF -> g.setBackground(Color.RED);
                case ERROR -> g.setBackground(Color.YELLOW);
            }
        }

        g.setBounds(room.getX() * rate + off,room.getY() * rate + off,40,20);

        panel.add(g);
    }


}
