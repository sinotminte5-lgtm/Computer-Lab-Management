package labmanagement;

/**
 * The Peripheral class represents a peripheral device in the lab.
 * It has an ID, a status (Available/Assigned/Reserved), and can be assigned or released.
 */
public class Peripheral {
    private String peripheralId;
    private String status;
    private User assignedUser;

    /**
     * Constructs a Peripheral with the given ID. Initially it is available.
     */
    public Peripheral(String peripheralId) {
        this.peripheralId = peripheralId;
        this.status = "Available";
        this.assignedUser = null;
    }

    public String getPeripheralId() {
        return peripheralId;
    }
    public String getStatus() {
        return status;
    }
    public User getAssignedUser() {
        return assignedUser;
    }

    /**
     * Assigns this peripheral to the specified user if it is available.
     */
    public void assignToUser(User user) {
        if (assignedUser == null && status.equals("Available")) {
            assignedUser = user;
            status = "Assigned";
            System.out.println("Peripheral " + peripheralId + " assigned to " + user.getUsername() + ".");
        } else {
            System.out.println("Peripheral " + peripheralId + " is not available for assignment.");
        }
    }

    /**
     * Releases the peripheral from its assigned user, making it available.
     */
    public void release() {
        if (assignedUser != null) {
            System.out.println("Releasing peripheral " + peripheralId + " from " + assignedUser.getUsername() + ".");
            assignedUser = null;
            status = "Available";
        } else {
            System.out.println("Peripheral " + peripheralId + " is not currently assigned.");
        }
    }

    /**
     * Sets the status of the peripheral directly (e.g., Reserved).
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String userStr = (assignedUser != null) ? assignedUser.getUsername() : "None";
        return "Peripheral[ID=" + peripheralId + ", Status=" + status + ", User=" + userStr + "]";
    }
}
