package Container;

import Port.PortCRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContainerCRUD {
    private static List<Container> containers = new ArrayList<>();
    private static String filePath = "DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt";

    public static void addContainer(String type, int weight, int storingCapacity) {
        String id = generateContainerId();
//        String type = "Standard"; // You can customize the type as needed

        Container container = new Container(id, type, (int) weight, storingCapacity);
        containers.add(container);
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Create parent directories if they don't exist
        }

        // Write the container details to the "Container.txt" file
        try (FileWriter writer = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(container.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public static void displayContainers() {
        if (containers.isEmpty()) {
            System.out.println("No containers to display!!");
        } else {
            for (Container container : containers) {
                System.out.println("ID: " + container.getId() + ", Type: " + container.getType() +
                        ", Weight: " + container.getWeight() + ", StoringCapacity: " + container.getStoringCapacity());
            }
        }
    }

    public static void updateContainer(String id, String type, int weight, int storingCapacity) {
        for (Container container : containers) {
            if (container.getId().equals(id)) {
                container.setType(type);
                container.setWeight(weight);
                container.setStoringCapacity(storingCapacity);
                System.out.println("Container details updated successfully.");
                return;
            }
        }
        System.out.println("Container with ID " + id + " not found.");
    }

    public static void removeContainer(String id) {
        containers.removeIf(container -> container.getId().equals(id));
        rewriteContainerFile(); // Update the "Container.txt" file without the removed container
        System.out.println("Container with ID " + id + " removed successfully.");
    }

    private static String generateContainerId() {
        // Generate a new container ID with format "c-numberFromOne"
        int nextNumber = containers.size() + 1;
        String formattedNumber = String.format("%03d", nextNumber);
        return "c-" + formattedNumber;
    }
    public static void transferContainerToPort(String chosenVehicleId, String chosenPortId) {
        String vehicleFilePath = "DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt";
        String portFilePath = "DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt";

        List<String> allVehicles = new ArrayList<>();
        String chosenContainerId = null;

        // Read the entire Vehicle.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader(vehicleFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(chosenVehicleId)) {
                    chosenContainerId = parts[7]; // Extract container ID
                    // Remove the container ID from the vehicle's record
                    line = String.join(",", parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                }
                allVehicles.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write all vehicles back to Vehicle.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(vehicleFilePath))) {
            for (String line : allVehicles) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

// Add the container ID to the chosen port in Port.txt
        List<String> updatedPortLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(portFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(chosenPortId + ",")) {
                    String[] parts = line.split(",");
                    // Check if the port already has containers
                    if ("no".equals(parts[6])) {
                        parts[6] = chosenContainerId; // Replace "no" with the container ID
                    } else {
                        // Find the next available spot for the container ID
                        int index = 7;
                        while (index < parts.length && !"no".equals(parts[index])) {
                            index++;
                        }
                        if (index < parts.length) {
                            parts[index] = chosenContainerId; // Add the container ID in the next available spot
                        } else {
                            // If there's no available spot, append the container ID to the end
                            line += "," + chosenContainerId;
                            continue;
                        }
                        line = String.join(",", parts);
                    }
                }
                updatedPortLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




// Write the updated lines back to Port.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(portFilePath))) {
            for (String line : updatedPortLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

}


    private static void rewriteContainerFile() {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Create parent directories if they don't exist
        }
        // Rewrite the "Container.txt" file with the updated container list
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (Container container : containers) {
                bufferedWriter.write(container.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
//    public static void checkContainerStorage(String vehicleID, String containerID, String portA, String portB) {
//        double containerWeight = readContainerWeight(containerID);
//
//        // Step 3: Read the "storingCapacity" of port B
//        double portBCapacity = PortCRUD.readPortCapacity(portB);
//
//        // Step 4: Compare container weight with port B's capacity
//        boolean result = containerWeight <= portBCapacity;
//
//        // Step 5: Return true or false
//        System.out.println("Container Weight: " + containerWeight + " tons");
//        System.out.println("Port B Capacity: " + portBCapacity + " tons");
//        System.out.println("Can the container be stored in Port B? " + result);
//    }

    // Method to read container weight from container.txt
    public static double readContainerWeight(String containerId) {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Create parent directories if they don't exist
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equals(containerId)) {
                    return Double.parseDouble(parts[1].trim());
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0; // Default if not found
    }
}

