package User;
import  Entity.Entity;
public class User implements Entity {
    protected String username;
    protected String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Create a new user in the system (Create)
    public void create() {
        // Add user to database or data storage
        System.out.println("Created a new user with username: " + username);
    }

    // Read user information (Read)
    public void read() {
        // Fetch and display user information from database or data storage
        System.out.println("Reading user information for username: " + username);
    }

    // Update user information (Update)
    public void update() {
        // Update user information in database or data storage
        System.out.println("Updated user information for username: " + username);
    }

    @Override
    public void save() {

    }

    // Delete user from the system (Delete)
    public void delete() {
        // Remove user from database or data storage
        System.out.println("Deleted user with username: " + username);
    }
}

