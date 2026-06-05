package labmanagement;

import java.util.List;

/**
 * The User class represents a user in the Computer Lab Management System.
 * It has basic attributes like userId, username, password, and role.
 */
public class User {
    private String userId;
    private String username;
    private String password;
    private String role;

    /**
     * Constructs a User with the specified ID, username, password, and role.
     */
    public User(String userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Logs in by verifying username and password against the list of users.
     * Returns the matching User object if found, otherwise null.
     */
    public static User login(String username, String password, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Logs out the user from the system.
     */
    public void logout() {
        System.out.println(username + " has logged out.");
    }

    /**
     * Changes the user's password to the specified new password.
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password changed successfully for user " + username + ".");
    }

    @Override
    public String toString() {
        return "User[ID=" + userId + ", Username=" + username + ", Role=" + role + "]";
    }
}
