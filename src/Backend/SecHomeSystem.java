package Backend;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SecHomeSystem {
    private String serviceContractId;
    String customerName;
    private String addressOfProperty;
    String contactNumber1;
    String contactNumber2;
    private String customerID;
    private Date effectiveDates;
    String password;
    static int fireSensorPrice;
    static int motionSensorPrice;
    static int cameraPrice;
    Room[][] roomLayOut;
    BuildingSection building;
    BuildingSection section1;
    BuildingSection section2;
    BuildingSection section3;
    SystemStatus status;

    // to locate a Room by reported information from a sensor
    HashMap<UUID, Room> location;
    // to locate a Room by its id
    HashMap<UUID, Room> map;

    HashMap<UUID, BuildingSection> sectionMap;
    private volatile static SecHomeSystem singletonSecHomeSystem;
    private String responseCode;
    private ArrayList<Room> testSensors;

    private SecHomeSystem(){
        try {
            String customerInfo = "setting.properties";
            Properties props = new Properties();
            try {
                props.load(new java.io.FileInputStream(customerInfo));
                responseCode = props.getProperty("responseCode");
                serviceContractId = props.getProperty("serviceContractId");
                addressOfProperty = props.getProperty("addressOfProperty");
                customerName = props.getProperty("customerName");
                effectiveDates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(props.getProperty("effectiveDates"));
                customerID = props.getProperty("customerID");
                contactNumber1 = props.getProperty("contactNumber1");
                contactNumber2 = props.getProperty("contactNumber2");
                password = props.getProperty("password");
                fireSensorPrice = Integer.parseInt(props.getProperty("fireSensorPrice"));
                motionSensorPrice = Integer.parseInt(props.getProperty("motionSensorPrice"));
                cameraPrice = Integer.parseInt(props.getProperty("cameraPrice"));
            } catch (IOException | ParseException e) {
                System.exit(2);
            }

            this.location = new HashMap<>();
            this.map = new HashMap<>();
            this.sectionMap = new HashMap<>();

            roomLayOut = new Room[10][10];
            building = new BuildingSection();
            section1 = new BuildingSection();
            building.addBuilding(section1);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 10; j++){
                    Room room = new Room(i, j);
                    map.put(room.getId(), room);
                    room.setSectionID(section1.getId());
                    section1.addBuilding(room);
                    roomLayOut[i][j] = room;
                }
            }
            section2 = new BuildingSection();
            building.addBuilding(section2);
            for (int i = 3; i < 7; i++) {
                for (int j = 0; j < 10; j++){
                    Room room = new Room(i, j);
                    map.put(room.getId(), room);
                    room.setSectionID(section2.getId());
                    section2.addBuilding(room);
                    roomLayOut[i][j] = room;
                }
            }
            section3 = new BuildingSection();
            building.addBuilding(section3);
            for (int i = 7; i < 10; i++) {
                for (int j = 0; j < 10; j++){
                    Room room = new Room(i, j);
                    map.put(room.getId(), room);
                    room.setSectionID(section3.getId());
                    section3.addBuilding(room);
                    roomLayOut[i][j] = room;
                }
            }
            status = SystemStatus.RUNNING;
            sectionMap.put(section1.getId(), section1);
            sectionMap.put(section2.getId(), section2);
            sectionMap.put(section3.getId(), section3);

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.exit(2);
        }
    }

    public void activateSystem() {
        this.building.turnOnSensor();
        this.status = SystemStatus.RUNNING;
    }

    public void deActivateSystem() {
        this.building.turnOffSensor();
        this.status = SystemStatus.SHUNTDOWN;
    }

    public static SecHomeSystem getSingletonSystem(){
        if (singletonSecHomeSystem == null) {
            singletonSecHomeSystem = new SecHomeSystem();
        }
        return singletonSecHomeSystem;
    }

    public String buildBill(SensorType sensorType) {
        String bill = BillDirector.getSingletonDirector().makeBill(sensorType, getBuilding());
        System.out.println(bill);
        return bill;
    }

    public BuildingSection getBuilding() {return this.building;}
    public String getAddressOfProperty() {
        return addressOfProperty;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Date getEffectiveDates() {
        return effectiveDates;
    }

    public String getServiceContractId() {
        return serviceContractId;
    }

    public boolean validateContactNumber(String contactNumber) {
        String pattern = "^\\d{10}$";
        return contactNumber.matches(pattern);
    }

    public void setContactNumber1(String contactNumber1) throws Exception {
        if (validateContactNumber(contactNumber1)) {
            this.contactNumber1 = contactNumber1;
        } else {
            throw new Exception("Illegal Contact Number!");
        }

    }

    public void setContactNumber2(String contactNumber2) throws Exception {
        if (validateContactNumber(contactNumber2)) {
            this.contactNumber2 = contactNumber2;
        } else {
            throw new Exception("Illegal Contact Number!");
        }

    }

    public boolean verifyPassword(String password) {
        return password.equals(this.password);
    }

    public void alert(UUID sensorId) {
        Room room = getRoombySensorId(sensorId);
        int x = room.getX();
        int y = room.getY();
        System.out.println("Sensor: "+sensorId.toString() +
                "\nRoom: "+"("+x+","+y+") has reported an Emergency! Alerting type: "+ room.getSensor().sensorType+
                ". Calling: "+this.contactNumber1 + ". Calling:"+this.contactNumber2);
        if (status == SystemStatus.RUNNING) {
            status = SystemStatus.ALERTING;
            final long timeInterval = 1000;
                new Thread(() -> {
                    while(status == SystemStatus.ALERTING) {
                        BaseNotifier notifier = new BaseNotifier(customerID);
                        if (room.getSensor().sensorType == SensorType.FIRE) {
                            FireNotifier fireNotifier = new FireNotifier(notifier);
                            fireNotifier.notifyEmergency();
                        } else if (room.getSensor().sensorType == SensorType.SEC) {
                            SecNotifier secNotifier = new SecNotifier(notifier);
                            secNotifier.notifyEmergency();
                        }
                        try {
                            Thread.sleep(timeInterval);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
        }

        // TODO: notify the GUI that this sensor should be red
    }

    public boolean verifyResponseCode(String responseCode) {
        return responseCode == this.responseCode;
    }
    public void turnOffAlertings() {
        this.setStatus(SystemStatus.RUNNING);
        for (Room room : testSensors) {
            room.getSensor().setState(SensorState.ON);
        }
    }

    private void setStatus(SystemStatus status) {
        this.status = status;
    }

    public ArrayList<Room> getASensorForTest() throws Exception {
        ArrayList<Room> rooms = new ArrayList<>();
        if (this.location.size() == 0) {
            throw new Exception("Please install at least one sensor to test.");
        } else {
            for (UUID key : this.location.keySet()) {
                location.get(key).getSensor().report();
                rooms.add(location.get(key));
            }
            this.testSensors = rooms;
            return rooms;
        }
    }

    public Room[][] getRoomLayOut() {
        return this.roomLayOut;
    }

    public Room getRoomById(UUID id) {
        return map.get(id);
    }

    public Room getRoombySensorId(UUID id){
        return location.get(id);
    }

    public BuildingSection getSectionById(UUID id) {
        return sectionMap.get(id);
    }

    public HashMap<UUID, Room> getLocation() {
        return location;
    }

    public HashMap<UUID, BuildingSection> getSectionMap() {return this.sectionMap;}

    public ArrayList<Room> turnOnSystem () { return this.building.turnOnSensor();}

    public ArrayList<Room> turnOffSystem () { return this.building.turnOffSensor();}

    public void setPassword(String pwd) {this.password = pwd;};

}
