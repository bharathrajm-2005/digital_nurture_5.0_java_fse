package design_patterns;

// Component Interface
interface Notifier {
    void send(String message);
}

// Concrete Component
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// Abstract Decorator class that implements Notifier and holds a reference to a Notifier object
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

// Concrete Decorator adding SMS notification behavior
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // Invoke base notifier send
        sendSMS(message);     // Add SMS functionality
    }

    private void sendSMS(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Concrete Decorator adding Slack notification behavior
class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message); // Invoke base notifier send
        sendSlack(message);   // Add Slack functionality
    }

    private void sendSlack(String message) {
        System.out.println("Sending Slack Message: " + message);
    }
}

// Test class to verify the Decorator pattern implementation
public class DecoratorPatternExample {
    public static void main(String[] args) {
        // Base case: Default notifier (Email only)
        System.out.println("--- Email Only Notification ---");
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Service deployment completed!");

        System.out.println();

        // Decorating: Email + SMS Notifier
        System.out.println("--- Email + SMS Notification ---");
        Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());
        smsNotifier.send("Critical alerts: CPU temperature high!");

        System.out.println();

        // Multi-layered Decorating: Email + SMS + Slack Notifier
        System.out.println("--- Email + SMS + Slack Notification ---");
        Notifier fullNotifier = new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()));
        fullNotifier.send("Emergency: Server shutdown imminent!");
    }
}
