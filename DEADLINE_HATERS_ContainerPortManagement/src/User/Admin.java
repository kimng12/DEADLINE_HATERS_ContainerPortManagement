package User;
import Port.Port;
import Container.Container;
import Vehicle.Vehicle;
public class Admin extends User {
    // Constructor
    public Admin(String username, String password) {
        super(username, password);
    }

    // Method to add a new port
    public void addPort(Port port) {
        System.out.println("Admin adding a new port: " + port.getName());
        // Code to add the port to the system (e.g., database)
    }

    // Method to remove an existing port
    public void removePort(Port port) {
        System.out.println("Admin removing an existing port: " + port.getName());
        // Code to remove the port from the system (e.g., database)
    }

    // Method to add a new vehicle
//    public void addVehicle(Vehicle vehicle) {
//        System.out.println("Admin adding a new vehicle: " + vehicle.getName());
        // Code to add the vehicle to the system (e.g., database)
    }

    // Method to remove an existing vehicle
//    public void removeVehicle(Vehicle vehicle) {
//        System.out.println("Admin removing an existing vehicle: " + vehicle.getName());
        // Code to remove the vehicle from the system (e.g., database)
    //}

//    // Method to generate statistics
//    public void generateStatistics() {
//        System.out.println("Admin generating system statistics.");
//        // Code to generate statistics (e.g., fuel usage, total containers, etc.)
//    }
//}

