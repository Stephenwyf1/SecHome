import java.util.HashMap;
import java.util.UUID;

public class ControlPanel {
    // to locate a Room by reported information from a sensor
    HashMap<UUID, Room> location = new HashMap<>();
    private volatile static ControlPanel singletonControlPanel;
    String emgNumber = "12311325155";
    private ControlPanel() {}

    public static ControlPanel getSingletonControlPanel() {
        if (singletonControlPanel == null) {
            singletonControlPanel = new ControlPanel();
        }
        return singletonControlPanel;
    }

    public void alert(UUID sensorId) {
        Room room = location.get(sensorId);
        int x = room.getX();
        int y = room.getY();
        System.out.println("Sensor: "+sensorId.toString() +
                "\nRoom: "+"("+x+","+y+") has reported an Emergency!, Calling:"+this.emgNumber);
    }
}
