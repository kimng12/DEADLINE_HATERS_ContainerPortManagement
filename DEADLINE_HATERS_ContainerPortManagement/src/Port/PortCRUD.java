package Port;

import Container.ContainerCRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PortCRUD {
    public  static  int getPortCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e){
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
        return count;
    }

    public static void createPort(String portDetails) {
        int nextId = getPortCount() + 1;
        String portId = String.format("p-%03d", nextId);
        String fullDetails = portId + ", " + portDetails;

        try (FileWriter writer = new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt", true)) {
            writer.write(fullDetails + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static List<String> readPorts() {
        List<String> ports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ports.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return ports;
    }

    public static void updatePort(String portID, String newPortDetails) {
        List<String> ports = readPorts();
        boolean portFound = false;

        for (int i = 0; i < ports.size(); i++) {
            String port = ports.get(i);
            String[] parts = port.split(",");
            if (parts.length >= 1 && parts[0].trim().equals(portID)) {
                ports.set(i, portID + ", " + newPortDetails); // Update the port details
                portFound = true;
                break;
            }
        }

        if (portFound) {
            try (FileWriter writer = new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt")) {
                for (String updatedPort : ports) {
                    writer.write(updatedPort + "\n");
                }
                System.out.println("Port with ID " + portID + " updated successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while updating the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Port with ID " + portID + " not found.");
        }
    }

    public static void deletePort(String portIdToDelete, Scanner scanner) {
        List<String> ports = readPorts();
        boolean portFound = false;

        for (String port : ports) {
            String[] parts = port.split(",");
            if (parts.length >= 1 && parts[0].trim().equals(portIdToDelete)) {
                portFound = true;
                break;
            }
        }

        if (!portFound) {
            System.out.println("Port with ID " + portIdToDelete + " not found.");
        } else {
            System.out.print("Are you sure you want to delete the port with ID " + portIdToDelete + "? (Yes/No): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {
                ports.removeIf(port -> {
                    String[] parts = port.split(",");
                    return parts.length >= 1 && parts[0].trim().equals(portIdToDelete);
                });

                try (FileWriter writer = new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt")) {
                    for (String updatedPort : ports) {
                        writer.write(updatedPort + "\n");
                    }
                    System.out.println("Port with ID " + portIdToDelete + " deleted successfully.");
                } catch (IOException e) {
                    System.out.println("An error occurred while deleting from the file.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Deletion canceled.");
            }
        }
    }


    // Method to read storingCapacity of port B from port.txt
    public static double readPortStoringCapacity(String portId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(portId)) {
                    // Assuming that storingCapacity is in the 5th column (index 4)
                    if (parts.length >= 5) {
                        String capacityStr = parts[4].trim().replaceAll("[^\\d.]", "");
                        return Double.parseDouble(capacityStr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0; // Default if not found
    }

    public static boolean checkLandingAbility(String portId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(portId)) {
                    // Assuming that landingAbility is in the 6th column (index 5)
                    if (parts.length >= 6) {
                        String landingAbilityStr = parts[5].trim().toLowerCase();
                        return landingAbilityStr.equals("yes");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Default if not found or "no"
    }


    // Method to check the landing ability of a port
//    public static boolean checkPortAbility(String portId) {
//        // I want the system can check the information base on the ID inputted
//        // Take the data of the port StoringCapacity and container weight
//        // Then compare them
//        double portStoringCapacity = readPortStoringCapacity(portId);
//
//    }
}
