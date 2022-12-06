package Backend;

public class NotifierDecorator implements SystemNotifier {

    SystemNotifier notifier;

    public NotifierDecorator(SystemNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void notifyEmergency(String info) {
        this.notifier.notifyEmergency(info);
    }
}
