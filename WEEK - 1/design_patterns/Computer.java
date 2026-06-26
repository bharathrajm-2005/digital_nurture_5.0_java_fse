package design_patterns;

public class Computer {

    private final String storage;
    private final String ram;
    private final String graphicsCard;
    private final String bluetoothDevice;

    public Computer(String storage,
            String ram,
            String graphicsCard,
            String bluetoothDevice) {

        this.storage = storage;
        this.ram = ram;
        this.graphicsCard = graphicsCard;
        this.bluetoothDevice = bluetoothDevice;
    }

    @Override
    public String toString() {
        return "Computer [Storage=" + storage +
                ", RAM=" + ram +
                ", Graphics Card=" + graphicsCard +
                ", Bluetooth=" + bluetoothDevice + "]";
    }

    public static void main(String[] args) {

        ComputerDirector director = new ComputerDirector();

        Computer gamingComputer = director.buildGamingComputer();

        System.out.println("Gaming Computer:");
        System.out.println(gamingComputer);

        System.out.println();

        Computer officeComputer = director.buildOfficeComputer();

        System.out.println("Office Computer:");
        System.out.println(officeComputer);
    }
}

// Builder Interface
interface ComputerBuilder {

    ComputerBuilder addGraphicsCard(String graphicsCard);

    ComputerBuilder addBluetooth(String bluetoothDevice);

    Computer build();
}

// Gaming Computer Builder
class GamingComputerBuilder implements ComputerBuilder {

    private final String storage;
    private final String ram;

    private String graphicsCard;
    private String bluetoothDevice;

    public GamingComputerBuilder(String storage, String ram) {
        this.storage = storage;
        this.ram = ram;
    }

    @Override
    public ComputerBuilder addGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    @Override
    public ComputerBuilder addBluetooth(String bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        return this;
    }

    @Override
    public Computer build() {
        return new Computer(
                storage,
                ram,
                graphicsCard,
                bluetoothDevice);
    }
}

// Office Computer Builder
class OfficeComputerBuilder implements ComputerBuilder {

    private final String storage;
    private final String ram;

    private String graphicsCard;
    private String bluetoothDevice;

    public OfficeComputerBuilder(String storage, String ram) {
        this.storage = storage;
        this.ram = ram;
    }

    @Override
    public ComputerBuilder addGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
    }

    @Override
    public ComputerBuilder addBluetooth(String bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        return this;
    }

    @Override
    public Computer build() {
        return new Computer(
                storage,
                ram,
                graphicsCard,
                bluetoothDevice);
    }
}

// Director Class
class ComputerDirector {

    public Computer buildGamingComputer() {

        return new GamingComputerBuilder(
                "1 TB SSD",
                "16 GB")
                .addGraphicsCard("NVIDIA RTX 4060")
                .addBluetooth("Sony Adapter")
                .build();
    }

    public Computer buildOfficeComputer() {

        return new OfficeComputerBuilder(
                "500 GB SSD",
                "8 GB")
                .addBluetooth("Standard Bluetooth")
                .build();
    }
}