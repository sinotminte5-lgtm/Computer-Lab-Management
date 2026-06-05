package labmanagement;

import java.util.List;
import java.util.Scanner;

/**
 * The LabAssistant class extends User and provides functionality
 * for allocating resources and tracking usage in the lab.
 */
public class LabAssistant extends User {

    /**
     * Constructs a LabAssistant with the specified details.
     */
    public LabAssistant(String userId, String username, String password, String role) {
        super(userId, username, password, role);
    }

    /**
     * Allocates computers or peripherals to users based on selections.
     * Presents a menu to assign or release resources.
     */
    public void allocateResources(List<Computer> computers, List<Peripheral> peripherals, List<User> users, Scanner scanner) {
        while (true) {
            System.out.println("\nResource Allocation Menu:");
            System.out.println("1. Assign Computer to User");
            System.out.println("2. Release Computer from User");
            System.out.println("3. Assign Peripheral to User");
            System.out.println("4. Release Peripheral from User");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Assign computer
                    System.out.println("Available Computers:");
                    for (Computer comp : computers) {
                        System.out.println(comp);
                    }
                    System.out.print("Enter Computer ID to assign: ");
                    String compId = scanner.nextLine();
                    Computer selectedComp = null;
                    for (Computer comp : computers) {
                        if (comp.getComputerId().equals(compId)) {
                            selectedComp = comp;
                            break;
                        }
                    }
                    if (selectedComp == null) {
                        System.out.println("Computer not found.");
                        break;
                    }
                    if (!selectedComp.getStatus().equals("Available")) {
                        System.out.println("Computer is not available for assignment.");
                        break;
                    }
                    // Choose user
                    System.out.println("Users:");
                    for (User u : users) {
                        System.out.println(u);
                    }
                    System.out.print("Enter User ID to assign to: ");
                    String userId = scanner.nextLine();
                    User selectedUser = null;
                    for (User u : users) {
                        if (u.getUserId().equals(userId)) {
                            selectedUser = u;
                            break;
                        }
                    }
                    if (selectedUser == null) {
                        System.out.println("User not found.");
                    } else {
                        selectedComp.assignToUser(selectedUser);
                    }
                    break;
                case 2:
                    // Release computer
                    System.out.println("Assigned Computers:");
                    for (Computer comp : computers) {
                        if (comp.getAssignedUser() != null) {
                            System.out.println(comp);
                        }
                    }
                    System.out.print("Enter Computer ID to release: ");
                    String releaseCompId = scanner.nextLine();
                    Computer compToRelease = null;
                    for (Computer comp : computers) {
                        if (comp.getComputerId().equals(releaseCompId)) {
                            compToRelease = comp;
                            break;
                        }
                    }
                    if (compToRelease != null) {
                        compToRelease.release();
                    } else {
                        System.out.println("Computer not found.");
                    }
                    break;
                case 3:
                    // Assign peripheral
                    System.out.println("Available Peripherals:");
                    for (Peripheral per : peripherals) {
                        System.out.println(per);
                    }
                    System.out.print("Enter Peripheral ID to assign: ");
                    String perId = scanner.nextLine();
                    Peripheral selectedPer = null;
                    for (Peripheral per : peripherals) {
                        if (per.getPeripheralId().equals(perId)) {
                            selectedPer = per;
                            break;
                        }
                    }
                    if (selectedPer == null) {
                        System.out.println("Peripheral not found.");
                        break;
                    }
                    if (!selectedPer.getStatus().equals("Available")) {
                        System.out.println("Peripheral is not available for assignment.");
                        break;
                    }
                    // Choose user
                    System.out.println("Users:");
                    for (User u : users) {
                        System.out.println(u);
                    }
                    System.out.print("Enter User ID to assign to: ");
                    String perUserId = scanner.nextLine();
                    User perSelectedUser = null;
                    for (User u : users) {
                        if (u.getUserId().equals(perUserId)) {
                            perSelectedUser = u;
                            break;
                        }
                    }
                    if (perSelectedUser == null) {
                        System.out.println("User not found.");
                    } else {
                        selectedPer.assignToUser(perSelectedUser);
                    }
                    break;
                case 4:
                    // Release peripheral
                    System.out.println("Assigned Peripherals:");
                    for (Peripheral per : peripherals) {
                        if (per.getAssignedUser() != null) {
                            System.out.println(per);
                        }
                    }
                    System.out.print("Enter Peripheral ID to release: ");
                    String releasePerId = scanner.nextLine();
                    Peripheral perToRelease = null;
                    for (Peripheral per : peripherals) {
                        if (per.getPeripheralId().equals(releasePerId)) {
                            perToRelease = per;
                            break;
                        }
                    }
                    if (perToRelease != null) {
                        perToRelease.release();
                    } else {
                        System.out.println("Peripheral not found.");
                    }
                    break;
                case 5:
                    // Back to main menu
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    /**
     * Tracks and displays the usage of resources by each user.
     */
    public void trackUsage(List<Computer> computers, List<Peripheral> peripherals, List<User> users) {
        System.out.println("\nUsage Report:");
        for (User u : users) {
            int compCount = 0;
            int perCount = 0;
            for (Computer comp : computers) {
                if (comp.getAssignedUser() != null && comp.getAssignedUser().equals(u)) {
                    compCount++;
                }
            }
            for (Peripheral per : peripherals) {
                if (per.getAssignedUser() != null && per.getAssignedUser().equals(u)) {
                    perCount++;
                }
            }
            if (compCount > 0 || perCount > 0) {
                System.out.println(u.getUsername() + " is using " + compCount + " computers and " + perCount + " peripherals.");
            }
        }
    }
}
