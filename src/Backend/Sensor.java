package Backend;


import java.util.UUID;

public abstract class Sensor {
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
        SecHomeSystem system = SecHomeSystem.getSingletonSystem();
        system.getErrorSensors().add(system.location.get(this.getId()));
        system.setStatus(SystemStatus.ALERTING);
    }
    void setPrice(int price) {this.price = price;}
    int getPrice() {
        return this.price;
    }

    public void setState(SensorState state) {
        this.state = state;
    }
    public SensorType getSensorType() { return this.sensorType; }
    public SensorState getSensorState() { return this.state; }
    public UUID getId() { return this.id; }
    abstract public boolean hasCamera();

    public SensorState getState(){
        return this.state;
    };
}
