package design_patterns;

import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(String stockName, double price);
}

// Subject Interface
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject Class
class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    // Method to update stock prices and notify registered clients
    public void setStockPrice(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }
}

// Concrete Observer 1: MobileApp client
class MobileApp implements Observer {
    private final String clientName;

    public MobileApp(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("MobileApp Notification [" + clientName + "] - " + stockName + " is now $" + price);
    }
}

// Concrete Observer 2: WebApp client
class WebApp implements Observer {
    private final String clientName;

    public WebApp(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("WebApp Dashboard Alert [" + clientName + "] - " + stockName + " value updated to $" + price);
    }
}

// Test class to verify the Observer pattern implementation
public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        // Create observers
        Observer userMobile = new MobileApp("JohnsiPhone");
        Observer userWeb = new WebApp("CorporateWebPortal");

        // Register observers
        stockMarket.registerObserver(userMobile);
        stockMarket.registerObserver(userWeb);

        // Update stock values (should trigger notification to both)
        System.out.println("--- Stock Price Update 1 ---");
        stockMarket.setStockPrice("APPLE", 182.25);

        System.out.println();

        // Deregister one observer and update price again
        stockMarket.deregisterObserver(userWeb);
        System.out.println("--- Stock Price Update 2 (After WebApp deregistered) ---");
        stockMarket.setStockPrice("APPLE", 185.50);
    }
}
