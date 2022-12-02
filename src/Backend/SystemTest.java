package Backend;

import java.util.List;


public class SystemTest {
    public static void main(String[] args) {
        // magic number 10, indicate the size of the building.
        int[][] building = new int[10][10];
        BuildingSection section1 = new BuildingSection();
        BuildingSection section2 = new BuildingSection();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                section1.addBuilding(new Room(i, j));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 5; j < 10; j++) {
                section2.addBuilding(new Room(i, j));
            }
        }
        section1.installSensor(SensorType.FIRE, true);
        section2.installSensor(SensorType.SEC, false);
        List<Room> roomList1 = section1.getRooms();
        List<Room> roomList2 = section2.getRooms();
        for (Room room : roomList1) {
            building[room.getX()][room.getY()] = room.sensor.sensorType == SensorType.FIRE ? 1 : 0;
        }
        for (Room room : roomList2) {
            building[room.getX()][room.getY()] = room.sensor.sensorType == SensorType.FIRE ? 1 : 0;
        }
        showBuilding(building);
        roomList1.get(1).sensor.report();
        System.out.println(section1.computePrice());
        System.out.println(section2.computePrice());
    }

    public static void showBuilding(int[][] building) {
        for (int[] ints : building) {
            for (int j = 0; j < building[0].length; j++) {
                System.out.print(ints[j] + ",");
            }
            System.out.println();
        }
    }
}
