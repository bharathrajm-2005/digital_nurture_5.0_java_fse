package design_patterns;

// Target Interface that the client expects
interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee 1: PayPal Gateway with a different method name
class PayPalGateway {
    public void makePayment(double amountInUSD) {
        System.out.println("Processing payment of $" + amountInUSD + " via PayPal.");
    }
}

// Adaptee 2: Stripe Gateway with its own unique charging interface
class StripeGateway {
    public void charge(double amountInEuro) {
        System.out.println("Charging EUR " + amountInEuro + " using Stripe.");
    }
}

// Adapter for PayPal to make it compatible with PaymentProcessor
class PayPalAdapter implements PaymentProcessor {
    private final PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public void processPayment(double amount) {
        // Translate the call to PayPal-specific method
        payPalGateway.makePayment(amount);
    }
}

// Adapter for Stripe to make it compatible with PaymentProcessor
class StripeAdapter implements PaymentProcessor {
    private final StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double amount) {
        // Translate the call to Stripe-specific method
        stripeGateway.charge(amount);
    }
}

// Test class to verify the Adapter pattern implementation
public class AdapterPatternExample {
    public static void main(String[] args) {
        // Create the individual gateway objects (adaptees)
        PayPalGateway payPal = new PayPalGateway();
        StripeGateway stripe = new StripeGateway();

        // Wrap them inside the adapters to conform to the PaymentProcessor interface
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);

        // Client processes payment using uniform interface
        System.out.println("--- Payment Client Operations ---");
        payPalProcessor.processPayment(120.00);
        stripeProcessor.processPayment(350.50);
    }
}
