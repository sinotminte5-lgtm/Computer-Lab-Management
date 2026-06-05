package labmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to run the Computer Lab Management System.
 * It handles the login interface and menu navigation based on user roles.
 */
public class LabManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize users
        List<User> users = new ArrayList<>();
        Administrator adminUser = new Administrator("U1", "admin", "admin", "Administrator");
        users.add(adminUser);
        users.add(new LabAssistant("U2", "labassist1", "pass1", "LabAssistant"));
        users.add(new LabAssistant("U3", "labassist2", "pass2", "LabAssistant"));
        users.add(new User("U4", "student1", "pass3", "Student"));
        users.add(new User("U5", "student2", "pass4", "Student"));

        // Initialize computers
        List<Computer> computers = new ArrayList<>();
        computers.add(new Computer("C1"));
        computers.add(new Computer("C2"));
        computers.add(new Computer("C3"));
        computers.add(new Computer("C4"));
        computers.add(new Computer("C5"));

        // Initialize peripherals
        List<Peripheral> peripherals = new ArrayList<>();
        peripherals.add(new Peripheral("P1"));
        peripherals.add(new Peripheral("P2"));
        peripherals.add(new Peripheral("P3"));
        peripherals.add(new Peripheral("P4"));

        // Initialize maintenance tasks list
        List<MaintenanceTask> maintenanceTasks = new ArrayList<>();

        // Initialize reservation manager
        ReservationManager reservationManager = new ReservationManager();

        System.out.println("Welcome to the Computer Lab Management System");

        // Main loop for login and menu
        while (true) {
            System.out.print("\nEnter username (or type 'exit' to quit): ");
            String username = scanner.next();
            if (username.equalsIgnoreCase("exit")) {
                System.out.println("Exiting system. Goodbye!");
                break;
            }
            System.out.print("Enter password: ");
            String password = scanner.next();

            // Authenticate user
            User currentUser = null;
            for (User u : users) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                    currentUser = u;
                    break;
                }
            }

            if (currentUser == null) {
                System.out.println("Invalid credentials. Please try again.");
                continue;
            }

            System.out.println("Login successful. Welcome, " + currentUser.getUsername() +
                    " [" + currentUser.getRole() + "]");

            // Role-based menu
            if (currentUser instanceof Administrator) {
                Administrator admin = (Administrator) currentUser;
                // Administrator menu loop
                while (true) {
                    System.out.println("\nAdministrator Menu:");
                    System.out.println("1. Manage Users");
                    System.out.println("2. Schedule Maintenance");
                    System.out.println("3. View Reservations");
                    System.out.println("4. Change Password");
                    System.out.println("5. Logout");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    switch (choice) {
                        case 1:
                            admin.manageUsers(users, scanner);
                            break;
                        case 2:
                            admin.scheduleMaintenance(computers, peripherals, maintenanceTasks, scanner);
                            break;
                        case 3:
                            reservationManager.viewReservations();
                            break;
                        case 4:
                            System.out.print("Enter new password: ");
                            String newPwd = scanner.nextLine();
                            admin.changePassword(newPwd);
                            break;
                        case 5:
                            admin.logout();
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                            continue;
                    }
                    if (choice == 5) break;
                }
            } else if (currentUser instanceof LabAssistant) {
                LabAssistant assistant = (LabAssistant) currentUser;
                // Lab Assistant menu loop
                while (true) {
                    System.out.println("\nLab Assistant Menu:");
                    System.out.println("1. Allocate Resources");
                    System.out.println("2. Track Usage");
                    System.out.println("3. Change Password");
                    System.out.println("4. Logout");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    switch (choice) {
                        case 1:
                            assistant.allocateResources(computers, peripherals, users, scanner);
                            break;
                        case 2:
                            assistant.trackUsage(computers, peripherals, users);
                            break;
                        case 3:
                            System.out.print("Enter new password: ");
                            String newPwd = scanner.nextLine();
                            assistant.changePassword(newPwd);
                            break;
                        case 4:
                            assistant.logout();
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                            continue;
                    }
                    if (choice == 4) break;
                }
            } else {
                // Student user
                while (true) {
                    System.out.println("\nStudent Menu:");
                    System.out.println("1. Reserve Computer");
                    System.out.println("2. Cancel Reservation");
                    System.out.println("3. View My Reservations");
                    System.out.println("4. Change Password");
                    System.out.println("5. Logout");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    switch (choice) {
                        case 1:
                            // Reserve computer
                            System.out.println("Available Computers for Reservation:");
                            for (Computer comp : computers) {
                                if (comp.getStatus().equals("Available")) {
                                    System.out.println(comp);
                                }
                            }
                            System.out.print("Enter Computer ID to reserve: ");
                            String resId = scanner.nextLine();
                            Computer compToReserve = null;
                            for (Computer comp : computers) {
                                if (comp.getComputerId().equals(resId)) {
                                    compToReserve = comp;
                                    break;
                                }
                            }
                            if (compToReserve != null) {
                                reservationManager.reserveComputer(currentUser, compToReserve);
                            } else {
                                System.out.println("Computer not found.");
                            }
                            break;
                        case 2:
                            // Cancel reservation
                            System.out.println("Your Reservations:");
                            reservationManager.viewUserReservations(currentUser);
                            System.out.print("Enter Computer ID to cancel reservation: ");
                            String cancelId = scanner.nextLine();
                            Computer compToCancel = null;
                            for (Computer comp : computers) {
                                if (comp.getComputerId().equals(cancelId)) {
                                    compToCancel = comp;
                                    break;
                                }
                            }
                            if (compToCancel != null) {
                                reservationManager.cancelReservation(currentUser, compToCancel);
                            } else {
                                System.out.println("Computer not found.");
                            }
                            break;
                        case 3:
                            // View my reservations
                            System.out.println("Your Reservations:");
                            reservationManager.viewUserReservations(currentUser);
                            break;
                        case 4:
                            System.out.print("Enter new password: ");
                            String newPwd = scanner.nextLine();
                            currentUser.changePassword(newPwd);
                            break;
                        case 5:
                            currentUser.logout();
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                            continue;
                    }
                    if (choice == 5) break;
                }
            }
        }

        scanner.close();
    }
}
