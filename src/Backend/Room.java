package Backend;

import java.util.ArrayList;
import java.util.List;

public class Room extends Building {
    final int x;
    final int y;
    Sensor sensor = null;
    public Room(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int computePrice() {
        return this.sensor == null ? 0 : this.sensor.getPrice();
    }

    @Override
    public int computePrice(SensorType sensorType) {
        return this.sensor != null && this.sensor.sensorType == sensorType ? this.sensor.getPrice() : 0;
    }

    @Override
    public void installSensor(SensorType sensorType, Boolean isNeedCamera) {
        if (!this.hasSensor()) {
            this.sensor = switch (sensorType) {
                case FIRE -> new FireSensor(sensorType, SecHomeSystem.fireSensorPrice);
                case SEC -> new MotionSensor(sensorType, isNeedCamera ? new Camera() : null,
                        SecHomeSystem.motionSensorPrice + (isNeedCamera ? SecHomeSystem.cameraPrice : 0));
            };
            SecHomeSystem.getSingletonSystem().location.put(sensor.id, this);
        }
        else {
            System.out.println("This room (id: "+this.id+") already had a sensor. " +
                    "Please uninstall it before you try to install a new one.");
        }
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
        if (this.sensor.sensorType == sensorType) list.add(this);
        return list;
    }
    public void turnOnSensor() {
        if (this.hasSensor()) {
            sensor.turnOn();
        } else
            throw new NullPointerException("Backend.Sensor not installed");
    }

    public void turnOffSensor() {
        if (this.hasSensor()) {
            sensor.turnOff();
        } else
            throw new NullPointerException("Backend.Sensor not installed");
    }

    public boolean hasSensor() {
        return this.sensor != null;
    }

    public Sensor getSensor() {return this.sensor; }
    public int getX() {return this.x;}
    public int getY() {return this.y;}
}
