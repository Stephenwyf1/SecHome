
package Backend;

public class FireNotifier extends BaseNotifierDecorator {
    public FireNotifier(SystemNotifier notifier) {
        super(notifier);
    }
    @Override
    public void notifyEmergency(String info) {
        super.notifyEmergency(info);
        System.out.println("Calling 911 for fire emergency!!!\n" + info);
    }
}
