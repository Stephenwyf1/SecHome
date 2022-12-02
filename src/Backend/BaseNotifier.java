package Backend;
public class BaseNotifier implements SystemNotifier {

    String emergencyContact1;
    String emergencyContact2;

    public BaseNotifier(String emergencyContact1, String emergencyContact2) {
        this.emergencyContact1 = emergencyContact1;
        this.emergencyContact2 = emergencyContact2;
    }
    @Override
    public void notifyEmergency(String info) {
        System.out.println(info);
    }
}
