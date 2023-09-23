package Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleCRUD {

    private static final String VEHICLE_FILE = "DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt";

    public static void createVehicle(Vehicle vehicle) {
        int nextId = getVehicleCount() + 1;
        String vehicleId = String.format("v-%03d", nextId); // Format the ID with leading zeros

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLE_FILE, true))) {
            writer.write(vehicleId + ", " + vehicle.getType() + ", " + vehicle.getStoringCapacity());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<Vehicle> readVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String id = parts[0];
                    String type = parts[1];
                    int storingCapacity = Integer.parseInt(parts[2]);
                    Vehicle vehicle = new Vehicle(id, type, storingCapacity);
                    vehicles.add(vehicle);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return vehicles;
    }

    public static void updateVehicle(String vehicleId, Vehicle updatedVehicle) {
        List<Vehicle> vehicles = readVehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId().equals(vehicleId)) {
                vehicles.set(i, updatedVehicle);
                break;
            }
        }
        writeToFile(vehicles);
    }

    public static List<String> getVehiclesWithContainers() {
        List<String> vehiclesWithContainers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 7 && !parts[7].trim().isEmpty() && parts[7].startsWith("c-")) { // Check if there's a container ID
                    vehiclesWithContainers.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return vehiclesWithContainers;
    }



    public static void deleteVehicle(String vehicleId) {
        List<Vehicle> vehicles = readVehicles();
        vehicles.removeIf(vehicle -> vehicle.getId().equals(vehicleId));
        writeToFile(vehicles);
    }

    private static void writeToFile(List<Vehicle> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLE_FILE))) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.getId() + ", " + vehicle.getType() + ", " + vehicle.getStoringCapacity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int getVehicleCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(VEHICLE_FILE))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return count;
    }
}
