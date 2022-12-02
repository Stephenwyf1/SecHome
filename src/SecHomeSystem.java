import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SecHomeSystem {
    static final String serviceContractId;
    static Properties props;
    static final String customerName;
    static final String addressOfProperty;
    String contactNumber1;
    String contactNumber2;
    static final String customerID;
    static final Date effectiveDates;
    String password;
    static int fireSensorPrice;
    static int motionSensorPrice;
    static int cameraPrice;
    Room[][] roomLayOut;
    BuildingSection building;
    BuildingSection section1;
    BuildingSection section2;
    BuildingSection section3;

    // to locate a Room by reported information from a sensor
    HashMap<UUID, Room> location = new HashMap<>();
    private volatile static SecHomeSystem singletonSecHomeSystem;
    String emgNumber = "12311325155";

    static {
        String customerInfo = "setting.properties";
        props = new Properties();
        try {
            props.load(new java.io.FileInputStream(customerInfo));
            serviceContractId = props.getProperty("serviceContractId");
            addressOfProperty = props.getProperty("addressOfProperty");
            customerName = props.getProperty("customerName");
            effectiveDates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(props.getProperty("effectiveDates"));
            customerID = props.getProperty("customerID");
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private SecHomeSystem(){
        try {
            contactNumber1 = props.getProperty("contactNumber1");
            contactNumber2 = props.getProperty("contactNumber2");
            password = props.getProperty("password");
            fireSensorPrice = Integer.parseInt(props.getProperty("fireSensorPrice"));
            motionSensorPrice = Integer.parseInt(props.getProperty("motionSensorPrice"));
            cameraPrice = Integer.parseInt(props.getProperty("cameraPrice"));
            roomLayOut = new Room[10][10];
            building = new BuildingSection();
            section1 = new BuildingSection();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 10; j++){
                    Room room = new Room(i, j);
                    section1.addBuilding(room);
                    roomLayOut[i][j] = room;
                }
            }
            section2 = new BuildingSection();
            for (int i = 3; i < 7; i++) {
                for (int j = 0; j < 10; j++){
                    Room room = new Room(i, j);
                    section2.addBuilding(room);
                    roomLayOut[i][j] = room;
                }
            }
            section3 = new BuildingSection();
            for (int i = 7; i < 10; i++) {
                for (int j = 0; j < 10; j++){
                    Room room = new Room(i, j);
                    section3.addBuilding(room);
                    roomLayOut[i][j] = room;
                }
            }

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.exit(2);
        }
    }

    public static SecHomeSystem getSingletonSystem(){
        if (singletonSecHomeSystem == null) {
            singletonSecHomeSystem = new SecHomeSystem();
        }
        return singletonSecHomeSystem;
    }

    public String buildBill(SensorType sensorType, BuildingSection section) {
        String bill = BillDirector.getSingletonDirector().makeBill(sensorType, section);
        System.out.println(bill);
        return bill;
    }

    public boolean verifyPassword(String password) {
        return password.equals(this.password);
    }

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
