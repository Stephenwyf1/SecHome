package Backend;

public class SecurityBill extends Bill{

    String securityInfo = "Security system bill details below: \n";
    public SecurityBill() {
        super();
    }

    @Override
    public String toString() {
        return securityInfo + super.toString();
    }
}
