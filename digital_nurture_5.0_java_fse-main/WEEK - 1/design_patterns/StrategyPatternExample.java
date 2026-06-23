package design_patterns;

// Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy: Credit Card Payment
class CreditCardPayment implements PaymentStrategy {
    @SuppressWarnings("unused")
    private final String cardNumber;
    private final String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing credit card payment of $" + amount + " [Cardholder: " + cardHolderName + "]");
    }
}

// Concrete Strategy: PayPal Payment
class PayPalPayment implements PaymentStrategy {
    private final String emailId;

    public PayPalPayment(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing PayPal payment of $" + amount + " [Account: " + emailId + "]");
    }
}

// Context Class holding a reference to a Strategy
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // Set strategy dynamically at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Execute the strategy
    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Error: No payment strategy selected!");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}

// Test class to verify Strategy pattern behavior
public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Dynamically choose Credit Card strategy
        System.out.println("--- Scenario 1: User pays using Credit Card ---");
        context.setPaymentStrategy(new CreditCardPayment("4111-2222-3333-4444", "Alice Smith"));
        context.executePayment(500.00);

        System.out.println();

        // Dynamically switch strategy to PayPal
        System.out.println("--- Scenario 2: User pays using PayPal ---");
        context.setPaymentStrategy(new PayPalPayment("alice@example.com"));
        context.executePayment(120.75);
    }
}
