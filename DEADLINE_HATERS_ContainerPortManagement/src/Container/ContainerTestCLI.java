package Container;  // Add this line if it's in the Container package
import java.io.IOException;
import java.util.Scanner;

public class ContainerTestCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("||==================================||");
            System.out.println("||       Container Test Menu        ||");
            System.out.println("||----------------------------------||");
            System.out.println("|| 1. Transfer Container            ||");
            System.out.println("|| 2. Exit                          ||");
            System.out.println("||==================================||");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Container ID (e.g., c-005): ");
                    String containerId = scanner.nextLine();

                    System.out.print("Enter Port ID from which to remove the container (e.g., p-005): ");
                    String portId = scanner.nextLine();

                    System.out.print("Enter Vehicle ID to which to add the container (e.g., v-001): ");
                    String vehicleId = scanner.nextLine();

                    try {
                        ContainerTest.transferContainerFromPortToVehicle(containerId, portId, vehicleId);
                    } catch (IOException e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
