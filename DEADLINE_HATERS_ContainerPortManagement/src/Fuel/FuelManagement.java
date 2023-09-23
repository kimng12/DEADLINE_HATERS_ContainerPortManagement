package Fuel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FuelManagement {

    private Map<String, Vehicle> vehicleData; // VehicleID -> Vehicle
    private static String filePath = "DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt";
    public FuelManagement() {
        this.vehicleData = new HashMap<>();
        loadVehicleDataFromFile();
    }

    // Load vehicle data from the file into the map
    private void loadVehicleDataFromFile() {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Create parent directories if they don't exist
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String vehicleID = parts[0].trim();
                    String vehicleType = parts[1].trim();
                    String containerType = parts[2].trim();
                    double currentFuel = Double.parseDouble(parts[3].trim());
                    double fuelCapacity = Double.parseDouble(parts[4].trim());
                    String containerID = parts[5].trim();
                    String portID = parts[6].trim();
                    vehicleData.put(vehicleID, new Vehicle(vehicleType, containerType, currentFuel, fuelCapacity, containerID, portID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create: Refuel the vehicle by a specified amount of gallons
    public boolean refuel(String vehicleID, double gallons) {
        Vehicle vehicle = vehicleData.get(vehicleID);
        if (vehicle != null && gallons > 0) {
            double currentFuel = vehicle.getCurrentFuel();
            double fuelCapacity = vehicle.getFuelCapacity();
            double newFuel = Math.min(currentFuel + gallons, fuelCapacity);
            vehicle.setCurrentFuel(newFuel);
            saveVehicleDataToFile(); // Save the updated data
            return true;
        }
        return false;
    }

    // Read: Get the current fuel level
    public double getCurrentFuelLevel(String vehicleID) {
        Vehicle vehicle = vehicleData.get(vehicleID);
        if (vehicle != null) {
            return vehicle.getCurrentFuel();
        }
        return 0.0; // Vehicle not found, return 0 as default
    }

    // Update: Deduct fuel consumption for a trip and update the current fuel level
    public boolean updateFuelForTrip(String vehicleID, double fuelConsumed) {
        Vehicle vehicle = vehicleData.get(vehicleID);
        if (vehicle != null && fuelConsumed >= 0) {
            double currentFuel = vehicle.getCurrentFuel();
            double newFuel = Math.max(currentFuel - fuelConsumed, 0.0);
            vehicle.setCurrentFuel(newFuel);
            saveVehicleDataToFile(); // Save the updated data
            return true;
        }
        return false;
    }

    // Delete: Set the current fuel level to zero (simulate emptying the fuel tank)
    public boolean emptyFuelTank(String vehicleID) {
        Vehicle vehicle = vehicleData.get(vehicleID);
        if (vehicle != null) {
            vehicle.setCurrentFuel(0.0);
            saveVehicleDataToFile(); // Save the updated data
            return true;
        }
        return false;
    }

    // Save vehicle data to the file
    private void saveVehicleDataToFile() {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Create parent directories if they don't exist
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Vehicle> entry : vehicleData.entrySet()) {
                String vehicleID = entry.getKey();
                Vehicle vehicle = entry.getValue();
                writer.write(vehicleID + "," + vehicle.getVehicleType() + "," + vehicle.getContainerType() + "," +
                        vehicle.getCurrentFuel() + "," + vehicle.getFuelCapacity() + "," +
                        vehicle.getContainerID() + "," + vehicle.getPortID());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FuelManagement fuelManager = new FuelManagement();

        // Example usage
        boolean refuelSuccess = fuelManager.refuel("v-001", 50.0);
        System.out.println("Refuel success: " + refuelSuccess);
        double currentFuelLevel = fuelManager.getCurrentFuelLevel("v-001");
        System.out.println("Current Fuel Level: " + currentFuelLevel);
        boolean updateSuccess = fuelManager.updateFuelForTrip("v-001", 10.0);
        System.out.println("Update success: " + updateSuccess);
        boolean emptyTankSuccess = fuelManager.emptyFuelTank("v-001");
        System.out.println("Empty Tank success: " + emptyTankSuccess);
    }
}

class Vehicle {
    private String vehicleType;
    private String containerType;
    private double currentFuel;
    private double fuelCapacity;
    private String containerID;
    private String portID;

    public Vehicle(String vehicleType, String containerType, double currentFuel, double fuelCapacity, String containerID, String portID) {
        this.vehicleType = vehicleType;
        this.containerType = containerType;
        this.currentFuel = currentFuel;
        this.fuelCapacity = fuelCapacity;
        this.containerID = containerID;
        this.portID = portID;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getContainerType() {
        return containerType;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public String getContainerID() {
        return containerID;
    }

    public String getPortID() {
        return portID;
    }
}

