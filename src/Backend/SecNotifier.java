package Backend;


public class SecNotifier extends NotifierDecorator {
    public SecNotifier(SystemNotifier notifier) {
        super(notifier);
    }
    @Override
    public void notifyEmergency(String info) {
        super.notifyEmergency(info);
        System.out.println("Calling 911 for security emergency!!\n"+info);
    }
}
