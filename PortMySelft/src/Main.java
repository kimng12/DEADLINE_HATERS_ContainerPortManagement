import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import User.PortManager;
import User.Admin;
import Port.*;
import  Vehicle.*;
import Container.*;

import javax.sound.midi.Soundbank;

public class Main {
    public static ArrayList<Port> ports = new ArrayList<>();
    public static ArrayList<Container> containers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Port Management System");

        // Sample data (Ideally, this would be loaded from a file or database)
        PortManager manager1 = new PortManager("manager1", "password");
        Admin admin = new Admin("admin", "adminPassword");

        while (true) {
            System.out.println("\n1. Login as Admin");
            System.out.println("2. Login as Port Manager");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Admin Login");
                    System.out.print("Username: ");
                    String adminUser = scanner.next();
                    System.out.print("Password: ");
                    String adminPass = scanner.next();

                    if ("admin".equals(adminUser) && "adminPassword".equals(adminPass)) {
                        System.out.println("Admin successfully logged in");
                        // Show admin-specific operations
                        displayAdminMenu(scanner);
                    } else {
                        System.out.println("Admin login failed");
                    }
                    break;
                case 2:
                    System.out.println("Port Manager Login");
                    System.out.print("Username: ");
                    String managerUser = scanner.next();
                    System.out.print("Password: ");
                    String managerPass = scanner.next();

                    if ("manager1".equals(managerUser) && "password".equals(managerPass)) {
                        System.out.println("Port Manager successfully logged in");
                        // Show port manager-specific operations
                    } else {
                        System.out.println("Port Manager login failed");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Admin Menu
    private static void displayAdminMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Welcome to Admin Menu");
            System.out.println("1. CRUD operations for Vehicles");
            System.out.println("2. CRUD operations for Port");
            System.out.println("3. CRUD operations for Container");
            System.out.println("4. Moving Container Among the Ports");
            // ... other admin-specific operations
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    vehicleCRUDMenu(scanner);
                    break;
                case 2:
                    portCRUDMenu(scanner);
                    break;
                case 3:
                    containerCRUDMenu(scanner);
                    break;
                case 4:
                    displayMovePortForm();
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }

    // Vehicles CRUD Menu
    private static void vehicleCRUDMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Vehicle CRUD Operations");
            System.out.println("1. Add a new vehicle");
            System.out.println("2. Display all vehicles");
            System.out.println("3. Update a vehicle");
            System.out.println("4. Delete a vehicle");
            System.out.println("0. Go back to main menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    // Case 1: Add a new vehicle
                    System.out.println("Enter vehicle details:");
                    System.out.print("Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Storing Capacity: ");
                    int storingCapacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    // Generate a unique vehicle ID (you can modify this logic if needed)
                    int nextId = VehicleCRUD.getVehicleCount() + 1;
                    String vehicleId = "v-" + nextId;
                    // Create a new Vehicle object and add it to the file
                    Vehicle newVehicle = new Vehicle(vehicleId, type, storingCapacity);
                    VehicleCRUD.createVehicle(newVehicle);
                    System.out.println("Vehicle created and saved with ID: " + vehicleId);
                    break;
                case 2:
                    // Case 2: Display all vehicles
                    List<Vehicle> allVehicles = VehicleCRUD.readVehicles();
                    if (allVehicles.isEmpty()) {
                        System.out.println("No vehicles to display!!");
                    } else {
                        for (Vehicle vehicle : allVehicles) {
                            System.out.println(vehicle.toString());
                        }
                    }
                    break;
                case 3:
                    // Case 3: Update a vehicle
                    System.out.print("Enter existing vehicle ID to update: ");
                    String oldVehicleId = scanner.nextLine();
                    System.out.print("Enter new vehicle details:");
                    System.out.print("Type: ");
                    String newType = scanner.nextLine();
                    System.out.print("Storing Capacity: ");
                    int newStoringCapacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    // Create a new Vehicle object with updated details
                    Vehicle updatedVehicle = new Vehicle(oldVehicleId, newType, newStoringCapacity);
                    VehicleCRUD.updateVehicle(oldVehicleId, updatedVehicle);
                    System.out.println("Vehicle updated successfully.");
                    break;
                case 4:
                    // Case 4: Delete a vehicle
                    System.out.print("Enter vehicle ID to delete: ");
                    String vehicleIdToDelete = scanner.nextLine();
                    VehicleCRUD.deleteVehicle(vehicleIdToDelete);
                    System.out.println("Vehicle with ID " + vehicleIdToDelete + " deleted.");
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }


    // portCRUDMenu
    private static void portCRUDMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Port CRUD Operations");
            System.out.println("1. Add a new Port");
            System.out.println("2. Display all Ports");
            System.out.println("3. Update a Port");
            System.out.println("4. Delete a Port");
            System.out.println("0. Go back to the main menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter details for the new Port (excluding ID):");
                    String portDetails = scanner.nextLine();
                    PortCRUD.createPort(portDetails);
                    System.out.println("Port created and saved with a new ID.");
                    break;
                case 2:
                    List<String> ports = PortCRUD.readPorts();
                    if (ports.isEmpty()) {
                        System.out.println("No ports to display!!");
                    } else {
                        ports.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Enter existing port details to update: ");
                    String oldPortDetails = scanner.nextLine();
                    System.out.print("Enter new port details: ");
                    String newPortDetails = scanner.nextLine();
                    PortCRUD.updatePort(oldPortDetails, newPortDetails);
                    break;
                case 4:
                    System.out.print("Enter port details to delete: ");
                    String portToDelete = scanner.nextLine();
                    PortCRUD.deletePort(portToDelete);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }


    private static void containerCRUDMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Container CRUD Operations");
            System.out.println("1. Add a new Container");
            System.out.println("2. Display all Containers");
            System.out.println("3. Update a container");
            System.out.println("4. Delete a container");
            System.out.println("0. Go back to the main menu");
            System.out.println("Container CRUD Operations");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Container details with format: Type, Weight (tons), Storing Capacity (tons): ");
                    String input = scanner.nextLine();

                    try {
                        String[] parts = input.split(", ");
                        String type = parts[0];
                        int weight = Integer.parseInt(parts[1]);
                        int storingCapacity = Integer.parseInt(parts[2]);

                        // Call the method to add a new container with the updated attributes
                        ContainerCRUD.addContainer(type, weight, storingCapacity);
                    } catch (Exception e) {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 2:
                    // Call the method to display all containers
                    ContainerCRUD.displayContainers();
                    break;
                case 3:
                    System.out.print("Enter the container ID to update: ");
                    String idToUpdate = scanner.nextLine();

                    System.out.print("Enter new container details (Type, Weight, Storing Capacity): ");
                    String newDetails = scanner.nextLine();

                    try {
                        String[] newParts = newDetails.split(", ");
                        String type = newParts[0];
                        int weight = Integer.parseInt(newParts[1]);
                        int storingCapacity = Integer.parseInt(newParts[2]);

                        // Call the method to update the container with the new details
                        ContainerCRUD.updateContainer(idToUpdate, type, weight, storingCapacity);
                    } catch (Exception e) {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the container ID to delete: ");
                    String idToDelete = scanner.nextLine();

                    // Call the method to delete the container
                    ContainerCRUD.removeContainer(idToDelete);
                    break;
                case 0:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void displayMovePortForm() {
        Scanner scanner = new Scanner(System.in);
        // Step 1: User input vehicle ID, container ID, port A, port B
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter Container ID: ");
        String containerId = scanner.nextLine();
        System.out.print("Enter Port A: ");
        String portA = scanner.nextLine();
        System.out.print("Enter Port B: ");
        String portB = scanner.nextLine();

        // Call the PortCRUD method to check if the container can be stored in Port B
        ContainerCRUD.checkContainerStorage(vehicleId, containerId, portA, portB);

        scanner.close();
    }
}






// Write the addPort method here

// Write the removePort method here
