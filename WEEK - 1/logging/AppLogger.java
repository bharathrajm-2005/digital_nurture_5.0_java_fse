package logging;

// A simple example simulating a logging framework
public class AppLogger {

    public void processData() {
        // Log info message
        System.out.println("[INFO] Starting data processing...");

        try {
            int result = 10 / 0; // Simulated error
            System.out.println(result);
        } catch (Exception e) {
            // Log error message with exception details
            System.out.println("[ERROR] Failed to process data: " + e.getMessage());
        }

        // Log debug message
        System.out.println("[DEBUG] Finished data processing method.");
    }
}
