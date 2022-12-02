package Backend;
public class BaseNotifier implements SystemNotifier {

    String emergencyContact;

    public BaseNotifier(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    @Override
    public void notifyEmergency() {
        System.out.println("Calling emergency contact number: "+ emergencyContact);
    }
}
