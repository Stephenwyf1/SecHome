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
        BillBuilder builder;
        if (sensorType == SensorType.SEC) {
            builder = new SecurityBillBuilder();
            unitPrice = SecHomeSystem.motionSensorPrice + (section.isInstalledCamera ? SecHomeSystem.cameraPrice : 0);
        } else {
            builder = new FireAlarmBillBuilder();
            unitPrice = SecHomeSystem.fireSensorPrice;
        }
        Bill bill = builder.buildAddressOfProperty(system.addressOfProperty)
                .buildContactNumber1(system.contactNumber1)
                .buildContactNumber2(system.contactNumber2)
                .buildCustomerID(system.customerID)
                .buildCustomerName(system.customerName)
                .buildEffectiveDates(system.effectiveDates)
                .buildServiceContractId(system.serviceContractId)
                .buildPriceDetail(unitPrice)
                .buildCost(section)
                .buildCoverageDetail(section.getRooms(sensorType))
                .getResult();

        return bill.toString();
    }
}
