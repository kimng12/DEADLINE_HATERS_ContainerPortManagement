package Weight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class CRUD_port_to_Vehicle {

    public static void runPortToVehicleTransfer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> containerData = readContainerData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt");
        Map<String, String> portData = readPortData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt");

        System.out.println("Welcome to the Port to Vehicle Weight Transfer System");
        System.out.print("Enter the port ID: ");
        String portId = scanner.nextLine();
        System.out.print("Enter the container ID: ");
        String containerId = scanner.nextLine();
        System.out.print("Enter the weight in KG: ");
        int weight = scanner.nextInt();

        // Update the port data
        String portLine = portData.get(portId);
        String[] portParts = portLine.split(",");
        int currentWeight = Integer.parseInt(portParts[3]);
        portParts[3] = String.valueOf(currentWeight - weight);
        portLine = String.join(",", portParts);
        portData.put(portId, portLine);

        // Update the container data
        containerData.put(containerId, containerData.get(containerId) + weight);

        // Save the updated data back to the text files
        savePortDataToFile("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt", portData);
        saveContainerDataToFile("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt", containerData);
        System.out.println("Weight transfer successful.");
    }

    public static Map<String, Integer> readContainerData(String fileName) throws IOException {
        Map<String, Integer> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    data.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        }
        return data;
    }

    public static Map<String, String> readPortData(String fileName) throws IOException {
        Map<String, String> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    data.put(parts[0], line);
                }
            }
        }
        return data;
    }

    public static void savePortDataToFile(String fileName, Map<String, String> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                bw.write(entry.getValue());
                bw.newLine();
            }
        }
    }

    public static void saveContainerDataToFile(String fileName, Map<String, Integer> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        }
    }
}
