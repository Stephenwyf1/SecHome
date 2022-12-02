package Backend;

public class DeviceListener {
    int threshold;
    int temperature;

    DeviceListener() {
        this.temperature = 0;
        this.threshold = 100;
    }
    public void startListen(Sensor sensor) {
        while (true) {
            if (temperature > threshold) {
                 sensor.report();
                break;
            }
            if (sensor.state == SensorState.OFF) {
                break;
            }
        }
    }
}
