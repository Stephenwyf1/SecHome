package Backend;

public class BaseNotifierDecorator implements SystemNotifier {

    SystemNotifier notifier;

    public BaseNotifierDecorator(SystemNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void notifyEmergency(String info) {
        this.notifier.notifyEmergency(info);
    }
}
