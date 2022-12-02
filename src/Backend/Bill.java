package Backend;

import java.util.Date;

public class Bill {
    String serviceContractId;
    String customerName;
    String addressOfProperty;
    String contactNumber1;
    String contactNumber2;
    String customerID;
    Date effectiveDates;
    String coverageDetail;
    int pirce;
    int totalCost;
    public Bill(){}
    @Override
    public String toString() {
        return String.format(
                "        serviceContractId = %s;\n" +
                "        customerName = %s\n" +
                "        addressOfProperty = %s\n" +
                "        contactNumber1 = %s\n" +
                "        contactNumber2 = %s\n" +
                "        customerID = %s\n" +
                "        effectiveDates = %s \n" +
                "        price = %d \n" +
                "        totalCost = %d\n" +
                "        coverageDetail = %s", serviceContractId, customerName, addressOfProperty, contactNumber1, contactNumber2,
                customerID, effectiveDates, pirce, totalCost, coverageDetail.length() == 0 ? "None" : coverageDetail);
    }
}
