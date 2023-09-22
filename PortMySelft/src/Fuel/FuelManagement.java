package Fuel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FuelManagement {
    private Map<String, FuelData> fuelDataMap; // VehicleID -> FuelData

    public FuelManagement() {
        this.fuelDataMap = new HashMap<>();
        loadFuelDataFromFile();
    }

    // Load fuel data from the file into the map
    private void loadFuelDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Vehicle.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String id = parts[0].trim();
                    String vehicleType = parts[1].trim();
                    String containerType = parts[2].trim();
                    double currentFuel = Double.parseDouble(parts[3].trim());
                    double fuelCapacity = Double.parseDouble(parts[4].trim());
                    String containerID = parts[5].trim();
                    String portID = parts[6].trim();
                    fuelDataMap.put(id, new FuelData(vehicleType, containerType, currentFuel, fuelCapacity, containerID, portID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save fuel data to the file
    private void saveFuelDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Vehicle.txt"))) {
            for (Map.Entry<String, FuelData> entry : fuelDataMap.entrySet()) {
                String id = entry.getKey();
                FuelData fuelData = entry.getValue();
                writer.write(id + "," + fuelData.getVehicleType() + "," + fuelData.getContainerType() + "," +
                        fuelData.getCurrentFuel() + "," + fuelData.getFuelCapacity() + "," +
                        fuelData.getContainerID() + "," + fuelData.getPortID());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create: Refuel the vehicle by a specified amount of gallons
    public void refuel(String id, double gallons) {
        if (gallons > 0) {
            FuelData fuelData = fuelDataMap.getOrDefault(id, new FuelData("", "", 0.0, 0.0, "", ""));
            double currentFuel = fuelData.getCurrentFuel();
            double fuelCapacity = fuelData.getFuelCapacity();
            double newFuel = Math.min(currentFuel + gallons, fuelCapacity);
            fuelData.setCurrentFuel(newFuel);
            fuelDataMap.put(id, fuelData);
            saveFuelDataToFile();
            System.out.println("Refueled " + gallons + " gallons for vehicle ID: " + id);
        } else {
            System.out.println("Invalid fuel amount. Please provide a positive value.");
        }
    }

    // Read: Get the current fuel level
    public double getCurrentFuelLevel(String id) {
        FuelData fuelData = fuelDataMap.getOrDefault(id, new FuelData("", "", 0.0, 0.0, "", ""));
        return fuelData.getCurrentFuel();
    }

    // Update: Deduct fuel consumption for a trip and update the current fuel level
    public void updateFuelForTrip(String id, double fuelConsumed) {
        if (fuelConsumed >= 0) {
            FuelData fuelData = fuelDataMap.getOrDefault(id, new FuelData("", "", 0.0, 0.0, "", ""));
            double currentFuel = fuelData.getCurrentFuel();
            double newFuel = Math.max(currentFuel - fuelConsumed, 0.0);
            fuelData.setCurrentFuel(newFuel);
            fuelDataMap.put(id, fuelData);
            saveFuelDataToFile();
        } else {
            System.out.println("Invalid fuel consumption value.");
        }
    }

    // Delete: Set the current fuel level to zero (simulate emptying the fuel tank)
    public void emptyFuelTank(String id) {
        FuelData fuelData = fuelDataMap.getOrDefault(id, new FuelData("", "", 0.0, 0.0, "", ""));
        fuelData.setCurrentFuel(0.0);
        fuelDataMap.put(id, fuelData);
        saveFuelDataToFile();
    }

    public static void main(String[] args) {
        FuelManagement fuelManager = new FuelManagement();

        // Example usage
        fuelManager.refuel("v-001", 50.0);
        double currentFuelLevel = fuelManager.getCurrentFuelLevel("v-001");
        System.out.println("Current Fuel Level: " + currentFuelLevel);
        fuelManager.updateFuelForTrip("v-001", 10.0);
        fuelManager.emptyFuelTank("v-001");
    }
}

class FuelData {
    private String vehicleType;
    private String containerType;
    private double currentFuel;
    private double fuelCapacity;
    private String containerID;
    private String portID;

    public FuelData(String vehicleType, String containerType, double currentFuel, double fuelCapacity, String containerID, String portID) {
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

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public String getContainerID() {
        return containerID;
    }

    public String getPortID() {
        return portID;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }
}
