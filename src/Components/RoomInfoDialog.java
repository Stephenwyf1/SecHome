package Components;

import javax.swing.*;
import Backend.Room;

public class RoomInfoDialog extends JDialog {
    public RoomInfoDialog(Room room) {
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
//        text.append("Room ID:" + room.id);
        text.append("Location:" + room.getX() + "," + room.getY() + "\n");
        if (room.getSensor() == null) {
            text.append("Sensor Type: Not Installed\n");
        } else {
            text.append("Sensor Type:" + room.getSensor().getSensorType() + "\n");
            text.append("Sensor State:" + room.getSensor().getSensorState() + "\n");
            text.append("Camera:" + (room.getSensor().hasCamera()? "Yes" : "No") + "\n");
        }

        this.setBounds(200,200,200,200);
        this.add(text);
        this.setVisible(true);
    }
}
