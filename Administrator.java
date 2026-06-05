package labmanagement;

import java.util.List;
import java.util.Scanner;

/**
 * The Administrator class extends User and provides functionality
 * for managing users and scheduling maintenance tasks.
 */
public class Administrator extends User {

    /**
     * Constructs an Administrator with the specified details.
     */
    public Administrator(String userId, String username, String password, String role) {
        super(userId, username, password, role);
    }

    /**
     * Manages user operations such as adding, removing, updating, and listing users.
     */
    public void manageUsers(List<User> users, Scanner scanner) {
        while (true) {
            System.out.println("\nUser Management Menu:");
            System.out.println("1. Add New User");
            System.out.println("2. Remove User");
            System.out.println("3. Update User");
            System.out.println("4. Display All Users");
            System.out.println("5. Search User");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Add new user
                    System.out.print("Enter user ID: ");
                    String newUserId = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Enter role (Administrator/LabAssistant/Student): ");
                    String newRole = scanner.nextLine();
                    User newUser;
                    if (newRole.equalsIgnoreCase("Administrator")) {
                        newUser = new Administrator(newUserId, newUsername, newPassword, newRole);
                    } else if (newRole.equalsIgnoreCase("LabAssistant")) {
                        newUser = new LabAssistant(newUserId, newUsername, newPassword, newRole);
                    } else {
                        newUser = new User(newUserId, newUsername, newPassword, newRole);
                    }
                    users.add(newUser);
                    System.out.println("User added: " + newUser);
                    break;
                case 2:
                    // Remove user
                    System.out.print("Enter user ID to remove: ");
                    String removeId = scanner.nextLine();
                    User userToRemove = null;
                    for (User u : users) {
                        if (u.getUserId().equals(removeId)) {
                            userToRemove = u;
                            break;
                        }
                    }
                    if (userToRemove != null) {
                        users.remove(userToRemove);
                        System.out.println("User removed: " + userToRemove);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 3:
                    // Update user
                    System.out.print("Enter user ID to update: ");
                    String updateId = scanner.nextLine();
                    User userToUpdate = null;
                    for (User u : users) {
                        if (u.getUserId().equals(updateId)) {
                            userToUpdate = u;
                            break;
                        }
                    }
                    if (userToUpdate != null) {
                        System.out.println("Updating user: " + userToUpdate);
                        System.out.println("1. Change Password");
                        System.out.println("2. Change Role");
                        System.out.print("Choose an option: ");
                        int updateChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        if (updateChoice == 1) {
                            System.out.print("Enter new password: ");
                            String pwd = scanner.nextLine();
                            userToUpdate.changePassword(pwd);
                        } else if (updateChoice == 2) {
                            System.out.print("Enter new role (Administrator/LabAssistant/Student): ");
                            String newRole2 = scanner.nextLine();
                            userToUpdate.setRole(newRole2);
                            // Note: Not changing object type for simplicity
                            System.out.println("User role updated.");
                        } else {
                            System.out.println("Invalid option.");
                        }
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 4:
                    // Display all users
                    System.out.println("All Users:");
                    for (User u : users) {
                        System.out.println(u);
                    }
                    break;
                case 5:
                    // Search user by ID
                    System.out.print("Enter user ID to search: ");
                    String searchId = scanner.nextLine();
                    User foundUser = null;
                    for (User u : users) {
                        if (u.getUserId().equals(searchId)) {
                            foundUser = u;
                            break;
                        }
                    }
                    if (foundUser != null) {
                        System.out.println("User found: " + foundUser);
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 6:
                    // Back to main menu
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Schedules and manages maintenance tasks for computers and peripherals.
     */
    public void scheduleMaintenance(List<Computer> computers, List<Peripheral> peripherals, List<MaintenanceTask> tasks, Scanner scanner) {
        while (true) {
            System.out.println("\nMaintenance Scheduling Menu:");
            System.out.println("1. Schedule Computer Maintenance");
            System.out.println("2. Schedule Peripheral Maintenance");
            System.out.println("3. View Scheduled Tasks");
            System.out.println("4. Execute All Tasks");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Schedule computer maintenance
                    System.out.println("Computers:");
                    for (Computer comp : computers) {
                        System.out.println(comp);
                    }
                    System.out.print("Enter Computer ID for maintenance: ");
                    String compId = scanner.nextLine();
                    Computer compToMaintain = null;
                    for (Computer comp : computers) {
                        if (comp.getComputerId().equals(compId)) {
                            compToMaintain = comp;
                            break;
                        }
                    }
                    if (compToMaintain != null) {
                        MaintenanceTask task = new ComputerMaintenanceTask(compToMaintain);
                        task.scheduleTask();
                        tasks.add(task);
                        System.out.println("Maintenance scheduled: " + task);
                    } else {
                        System.out.println("Computer not found.");
                    }
                    break;
                case 2:
                    // Schedule peripheral maintenance
                    System.out.println("Peripherals:");
                    for (Peripheral per : peripherals) {
                        System.out.println(per);
                    }
                    System.out.print("Enter Peripheral ID for maintenance: ");
                    String perId = scanner.nextLine();
                    Peripheral perToMaintain = null;
                    for (Peripheral per : peripherals) {
                        if (per.getPeripheralId().equals(perId)) {
                            perToMaintain = per;
                            break;
                        }
                    }
                    if (perToMaintain != null) {
                        MaintenanceTask task = new PeripheralMaintenanceTask(perToMaintain);
                        task.scheduleTask();
                        tasks.add(task);
                        System.out.println("Maintenance scheduled: " + task);
                    } else {
                        System.out.println("Peripheral not found.");
                    }
                    break;
                case 3:
                    // View all tasks
                    if (tasks.isEmpty()) {
                        System.out.println("No maintenance tasks scheduled.");
                    } else {
                        System.out.println("Scheduled Maintenance Tasks:");
                        for (MaintenanceTask t : tasks) {
                            System.out.println(t);
                        }
                    }
                    break;
                case 4:
                    // Execute all tasks
                    for (MaintenanceTask t : tasks) {
                        if (!t.getStatus().equals("Completed")) {
                            t.executeTask();
                        }
                    }
                    System.out.println("All pending tasks executed.");
                    break;
                case 5:
                    // Back to main menu
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
