package Backend;

public class MotionSensor extends Sensor {
    Camera camera;
    public MotionSensor(SensorType sensorType, Camera camera, int price) {
        super(sensorType, price);
        this.camera = camera;
        this.price = 2314 + (this.camera == null ? 0 : 1000);
    }
}
