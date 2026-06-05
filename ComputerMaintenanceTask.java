package labmanagement;

/**
 * A concrete maintenance task for computers.
 */
public class ComputerMaintenanceTask extends MaintenanceTask {
    private Computer computer;

    /**
     * Constructs a ComputerMaintenanceTask for the specified computer.
     */
    public ComputerMaintenanceTask(Computer computer) {
        super("Maintenance for Computer " + computer.getComputerId());
        this.computer = computer;
        this.status = "Scheduled";
    }

    @Override
    public void scheduleTask() {
        System.out.println("Scheduling maintenance for computer " + computer.getComputerId() + ".");
        this.status = "Scheduled";
    }

    @Override
    public void executeTask() {
        System.out.println("Executing maintenance for computer " + computer.getComputerId() + ".");
        this.status = "Completed";
    }
}
