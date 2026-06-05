package labmanagement;

/**
 * The Computer class represents a computer in the lab.
 * It has an ID, a status (Available/Assigned/Reserved), and can be assigned or released.
 */
public class Computer {
    private String computerId;
    private String status;
    private User assignedUser;

    /**
     * Constructs a Computer with the given ID. Initially it is available.
     */
    public Computer(String computerId) {
        this.computerId = computerId;
        this.status = "Available";
        this.assignedUser = null;
    }

    public String getComputerId() {
        return computerId;
    }
    public String getStatus() {
        return status;
    }
    public User getAssignedUser() {
        return assignedUser;
    }

    /**
     * Assigns this computer to the specified user if it is available.
     */
    public void assignToUser(User user) {
        if (assignedUser == null && status.equals("Available")) {
            assignedUser = user;
            status = "Assigned";
            System.out.println("Computer " + computerId + " assigned to " + user.getUsername() + ".");
        } else {
            System.out.println("Computer " + computerId + " is not available for assignment.");
        }
    }

    /**
     * Releases the computer from its assigned user, making it available.
     */
    public void release() {
        if (assignedUser != null) {
            System.out.println("Releasing computer " + computerId + " from " + assignedUser.getUsername() + ".");
            assignedUser = null;
            status = "Available";
        } else {
            System.out.println("Computer " + computerId + " is not currently assigned.");
        }
    }

    /**
     * Sets the status of the computer directly (e.g., Reserved).
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String userStr = (assignedUser != null) ? assignedUser.getUsername() : "None";
        return "Computer[ID=" + computerId + ", Status=" + status + ", User=" + userStr + "]";
    }
}
