public class FireNotifier extends BaseNotifierDecorator {
    public FireNotifier(SystemNotifier notifier) {
        super(notifier);
    }
    @Override
    public void notifyEmergency() {
        super.notifyEmergency();
        System.out.println("Calling 911 for fire emergency!!!");
    }
}
