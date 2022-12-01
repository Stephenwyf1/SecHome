import java.util.UUID;

class Sensor {
    SensorState state = SensorState.OFF;

    DeviceListener listener;
    final SensorType sensorType;
    final UUID id;
    int price = 0;

    Sensor(SensorType sensorType) {
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
        ControlPanel.getSingletonControlPanel().alert(this.id);
    }

    int getPrice() {
        return this.price;
    }

}
