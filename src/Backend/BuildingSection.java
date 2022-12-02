package Backend;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class BuildingSection extends Building {
    SensorType currentInstallation;
    Boolean isInstalledCamera;
    HashSet<Building> collection;
    BuildingSection() {
        super();
        this.collection = new HashSet<>();
        this.isInstalledCamera = false;
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
    public ArrayList<Room> installSensor(SensorType sensorType, Boolean isNeedCamera) throws Exception {
        ArrayList<Room> rooms = new ArrayList<>();
        this.currentInstallation = sensorType;
        this.isInstalledCamera = isNeedCamera;
        for (Building b : collection) {
            rooms.addAll(b.installSensor(sensorType, isNeedCamera));
        }
        return rooms;
    }

    @Override
    public ArrayList<Room> turnOnSensor() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Building b : collection) {
            rooms.addAll(b.turnOnSensor());
        }
        return rooms;
    }

    @Override
    public ArrayList<Room> turnOffSensor() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (Building b : collection) {
            rooms.addAll(b.turnOffSensor());
        }
        return rooms;
    }

    @Override
    public ArrayList<Room> uninstallSensor() {
        ArrayList<Room> rooms = new ArrayList<>();
        this.isInstalledCamera = false;
        this.currentInstallation = null;
        for (Building b : collection) {
            rooms.addAll(b.uninstallSensor());
        }
        return rooms;
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
