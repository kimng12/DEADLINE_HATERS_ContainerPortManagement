package Weight;
import  Vehicle.*;
import Container.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Weights {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> containerData = readContainerData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt");
        Map<String, Integer> portData = readPortData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt");
        Map<String, String[]> vehicleData = readVehicleData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt");

        System.out.println("Welcome to the Port Management System");
        System.out.print("Enter the vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter the port ID: ");
        String portId = scanner.nextLine();
// output de lay di//
        boolean isWithinLimit = checkWeightLimit(vehicleId, portId, containerData, portData, vehicleData);
        System.out.println("Is within limit: " + (isWithinLimit ? "Yes" : "No"));
    }

    public static Map<String, Integer> readContainerData(String fileName) throws IOException {
        Map<String, Integer> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    data.put(parts[0], Integer.parseInt(parts[1].replace("kg", "")));
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        }
        return data;
    }

    public static Map<String, Integer> readPortData(String fileName) throws IOException {
        Map<String, Integer> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                data.put(parts[0], Integer.parseInt(parts[3].replace("kg", "")));
            }
        }
        return data;
    }


    public static Map<String, String[]> readVehicleData(String fileName) throws IOException {
        Map<String, String[]> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 5) {
                    data.put(parts[0], parts[5].split(","));
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        }
        return data;
    }
// output as text
//    public static void checkWeightLimit(String vehicleId, String portId, Map<String, Integer> containerData, Map<String, Integer> portData, Map<String, String[]> vehicleData) {
//        int totalWeight = 0;
//        String[] containers = vehicleData.get(vehicleId);
//
//        if (containers != null) {
//            for (String container : containers) {
//                Integer containerWeight = containerData.get(container);
//                if (containerWeight != null) {
//                    totalWeight += containerWeight;
//                } else {
//                    System.out.println("Container " + container + " not found in containerData");
//                }
//            }
//        } else {
//            System.out.println("Vehicle " + vehicleId + " not found in vehicleData");
//        }
//
//        Integer portLimit = portData.get(portId);
//        if (portLimit != null) {
//            if (totalWeight > portLimit) {
//                System.out.println("The total weight of containers for vehicle " + vehicleId + " exceeds the port limit of " + portId);
//            } else {
//                System.out.println("The total weight of containers for vehicle " + vehicleId + " is within the port limit of " + portId);
//            }
//        } else {
//            System.out.println("Port " + portId + " not found in portData");
//        }
//    }
//}

    // output as boolen
    public static boolean checkWeightLimit(String vehicleId, String portId, Map<String, Integer> containerData, Map<String, Integer> portData, Map<String, String[]> vehicleData) {
        int totalWeight = 0;
        String[] containers = vehicleData.get(vehicleId);

        if (containers != null) {
            for (String container : containers) {
                Integer containerWeight = containerData.get(container);
                if (containerWeight != null) {
                    totalWeight += containerWeight;
                }
            }
        }

        Integer portLimit = portData.get(portId);
        if (portLimit != null) {
            return totalWeight <= portLimit;
        }
        return false;
    }

}