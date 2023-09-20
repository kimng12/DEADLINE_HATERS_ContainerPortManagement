package Vehicle;
import java.io.*;
import java.util.*;

public class VehicleCRUD {

    private static final String VEHICLE_FILE = "src/Data/Vehicle.txt";

    public static void createVehicle(String vehicleDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLE_FILE, true))) {
            writer.write(vehicleDetails);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<String> readVehicles() {
        List<String> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                vehicles.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return vehicles;
    }

    public static void updateVehicle(String oldVehicleDetails, String newVehicleDetails) {
        List<String> vehicles = readVehicles();
        int index = vehicles.indexOf(oldVehicleDetails);
        if (index != -1) {
            vehicles.set(index, newVehicleDetails);
            writeToFile(vehicles);
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    public static void deleteVehicle(String vehicleDetails) {
        List<String> vehicles = readVehicles();
        vehicles.remove(vehicleDetails);
        writeToFile(vehicles);
    }

    private static void writeToFile(List<String> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLE_FILE))) {
            for (String vehicle : vehicles) {
                writer.write(vehicle);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}