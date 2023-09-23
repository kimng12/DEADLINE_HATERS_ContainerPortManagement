
package Weight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Weights {

    public static void runWeightCheck() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> containerData = readContainerData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt");
        Map<String, int[]> portData = readPortData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt");
        Map<String, String[]> vehicleData = readVehicleData("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt");

        System.out.println("Welcome to the Port Management System");
        System.out.print("Enter the vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter the port ID: ");
        String portBID = scanner.nextLine();

        boolean isWithinLimit = checkWeightLimit(vehicleId, portBID, containerData, portData, vehicleData);
        System.out.println("Is within limit: " + (isWithinLimit ? "Yes" : "No"));
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

    public static Map<String, int[]> readPortData(String fileName) throws IOException {
        Map<String, int[]> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 5) {
                    int currentKg = Integer.parseInt(parts[3]);
                    int maxKg = Integer.parseInt(parts[4]);
                    data.put(parts[0], new int[]{currentKg, maxKg});
                }
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
                if (parts.length > 6) {
                    data.put(parts[0], parts[6].split(","));
                }
            }
        }
        return data;
    }

    public static boolean checkWeightLimit(String vehicleId, String portBID, Map<String, Integer> containerData, Map<String, int[]> portData, Map<String, String[]> vehicleData) {
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

        int[] portLimits = portData.get(portBID);
        if (portLimits != null) {
            int currentKg = portLimits[0];
            int maxKg = portLimits[1];
            return (totalWeight + currentKg) <= maxKg;
        }
        return false;
    }
}