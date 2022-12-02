import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BillBuilder {

    public BillBuilder buildServiceContractId(String serviceContractId);
    public BillBuilder buildCustomerName(String customerName);
    public BillBuilder buildAddressOfProperty(String addressOfProperty);
    public BillBuilder buildContactNumber1(String contactNumber1);
    public BillBuilder buildContactNumber2(String contactNumber2);
    public BillBuilder buildCustomerID(String customerID);
    public BillBuilder buildEffectiveDates(Date effectiveDates);
    public BillBuilder buildCoverageDetail(List<Room> rooms);
    public BillBuilder buildPriceDetail(int price);
    public BillBuilder buildCost(BuildingSection section);
    public Bill getResult();

}
