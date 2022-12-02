
package Backend;

public class FireAlarmBill extends Bill{
    String fireAlarmInfo = "Fire alarm system bill details below: \n";
    public FireAlarmBill() {
        super();
    }

    @Override
    public String toString() {
        return fireAlarmInfo + super.toString();
    }
}
