package design_patterns;

// Product class representing a Computer configuration
class Computer {
    // Required attributes
    private final String CPU;
    private final String RAM;
    private final String storage;

    // Optional attributes
    private final boolean isGraphicsCardEnabled;
    private final boolean isBluetoothEnabled;

    // Private constructor taking the Builder as parameter
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    // Getters
    public String getCPU() { return CPU; }
    public String getRAM() { return RAM; }
    public String getStorage() { return storage; }
    public boolean isGraphicsCardEnabled() { return isGraphicsCardEnabled; }
    public boolean isBluetoothEnabled() { return isBluetoothEnabled; }

    @Override
    public String toString() {
        return "Computer Specifications: [CPU = " + CPU + ", RAM = " + RAM + ", Storage = " + storage +
               ", Graphics Card = " + isGraphicsCardEnabled + ", Bluetooth = " + isBluetoothEnabled + "]";
    }

    // Static nested Builder class
    public static class Builder {
        // Required attributes
        private final String CPU;
        private final String RAM;
        private final String storage;

        // Optional attributes initialized with default values
        private boolean isGraphicsCardEnabled = false;
        private boolean isBluetoothEnabled = false;

        // Constructor with required attributes
        public Builder(String CPU, String RAM, String storage) {
            this.CPU = CPU;
            this.RAM = RAM;
            this.storage = storage;
        }

        // Setter for optional graphics card
        public Builder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        // Setter for optional bluetooth
        public Builder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        // Method to return the fully built Computer instance
        public Computer build() {
            return new Computer(this);
        }
    }
}

// Test class to demonstrate the Builder pattern implementation
public class BuilderPatternExample {
    public static void main(String[] args) {
        // Build an office configuration (only required parts)
        Computer officePC = new Computer.Builder("Intel i3", "8 GB", "256 GB SSD")
                .build();

        // Build a gaming configuration (with all optional parts enabled)
        Computer gamingPC = new Computer.Builder("AMD Ryzen 7", "32 GB", "1 TB NVMe SSD")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();

        System.out.println("Office PC: " + officePC);
        System.out.println("Gaming PC: " + gamingPC);
    }
}
