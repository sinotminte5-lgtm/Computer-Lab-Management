package labmanagement;

/**
 * The abstract MaintenanceTask class represents a maintenance task
 * that can be scheduled and executed.
 */
public abstract class MaintenanceTask {
    private static int count = 0;
    protected String taskId;
    protected String description;
    protected String status;

    /**
     * Constructs a MaintenanceTask with a generated ID and description.
     */
    public MaintenanceTask(String description) {
        this.taskId = "T" + (++count);
        this.description = description;
        this.status = "Scheduled";
    }

    public String getTaskId() {
        return taskId;
    }
    public String getStatus() {
        return status;
    }
    public String getDescription() {
        return description;
    }

    /**
     * Schedules the maintenance task (implementation defined by subclasses).
     */
    public abstract void scheduleTask();

    /**
     * Executes the maintenance task (implementation defined by subclasses).
     */
    public abstract void executeTask();

    @Override
    public String toString() {
        return "Task[ID=" + taskId + ", Desc=" + description + ", Status=" + status + "]";
    }
}
