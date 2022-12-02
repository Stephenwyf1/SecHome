package Backend;

import java.util.List;
import java.util.UUID;

abstract class Building {
    final UUID id;
    Building() {
        this.id = UUID.randomUUID();
    }
    abstract public int computePrice();
    abstract public int computePrice(SensorType sensorType);
    abstract public void installSensor(SensorType sensorType, Boolean isNeedCamera);
    abstract public void turnOnSensor();
    abstract public void turnOffSensor();
    abstract public void uninstallSensor();
    abstract public List<Room> getRooms();
    abstract public List<Room> getRooms(SensorType sensorType);

}