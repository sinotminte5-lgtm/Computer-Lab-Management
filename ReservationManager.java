package labmanagement;

import java.util.HashMap;
import java.util.Map;

/**
 * The ReservationManager class implements ReservationSystem to handle
 * computer reservations.
 */
public class ReservationManager implements ReservationSystem {
    // Map to track which computer is reserved by which user
    private Map<Computer, User> reservations;

    public ReservationManager() {
        reservations = new HashMap<>();
    }

    /**
     * Reserves the specified computer for the given user if available.
     */
    @Override
    public void reserveComputer(User user, Computer computer) {
        if (computer.getStatus().equals("Available")) {
            reservations.put(computer, user);
            computer.setStatus("Reserved");
            System.out.println("Notification: Computer " + computer.getComputerId() +
                    " reserved by " + user.getUsername() + ".");
        } else {
            System.out.println("Computer " + computer.getComputerId() + " is not available for reservation.");
        }
    }

    /**
     * Cancels the reservation of the specified computer for the given user.
     */
    @Override
    public void cancelReservation(User user, Computer computer) {
        if (reservations.containsKey(computer) && reservations.get(computer).equals(user)) {
            reservations.remove(computer);
            computer.setStatus("Available");
            System.out.println("Notification: Reservation cancelled for Computer " + computer.getComputerId() +
                    " by " + user.getUsername() + ".");
        } else {
            System.out.println("No reservation found for this user and computer.");
        }
    }

    /**
     * Displays all current reservations.
     */
    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations at the moment.");
        } else {
            System.out.println("Current Reservations:");
            for (Map.Entry<Computer, User> entry : reservations.entrySet()) {
                System.out.println("Computer " + entry.getKey().getComputerId() +
                                   " -> " + entry.getValue().getUsername());
            }
        }
    }

    /**
     * Displays reservations for a specific user.
     */
    public void viewUserReservations(User user) {
        boolean found = false;
        for (Map.Entry<Computer, User> entry : reservations.entrySet()) {
            if (entry.getValue().equals(user)) {
                System.out.println("Computer " + entry.getKey().getComputerId() + " reserved by you.");
                found = true;
            }
        }
        if (!found) {
            System.out.println("You have no reservations.");
        }
    }
}
