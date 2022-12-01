import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

public class SecHomeSystem {

    static String serviceContractId;
    static String customerName;
    static String addressOfProperty;
    static String contactNumber1;
    static String contactNumber2;
    static String customerID;
    static Date effectiveDates;

    Room[][] roomLayOut;
    // GUI;

    // to locate a Room by reported information from a sensor
    HashMap<UUID, Room> location = new HashMap<>();
    private volatile static SecHomeSystem singletonSecHomeSystem;
    String emgNumber = "12311325155";
    private SecHomeSystem() {}

    public static void main(String[] args) throws IOException, ParseException {
        String customerInfo = "setting.properties";
        Properties props = new Properties();
        props.load(new java.io.FileInputStream(customerInfo));
        serviceContractId = props.getProperty("serviceContractId");
        customerName = props.getProperty("customerName");
        addressOfProperty = props.getProperty("addressOfProperty");
        contactNumber1 = props.getProperty("contactNumber1");
        contactNumber2 = props.getProperty("contactNumber2");
        customerID = props.getProperty("customerID");
        effectiveDates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(props.getProperty("effectiveDates"));

    }
    public static SecHomeSystem getSingletonControlPanel() {
        if (singletonSecHomeSystem == null) {
            singletonSecHomeSystem = new SecHomeSystem();
        }
        return singletonSecHomeSystem;
    }

//    public FireAlarmBill buildFireAlarmBill() {
//        FireAlarmBillBuilder builder = new FireAlarmBillBuilder();
//        builder.
//    }
//
//    public SecurityBill buildFireAlarmBill() {
//
//    }

    public void alert(UUID sensorId) {
        Room room = location.get(sensorId);
        int x = room.getX();
        int y = room.getY();
        System.out.println("Sensor: "+sensorId.toString() +
                "\nRoom: "+"("+x+","+y+") has reported an Emergency!, Calling:"+this.emgNumber);
        BaseNotifier notifier = new BaseNotifier(emgNumber);
        if (room.getSensor().sensorType == SensorType.FIRE) {
            FireNotifier fireNotifier = new FireNotifier(notifier);
            fireNotifier.notifyEmergency();
        } else if (room.getSensor().sensorType == SensorType.SEC) {
            SecNotifier secNotifier = new SecNotifier(notifier);
            secNotifier.notifyEmergency();
        }
        // TODO: notify the GUI that this sensor should be red
    }
}
