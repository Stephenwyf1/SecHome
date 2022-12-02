package Components;

import javax.swing.*;

public class HelpDialog extends JDialog {
    private static String title = "Help";
    private static String content =
            "<html>" +
                    "<body>" +
                    "<p>Room colored GREEN means sensor active<br/>" +
                    "Room colored YELLOW means sensor inactive<br/>" +
                    "Room colored RED means sensor error<br/>" +
                    "Room not colored means No Sensor installed in this room" +
                    "</body>" +
                    "</html>";

    private static JLabel lable = new JLabel(content);

    public HelpDialog() {
        this.setBounds(200,200,400,100);
        this.add(lable);
        this.setVisible(true);
    }
}
