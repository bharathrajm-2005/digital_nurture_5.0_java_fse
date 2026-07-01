

class Logger {

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }
}

public class AppLogger {

    public static void main(String[] args) {

        Logger logger = new Logger();

        logger.info("Application Started");

        try {
            int result = 10 / 0;
            logger.debug("Result = " + result);
        } catch (Exception e) {
            logger.error("Exception Occurred: " + e.getMessage());
        }

        logger.info("Application Ended");
    }
}