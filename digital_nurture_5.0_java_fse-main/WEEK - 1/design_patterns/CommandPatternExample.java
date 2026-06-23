package design_patterns;

// Command Interface
interface Command {
    void execute();
}

// Receiver Class representing the physical light device
class Light {
    public void turnOn() {
        System.out.println("The light is now ON.");
    }

    public void turnOff() {
        System.out.println("The light is now OFF.");
    }
}

// Concrete Command to turn the light ON
class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete Command to turn the light OFF
class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker Class representing the control panel / remote
class RemoteControl {
    private Command command;

    // Set the command object to be executed
    public void setCommand(Command command) {
        this.command = command;
    }

    // Execute the loaded command
    public void pressButton() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command mapped to this button.");
        }
    }
}

// Test class to verify Command pattern implementation
public class CommandPatternExample {
    public static void main(String[] args) {
        // Create Receiver
        Light livingRoomLight = new Light();

        // Create Command instances mapping to Receiver
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // Create Invoker
        RemoteControl remote = new RemoteControl();

        // Turn Light On
        System.out.println("--- Pressing Light On Button ---");
        remote.setCommand(lightOn);
        remote.pressButton();

        System.out.println();

        // Turn Light Off
        System.out.println("--- Pressing Light Off Button ---");
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}
