package User;
import Port.Port;
import Container.*;
public class PortManager extends User {
    private Port port;

    // Constructor
    public PortManager(String username, String password) {
        super(username, password);
        this.port = port;
    }

    // Method to add a container to the port
    public void addContainer(Container container) {
        System.out.println("Port Manager adding a new container to port: " + port.getName());
        // Code to add the container to the port (e.g., database)
    }

    // Method to remove a container from the port
    public void removeContainer(Container container) {
        System.out.println("Port Manager removing a container from port: " + port.getName());
        // Code to remove the container from the port (e.g., database)
    }

    // Method to list all containers in the port
    public void listContainers() {
        System.out.println("Port Manager listing all containers in port: " + port.getName());
        // Code to list all containers in the port (e.g., database query)
    }

    // Method to generate port-specific statistics
    public void generatePortStatistics() {
        System.out.println("Port Manager generating statistics for port: " + port.getName());
        // Code to generate port-specific statistics (e.g., fuel usage, total containers, etc.)
    }
}

