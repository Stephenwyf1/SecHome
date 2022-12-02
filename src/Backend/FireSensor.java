
package Backend;

public class FireSensor extends Sensor {
    public FireSensor(SensorType sensorType, int price) {
        super(sensorType, price);
    }
    public boolean hasCamera() {return false;}
}
