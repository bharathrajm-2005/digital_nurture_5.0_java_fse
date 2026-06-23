package design_patterns;

// Logger class implementing the Singleton pattern
class Logger {
    // Private static instance of the same class
    private static Logger instance;

    // Private constructor to prevent instantiation from other classes
    private Logger() {}

    // Public static method to provide a global access point to the instance
    public static synchronized Logger getInstance() {
        // Lazy initialization: instance is created only when requested
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Method to simulate logging messages
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Test class to verify Singleton behavior
public class SingletonPatternExample {
    public static void main(String[] args) {
        // Retrieve instances of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Perform some logging
        logger1.log("Initializing applications settings.");
        logger2.log("Connection established successfully.");

        // Check if both references point to the exact same memory instance
        if (logger1 == logger2) {
            System.out.println("Success: Both references point to the same Logger instance.");
        } else {
            System.out.println("Failure: Logger instances are different.");
        }
    }
}
