package Backend;

public class BillDirector {
    public static volatile BillDirector billDirector;
    private BillDirector() {}

    public static BillDirector getSingletonDirector() {
        if (BillDirector.billDirector == null) {
            BillDirector.billDirector = new BillDirector();
        }
        return billDirector;
    }

    public String makeBill(SensorType sensorType, BuildingSection section) {
        SecHomeSystem system = SecHomeSystem.getSingletonSystem();
        int unitPrice;
        int cameraPrice = 0;
        BillBuilder builder;
        if (sensorType == SensorType.SEC) {
            builder = new SecurityBillBuilder();
            unitPrice = SecHomeSystem.motionSensorPrice;
            cameraPrice = SecHomeSystem.cameraPrice;
        } else {
            builder = new FireAlarmBillBuilder();
            unitPrice = SecHomeSystem.fireSensorPrice;
        }
        Bill bill = builder.buildAddressOfProperty(system.getAddressOfProperty())
                .buildContactNumber1(system.contactNumber1)
                .buildContactNumber2(system.contactNumber2)
                .buildCustomerID(system.getCustomerID())
                .buildCustomerName(system.customerName)
                .buildEffectiveDates(system.getEffectiveDates())
                .buildServiceContractId(system.getServiceContractId())
                .buildPriceDetail(unitPrice)
                .buildCameraPrice(cameraPrice)
                .buildCost(section)
                .buildCoverageDetail(section.getRooms(sensorType))
                .getResult();

        return bill.toString();
    }
}
