import java.util.ArrayList;
import java.util.Date;

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
        return null;
    }

    @Override
    public BillBuilder buildContactNumber1(String contactNumber1) {
        return null;
    }

    @Override
    public BillBuilder buildContactNumber2(String contactNumber2) {
        return null;
    }

    @Override
    public BillBuilder buildCustomerID(String customerID) {
        return null;
    }

    @Override
    public BillBuilder buildEffectiveDates(String effectiveDates) {
        return null;
    }

    @Override
    public BillBuilder buildCoverageDetail(ArrayList<Room> rooms) {
        return null;
    }

    @Override
    public BillBuilder buildPriceDetail(int price) {
        return null;
    }

    @Override
    public BillBuilder buildCost(BuildingSection section) {
        return null;
    }
}
