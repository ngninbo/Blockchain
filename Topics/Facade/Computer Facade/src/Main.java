
class ComputerFacadeTestDrive {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Monitor monitor = new Monitor();
        Keyboard keyboard = new Keyboard();

        ComputerFacade computerFacade = new ComputerFacade(processor, monitor, keyboard);

        computerFacade.turnOnComputer();
        computerFacade.turnOffComputer();
    }
}

class ComputerFacade {

    private final Processor processor;
    private final Monitor monitor;
    private final Keyboard keyboard;

    public ComputerFacade(Processor processor, Monitor monitor, Keyboard keyboard) {
        this.processor = processor;
        this.monitor = monitor;
        this.keyboard = keyboard;
    }

    public void turnOnComputer() {
        processor.on();
        monitor.on();
        keyboard.on();
    }

    public void turnOffComputer() {
        keyboard.off();
        monitor.off();
        processor.off();
    }
}

class Processor {

    private final String description = "Processor";

    public void on() {
        System.out.printf("%s on\n", description);
    }

    public void off() {
        System.out.printf("%s off\n", description);
    }
}

class Monitor {

    private final String description = "Monitor";

    public void on() {
        System.out.printf("%s on\n", description);
    }

    public void off() {
        /* Write your code here */
        System.out.printf("%s off\n", description);
    }
}

class Keyboard {

    private final String description = "Keyboard";

    public void on() {
        System.out.printf("%s on\n", description);
        turnOnBacklight();
    }

    public void off() {
        System.out.printf("%s off\n", description);
        turnOffBacklight();
    }

    private void turnOnBacklight() {
        System.out.println("Backlight is turned on");
    }

    private void turnOffBacklight() {
        System.out.println("Backlight is turned off");
    }
}