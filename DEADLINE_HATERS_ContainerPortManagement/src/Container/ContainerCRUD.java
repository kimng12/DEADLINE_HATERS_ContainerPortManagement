package Container;

import Port.PortCRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContainerCRUD {
    private static List<Container> containers = new ArrayList<>();


    public static void addContainer(String type,int weight, int storingCapacity) {
        String id = generateContainerId();
//        String type = "Standard"; // You can customize the type as needed

        Container container = new Container(id, type, (int) weight, storingCapacity);
        containers.add(container);
        String filePath = "src/Data/Container.txt";
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

    private static void rewriteContainerFile() {
        String filePath = "src/Data/Container.txt";
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

    // Method to read container weight from container.txt
    public static double readContainerWeight(String containerId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(containerId)) {
                    // Assuming that weight is in the 2nd column (index 1)
                    if (parts.length >= 2) {
                        return Double.parseDouble(parts[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0; // Default if not found
    }
}

