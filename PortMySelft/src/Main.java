import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import User.PortManager;
import User.Admin;
import Port.*;
import  Vehicle.*;
import Container.*;
public class Main {
    public static ArrayList<Port> ports = new ArrayList<>();
    public static ArrayList<Container> containers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Port Management System");

        // Sample data (Ideally, this would be loaded from a file or database)
        Port port1 = new Port("p-001", "Port A", 1200.00, 1000, 1000, true, "c-001");
        Port port2 = new Port("p-002", "Port B", 1500.00, 1200, 1300, true, "c-002");
        PortManager manager1 = new PortManager("manager1", "password", port1);
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
                        case 0:
                            System.out.println("Logging out...");
                            break;
                        default:
                            System.out.println("Invalid choice! Please try again.");
                }
            } while (choice !=0);
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
                    /*
                    In this case user need to enter the details respectively as the format listed
                    */
                    System.out.print("Enter vehicle details (format: ID,Name,Type,FuelCapacity,CurrentFuel,CurrentPort,CarryingCapacity): ");
                    String vehicleDetails = scanner.nextLine();
                    VehicleCRUD.createVehicle(vehicleDetails);
                    break;
                case 2: // Show all the vehicles
                    List<String> vehicles = VehicleCRUD.readVehicles();
                    vehicles.forEach(System.out::println);
                    break;
                case 3:
                    /*
                    In this case, user need to enter all the details of that vehicle
                    ID,Name,Type,FuelCapacity,CurrentFuel,CurrentPort,CarryingCapacity. Then they can enter the improvement
                    for this vehicles
                    */
                    System.out.print("Enter existing vehicle details to update: ");
                    String oldVehicleDetails = scanner.nextLine();
                    System.out.print("Enter new vehicle details: ");
                    String newVehicleDetails = scanner.nextLine();
                    VehicleCRUD.updateVehicle(oldVehicleDetails, newVehicleDetails);
                    break;
                case 4:
                    /*
                    In this case, user need to enter all the details of that vehicle
                    ID,Name,Type,FuelCapacity,CurrentFuel,CurrentPort,CarryingCapacity. Then they can delete
                    for this vehicles
                    */
                    System.out.print("Enter vehicle details to delete: ");
                    String vehicleToDelete = scanner.nextLine();
                    VehicleCRUD.deleteVehicle(vehicleToDelete);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice !=0);
    }
    // portCRUDMenu
    private static void portCRUDMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("Port CRUD Operations");
            System.out.println("1. Add a new Port");
            System.out.println("2. Display all vehicles");
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
                    ports.forEach(System.out::println);
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
                    System.out.print("Enter Container details with format: ID, Weight: ");
                    String input = scanner.nextLine();

                    try {
                        String[] parts = input.split(", ");
                        String id = parts[0];
                        double weight = Double.parseDouble(parts[1]);
                        addContainer(id, weight);
                    } catch (Exception e) {
                        System.out.println("Invalid input format.");
                    }
                    break;
                case 2:
                    if (containers.isEmpty()) {
                        System.out.println("No containers to display!!");
                    } else {
                        for (Container container : containers) {
                            System.out.println("ID: " + container.getId() + ", Weight: " +container.getWeight());
                        }
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
                    System.out.print("Enter container details to delete: ");
                    String num = scanner.toString();
                    removeContainer(num);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }
    public static void addContainer(String id, double weight) {
        Container newContainer = new Container(id, weight);
        containers.add(newContainer);
        System.out.println("Container added successfully.");
    }
    public static void removeContainer(String id) {
        for (Container container : containers) {
            if (container.getId().equals(id)) {
                containers.remove(container);
                System.out.println("Container removed successfully.");
                return;
            }
        }
        System.out.println("Container not found.");
    }





    // Write the addPort method here

    // Write the removePort method here
}
