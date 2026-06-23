package design_patterns;

// Subject Interface
interface Image {
    void display();
}

// Real Subject Class representing the heavy resource from a remote server
class RealImage implements Image {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer();
    }

    // Heavy simulation operation
    private void loadFromRemoteServer() {
        System.out.println("Loading image: '" + filename + "' from remote server (takes time & bandwidth)...");
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy Class representing the lazy initialization and caching proxy
class ProxyImage implements Image {
    private final String filename;
    private RealImage realImage; // Cached reference to the real image

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        // Lazy initialization: Load the real image only when display is requested for the first time
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        // Use cached instance for subsequent display calls
        realImage.display();
    }
}

// Test class to verify the Proxy pattern implementation
public class ProxyPatternExample {
    public static void main(String[] args) {
        Image image = new ProxyImage("high_res_photo.png");

        System.out.println("--- First display call (requires loading) ---");
        image.display(); // Lazy-loads the image from the server and displays it
        System.out.println();

        System.out.println("--- Second display call (uses cached instance) ---");
        image.display(); // Skips loading since it is already cached
    }
}
