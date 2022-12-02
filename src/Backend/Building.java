package Backend;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

abstract class Building {
    private final UUID id;
    Building() {
        this.id = UUID.randomUUID();
    }
    abstract public int computePrice();
    abstract public int computePrice(SensorType sensorType);
    abstract public ArrayList<Room> installSensor(SensorType sensorType, Boolean isNeedCamera);
    abstract public void turnOnSensor();
    abstract public void turnOffSensor();
    abstract public void uninstallSensor();
    abstract public List<Room> getRooms();
    abstract public List<Room> getRooms(SensorType sensorType);
    public UUID getId(){
        return this.id;
    };

}
