package Components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.*;

import Backend.*;

public class Application extends JFrame{

    private static SecHomeSystem system;
    static JFrame frame;
    static JPanel panel = new JPanel(null);
    static HashMap<UUID,JButton> roomBtnMap = new HashMap<>();
    static JButton btn1 = new JButton("Install/Uninstall");
    static JButton btn2 = new JButton("Active/Inactive");
    static JButton btn3 = new JButton("Schedule");
    static JButton btn4 = new JButton("Build Bill");
    static JButton btn5 = new JButton("System Test");
    static JButton btn6 = new JButton("Settings");
    static JButton btn7 = new JButton("Help");

    public static void initialize() {
        frame = new JFrame("Control Panel");
        frame.setSize(1200,800);
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
        c.gridheight = 8;
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

        c.gridy = 6;
        frame.add(btn7,c);

        // initilize rooms location
        Room[][] layout = system.getRoomLayOut();
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout.length; j++) {
                if (layout[i][j] != null) {
                    paintSingleRoom(layout[i][j]);
                }
            }
        }

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }


    public static void paintSingleRoom(Room room) {

        int rate = 80;
        int off = 50;

        String label = "(" + room.getX() + "," + room.getY() + ")";
        JButton g;
        if (roomBtnMap.get(room.getId()) == null) {
            g = new JButton(label);
            roomBtnMap.put(room.getId(),g);
            g.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RoomInfoDialog dialog = new RoomInfoDialog(room);
                }
            });
        } else {
            g = roomBtnMap.get(room.getId());
        }

        if (room.getSensor() != null) {
            Sensor s = room.getSensor();
            Color color = switch (s.getState()) {
                case ON -> Color.GREEN;
                case OFF -> Color.RED;
                case ERROR -> Color.YELLOW;
            };
            g.setForeground(color);
        } else {
            g.setForeground(Color.black);
        }

        g.setBounds(room.getX() * rate + off,room.getY() * rate + off,40,20);
        panel.add(g);
    }

    public void listen () {
        while (system.getStatus() != SystemStatus.ALERTING) continue;
        return;
    }

    public static void main(String[] args) {

        System.out.println("Enjoy using the SecHome system~");
        system = SecHomeSystem.getSingletonSystem();
        Application app = new Application();
        app.initialize();
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstallAndUninstallDialog newInstallAndUninstallDialog = new InstallAndUninstallDialog();
            }
        });

        // Active and Inactive
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActiveAndInactiveDialog newActiveAndInactiveDialog = new ActiveAndInactiveDialog();
            }
        });

        // Schedule
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduleDialog newScheduleDialog = new ScheduleDialog();
            }
        });


        // BuildBill
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuildBillDialog newBuildBillDialog = new BuildBillDialog();
            }
        });

        // System Test
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Room> list = system.getASensorForTest();
                    ArrayList<String> alert = new ArrayList<>();
                    for (Room each : list) {
                        Application.paintSingleRoom(each);
                        alert.add(system.notifyEmergency(each));
                        Application.panel.updateUI();
                    }
                    new AlertDialog(list,alert);
                    new SuccessDialog();
                } catch (Exception exception) {
                    new ErrorDialog(exception.getMessage());
                }
            }
        });

        // Settings
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsDialog();
            }
        });

        // Help info
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HelpDialog newDialog = new HelpDialog();
            }
        });

        Thread t = new Thread(() -> {
            while (true) {
                for(Schedule s : system.getScheduler()) {
                    if (s.canStart()) {
                        s.startJob();
                        for(Map.Entry<UUID,Room> entry : system.getRoomMap().entrySet()) {
                            paintSingleRoom(entry.getValue());
                        }
                        Application.panel.updateUI();
                    }
                }
            }
        });

        t.start();

    }


}
