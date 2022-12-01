public class BaseNotifierDecorator implements SystemNotifier {

    SystemNotifier notifier;

    public BaseNotifierDecorator(SystemNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void notifyEmergency() {
        this.notifier.notifyEmergency();
    }
}
