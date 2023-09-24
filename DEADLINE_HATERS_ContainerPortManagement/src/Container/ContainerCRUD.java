package Container;

import Port.PortCRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;



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

        String chosenContainerId = null;

        // Step 1: Extract the container ID from the chosen vehicle and update the vehicle record.
        List<String> updatedVehicleLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(chosenVehicleId)) {
                    chosenContainerId = parts[6];
                    line = line.replace("," + chosenContainerId, ",no");
                }
                updatedVehicleLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the updated vehicle data back to Vehicle.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt"))) {
            for (String line : updatedVehicleLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 2: Add the container ID to the chosen port.
        List<String> updatedPortLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(chosenPortId + ",")) {
                    line += "," + chosenContainerId;
                }
                updatedPortLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the updated port data back to Port.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
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

    public static void loadContainerIntoVehicle() {
        String vehicleFilePath = "DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt";
        String portFilePath = "DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt";

        // Create a mapping of port IDs to containers in each port
        Map<String, List<String>> portContainersMap = new HashMap<>();
        try (BufferedReader portReader = new BufferedReader(new FileReader(portFilePath))) {
            String line;
            while ((line = portReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[5].trim().equals("yes")) {
                    String portId = parts[0].trim();
                    String containerId = parts[6].trim();
                    portContainersMap.computeIfAbsent(portId, k -> new ArrayList<>()).add(containerId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display available containers in each port
        if (portContainersMap.isEmpty()) {
            System.out.println("No available containers found in Port.txt!");
            return;
        }

        System.out.println("Available containers in each port:");
        int portIndex = 1;
        for (Map.Entry<String, List<String>> entry : portContainersMap.entrySet()) {
            String portId = entry.getKey();
            List<String> containerIds = entry.getValue();
            System.out.println(portIndex + ". Port " + portId + ":");
            int containerIndex = 1;
            for (String containerId : containerIds) {
                System.out.println("  " + containerIndex + ". " + containerId);
                containerIndex++;
            }
            portIndex++;
        }

        // Prompt the user to choose a container and a vehicle
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the port to choose a container from: ");
        int portChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate the port choice
        if (portChoice < 1 || portChoice > portContainersMap.size()) {
            System.out.println("Invalid port choice.");
            return;
        }

        // Get the selected port
        String selectedPortId = null;
        int selectedPortIndex = 1;
        for (Map.Entry<String, List<String>> entry : portContainersMap.entrySet()) {
            if (selectedPortIndex == portChoice) {
                selectedPortId = entry.getKey();
                break;
            }
            selectedPortIndex++;
        }

        // Get the container IDs in the selected port
        List<String> containerIdsInSelectedPort = portContainersMap.get(selectedPortId);

        // Display vehicles with no containers
        List<String> allVehicles = new ArrayList<>();
        try (BufferedReader vehicleReader = new BufferedReader(new FileReader(vehicleFilePath))) {
            String line;
            while ((line = vehicleReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7 && parts[6].trim().equals("no")) {
                    allVehicles.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display vehicles with no containers
        if (allVehicles.isEmpty()) {
            System.out.println("No vehicles without containers found!");
            return;
        }

        System.out.println("Vehicles without containers:");
        for (int i = 0; i < allVehicles.size(); i++) {
            System.out.println((i + 1) + ". " + allVehicles.get(i));
        }

        // Prompt the user to select a vehicle
        System.out.print("Choose a vehicle to load the container into (Enter the number): ");
        int vehicleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate the vehicle choice
        if (vehicleChoice < 1 || vehicleChoice > allVehicles.size()) {
            System.out.println("Invalid vehicle choice.");
            return;
        }

        // Get the selected vehicle
        String selectedVehicleLine = allVehicles.get(vehicleChoice - 1);
        String[] vehicleParts = selectedVehicleLine.split(",");

        // Check if there are containers in the selected port
        if (!containerIdsInSelectedPort.isEmpty()) {
            // Prompt the user to select a container from the selected port
            System.out.print("Enter the number of the container to load: ");
            int containerChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Validate the container choice
            if (containerChoice < 1 || containerChoice > containerIdsInSelectedPort.size()) {
                System.out.println("Invalid container choice.");
                return;
            }

            String chosenContainerId = containerIdsInSelectedPort.get(containerChoice - 1);

            // Update the vehicle with the chosen container
            vehicleParts[6] = chosenContainerId; // Replace "no" with the chosen container ID
            selectedVehicleLine = String.join(",", vehicleParts);
            allVehicles.set(vehicleChoice - 1, selectedVehicleLine);

            // Write all vehicles back to Vehicle.txt
            try (BufferedWriter vehicleWriter = new BufferedWriter(new FileWriter(vehicleFilePath))) {
                for (String line : allVehicles) {
                    vehicleWriter.write(line);
                    vehicleWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Remove the chosen container from the selected port
            containerIdsInSelectedPort.remove(chosenContainerId);

            // Update Port.txt with the modified container list for the selected port
            try (BufferedReader portReader = new BufferedReader(new FileReader(portFilePath))) {
                List<String> updatedPortLines = new ArrayList<>();
                String line;
                while ((line = portReader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2 && parts[0].trim().equals(selectedPortId)) {
                        // Update the line with the modified container list
                        parts[6] = String.join(",", containerIdsInSelectedPort);
                        line = String.join(",", parts);
                    }
                    updatedPortLines.add(line);
                }
                // Write the updated port data back to Port.txt
                try (BufferedWriter portWriter = new BufferedWriter(new FileWriter(portFilePath))) {
                    for (String updatedLine : updatedPortLines) {
                        portWriter.write(updatedLine);
                        portWriter.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Container loaded into the vehicle successfully and updated in Port.txt!");
        } else {
            System.out.println("No containers available in the selected port.");
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

