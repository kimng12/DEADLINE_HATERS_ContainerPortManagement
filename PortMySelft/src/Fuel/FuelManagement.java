package Fuel;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    // Abstract FuelManager class
    public abstract class FuelManagement {
        private double currentFuel;
        private double fuelCapacity;
        private String fuelFileName;
        private Map<String, Double> fuelData; // Map to store fuel data

        public FuelManagement(double fuelCapacity, String fuelFileName) {
            this.fuelCapacity = fuelCapacity;
            this.fuelFileName = fuelFileName;
            this.fuelData = new HashMap<>();
            loadFuelDataFromFile(); // Load fuel data from file
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

        // CRUD Operations

        // Create: Refuel the vehicle by a specified amount of gallons
        public void refuel(double gallons) {
            if (gallons > 0) {
                double newFuelLevel = currentFuel + gallons;
                if (newFuelLevel > fuelCapacity) {
                    System.out.println("Refueling exceeds fuel capacity. Fueling to maximum capacity.");
                    currentFuel = fuelCapacity;
                } else {
                    currentFuel = newFuelLevel;
                    saveFuelDataToFile(); // Save updated fuel data to file
                    System.out.println("Vehicle has been refueled by " + gallons + " gallons.");
                }
            } else {
                System.out.println("Invalid fuel amount. Please provide a positive value.");
            }
        }

        // Read: Get the current fuel level
        public double getCurrentFuelLevel() {
            return currentFuel;
        }

        // Update: Deduct fuel consumption for a trip and update the current fuel level
        public double updateFuelForTrip(double fuelConsumed) {
            if (fuelConsumed >= 0) {
                if (fuelConsumed <= currentFuel) {
                    currentFuel -= fuelConsumed;
                    saveFuelDataToFile(); // Save updated fuel data to file
                    return fuelConsumed;
                } else {
                    System.out.println("Insufficient fuel for the trip. Vehicle cannot make the trip.");
                }
            } else {
                System.out.println("Invalid fuel consumption value.");
            }
            return -1.0; // Negative value indicates insufficient fuel or invalid input
        }

        // Delete: Set the current fuel level to zero (simulate emptying the fuel tank)
        public void emptyFuelTank() {
            currentFuel = 0.0;
            saveFuelDataToFile(); // Save updated fuel data to file
        }

        // Load fuel data from the file
        private void loadFuelDataFromFile() {
            try (Scanner scanner = new Scanner(new File(fuelFileName))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String id = parts[0].trim();
                        double capacity = Double.parseDouble(parts[1].trim());
                        double fuel = Double.parseDouble(parts[2].trim());
                        fuelData.put(id, fuel);
                        if (id.equals("Current")) {
                            currentFuel = fuel;
                            fuelCapacity = capacity;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // Save fuel data to the file
        private void saveFuelDataToFile() {
            try (PrintWriter writer = new PrintWriter(new FileWriter(fuelFileName))) {
                for (Map.Entry<String, Double> entry : fuelData.entrySet()) {
                    writer.println(entry.getKey() + ", " + fuelCapacity + ", " + entry.getValue());
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // Abstract method for calculating fuel consumption (to be implemented by subclasses)
        public abstract double calculateFuelConsumption(double distance, double weight);
    }

