public class MotionSensor extends Sensor {
    Camera camera;
    public MotionSensor(SensorType sensorType, Camera camera) {
        super(sensorType);
        this.camera = camera;
        this.price = 2314 + (this.camera == null ? 0 : 1000);
    }
}
