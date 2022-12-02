package Backend;

import java.util.UUID;

public class Sensor {
    SensorState state = SensorState.OFF;
    DeviceListener listener;
    final SensorType sensorType;
    final UUID id;
    int price = 0;

    Sensor(SensorType sensorType, int price) {
        this.price = price;
        this.sensorType = sensorType;
        this.id = UUID.randomUUID();
        this.listener = new DeviceListener();
    }

    public void turnOn() {
        this.state = SensorState.ON;
        new Thread(() -> listener.startListen(this)).start();
    }
    public void turnOff() {
        this.state = SensorState.OFF;
    }

    public void report() {
        this.state = SensorState.ERROR;
        SecHomeSystem.getSingletonSystem().alert(this.id);
    }
    void setPrice(int price) {this.price = price;}
    int getPrice() {
        return this.price;
    }

    public SensorState getState() {
        return this.state;
    }
    public SensorType getSensorType() {return this.sensorType; }
    public DeviceListener getDeviceListener() {return this.listener; }

}
