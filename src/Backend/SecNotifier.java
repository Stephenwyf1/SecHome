package Backend;

public class SecNotifier extends BaseNotifierDecorator {
    public SecNotifier(SystemNotifier notifier) {
        super(notifier);
    }
    @Override
    public void notifyEmergency() {
        super.notifyEmergency();
        System.out.println("Calling 911 for security emergency!!!");
    }
}
