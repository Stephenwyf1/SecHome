import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BuildingSection extends Building {
    SensorType currentInstallation;
    Boolean isInstalledCamera;
    HashSet<Building> collection;
    BuildingSection() {
        super();
        this.collection = new HashSet<>();
    }

    @Override
    public int computePrice() {
        int sum = 0;
        for (Building b : collection) {
            sum += b.computePrice();
        }
        return sum;
    }

    @Override
    public int computePrice(SensorType sensorType) {
        int sum = 0;
        for (Building b : collection) {
            sum += b.computePrice(sensorType);
        }
        return sum;
    }

    @Override
    public void installSensor(SensorType sensorType, Boolean isNeedCamera) {
        this.currentInstallation = sensorType;
        this.isInstalledCamera = isNeedCamera;
        for (Building b : collection) {
            b.installSensor(sensorType, isNeedCamera);
        }
    }

    @Override
    public void turnOnSensor() {
        for (Building b : collection) {
            b.turnOnSensor();
        }
    }

    @Override
    public void turnOffSensor() {
        for (Building b : collection) {
            b.turnOffSensor();
        }
    }

    @Override
    public void uninstallSensor() {
        this.isInstalledCamera = false;
        this.currentInstallation = null;
        for (Building b : collection) {
            b.uninstallSensor();
        }
    }

    @Override
    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        for (Building b : collection) {
            rooms.addAll(b.getRooms());
        }
        return rooms;
    }

    @Override
    public List<Room> getRooms(SensorType sensorType) {
        List<Room> rooms = new ArrayList<>();
        for (Building b : collection) {
            rooms.addAll(b.getRooms(sensorType));
        }
        return rooms;
    }
    public void addBuilding(Building building) {
        collection.add(building);
    }

    public void removeBuilding(Building building) {
        collection.remove(building);
    }
}
