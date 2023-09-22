package Weight;
import  Vehicle.*;
import Container.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Weights {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> containerData = readContainerData("PortMySelft/src/Data/Container.txt");
        Map<String, Integer> portData = readPortData("PortMySelft/src/Data/Port.txt");
        Map<String, String[]> vehicleData = readVehicleData("PortMySelft/src/Data/Vehicle.txt");

        checkWeightLimit("v-001", "p-001", containerData, portData, vehicleData);
    }

    public static Map<String, Integer> readContainerData(String fileName) throws IOException {
        Map<String, Integer> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                data.put(parts[0], Integer.parseInt(parts[1].replace("kg", "")));
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
                data.put(parts[0], parts[5].split(","));
            }
        }
        return data;
    }

    public static void checkWeightLimit(String vehicleId, String portId, Map<String, Integer> containerData, Map<String, Integer> portData, Map<String, String[]> vehicleData) {
        int totalWeight = 0;
        String[] containers = vehicleData.get(vehicleId);

        for (String container : containers) {
            totalWeight += containerData.get(container);
        }

        int portLimit = portData.get(portId);

        if (totalWeight > portLimit) {
            System.out.println("The total weight of containers for vehicle " + vehicleId + " exceeds the port limit of " + portId);
        } else {
            System.out.println("The total weight of containers for vehicle " + vehicleId + " is within the port limit of " + portId);
        }
    }
}