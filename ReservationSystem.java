package labmanagement;

/**
 * The ReservationSystem interface defines methods for reserving
 * and cancelling computer reservations.
 */
public interface ReservationSystem {
    void reserveComputer(User user, Computer computer);
    void cancelReservation(User user, Computer computer);
}
