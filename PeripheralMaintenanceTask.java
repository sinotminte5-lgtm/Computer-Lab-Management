package labmanagement;

/**
 * A concrete maintenance task for peripherals.
 */
public class PeripheralMaintenanceTask extends MaintenanceTask {
    private Peripheral peripheral;

    /**
     * Constructs a PeripheralMaintenanceTask for the specified peripheral.
     */
    public PeripheralMaintenanceTask(Peripheral peripheral) {
        super("Maintenance for Peripheral " + peripheral.getPeripheralId());
        this.peripheral = peripheral;
        this.status = "Scheduled";
    }

    @Override
    public void scheduleTask() {
        System.out.println("Scheduling maintenance for peripheral " + peripheral.getPeripheralId() + ".");
        this.status = "Scheduled";
    }

    @Override
    public void executeTask() {
        System.out.println("Executing maintenance for peripheral " + peripheral.getPeripheralId() + ".");
        this.status = "Completed";
    }
}
