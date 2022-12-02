package Backend;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecurityBillBuilder implements BillBuilder {

    SecurityBill bill;

    public SecurityBillBuilder() {
        this.bill = new SecurityBill();
    }

    @Override
    public BillBuilder buildServiceContractId(String serviceContractId) {
        this.bill.serviceContractId = serviceContractId;
        return this;
    }

    @Override
    public BillBuilder buildCustomerName(String customerName) {
        this.bill.customerName = customerName;
        return this;
    }

    @Override
    public BillBuilder buildAddressOfProperty(String addressOfProperty) {
        this.bill.addressOfProperty = addressOfProperty;
        return this;
    }

    @Override
    public BillBuilder buildContactNumber1(String contactNumber1) {
        this.bill.contactNumber1 = contactNumber1;
        return this;
    }

    @Override
    public BillBuilder buildContactNumber2(String contactNumber2) {
        this.bill.contactNumber2 = contactNumber2;
        return this;
    }

    @Override
    public BillBuilder buildCustomerID(String customerID) {
        this.bill.customerID = customerID;
        return this;
    }

    @Override
    public BillBuilder buildEffectiveDates(Date effectiveDates) {
        this.bill.effectiveDates = effectiveDates;
        return this;
    }

    @Override
    public BillBuilder buildCoverageDetail(List<Room> rooms) {
        StringBuilder builder = new StringBuilder();
        for (Room room : rooms) {
            builder.append(String.format("Room: (%d, %d), RoomID: %s, Sensor: %s, Sensor type: %s\n", room.x, room.y, room.getId(), room.sensor.id, room.sensor.sensorType));
        }
        this.bill.coverageDetail = builder.toString();
        return this;
    }

    @Override
    public BillBuilder buildPriceDetail(int price) {
        this.bill.pirce = price;
        return this;
    }

    @Override
    public BillBuilder buildCost(BuildingSection section) {
        this.bill.totalCost = section.computePrice(SensorType.SEC);
        return this;
    }

    @Override
    public Bill getResult() {
        return this.bill;
    }
}
