package Backend;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room extends Building {
    final int x;
    final int y;
    private Sensor sensor = null;

    private UUID sectionID;
    public Room(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int computePrice() {
        return this.hasSensor() ? this.sensor.getPrice() : 0;
    }

    @Override
    public int computePrice(SensorType sensorType) {
//        System.out.println(this.x+":"+this.y+": "+((this.hasSensor() && this.getSensor().getSensorType() == sensorType) ? this.sensor.getPrice() : 0 ));
        return (this.hasSensor() && this.getSensor().getSensorType() == sensorType) ? this.sensor.getPrice() : 0;
    }

    @Override
    public ArrayList<Room> installSensor(SensorType sensorType, Boolean isNeedCamera) throws Exception {
        SensorType type = SecHomeSystem.getSingletonSystem().sectionMap.get(this.getSectionID()).currentInstallation;
        if (type != null && type != sensorType) {
            throw new Exception("A section can only have one type of sensor");
        }
        ArrayList<Room> rooms = new ArrayList<>();
        if (!this.hasSensor()) {
            this.sensor = switch (sensorType) {
                case FIRE -> new FireSensor(sensorType, SecHomeSystem.fireSensorPrice);
                case SEC -> new MotionSensor(sensorType, isNeedCamera ? new Camera() : null,
                        SecHomeSystem.motionSensorPrice + (isNeedCamera ? SecHomeSystem.cameraPrice : 0));
            };
            System.out.println(this.getSensor().price);
            SecHomeSystem.getSingletonSystem().location.put(sensor.id, this);
            rooms.add(this);
        }
        else if (this.getSensor().getSensorType() != sensorType){
            throw new Exception("This room (id: "+this.getId()+") already had a sensor. " +
                    "Please uninstall it before you try to install a new one.");
        }
        return rooms;
    }

    @Override
    public void uninstallSensor() {
        if (this.hasSensor()) {
            SecHomeSystem.getSingletonSystem().location.remove(sensor.id);
            this.sensor = null;
        }
    }

    @Override
    public List<Room> getRooms() {
        List<Room> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
    public List<Room> getRooms(SensorType sensorType) {
        List<Room> list = new ArrayList<>();
        if (this.hasSensor() && this.sensor.sensorType == sensorType) list.add(this);
        return list;
    }

    public void turnOnSensor() {
        if (this.hasSensor()) {
            sensor.turnOn();
        }
    }

    public void turnOffSensor() {
        if (this.hasSensor()) {
            sensor.turnOff();
        }
    }

    public UUID getSectionID() {
        return sectionID;
    }

    public void setSectionID(UUID sectionID) {
        this.sectionID = sectionID;
    }

    public boolean hasSensor() {
        return this.sensor != null;
    }

    public Sensor getSensor() {return this.sensor; }
    public int getX() {return this.x;}
    public int getY() {return this.y;}
}
