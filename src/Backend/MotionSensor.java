package Backend;


public class MotionSensor extends Sensor {
    Camera camera;
    public MotionSensor(SensorType sensorType, Camera camera, int price) {
        super(sensorType, price);
        this.camera = camera;
        this.price = price;
    }
    public boolean hasCamera(){ return this.camera == null; }
}
