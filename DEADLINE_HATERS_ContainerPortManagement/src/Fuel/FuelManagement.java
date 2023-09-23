package Fuel;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FuelManagement {
    private Map<String, FuelData> fuelDataMap;

    public FuelManagement() {
        this.fuelDataMap = new HashMap<>();
        loadFuelDataFromFile();
    }

    private void loadFuelDataFromFile() {
        File file = new File("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String id = parts[0].trim();
                    System.out.println("Read ID from file: " + id);
                    String vehicleType = parts[1].trim();
                    String containerType = parts[2].trim();
                    double currentFuel = Double.parseDouble(parts[3].trim());
                    double fuelCapacity = Double.parseDouble(parts[4].trim());
                    String portID = parts[5].trim();  // Port ID should come before Container ID
                    String containerID = parts[6].trim();  // Container ID should come after Port ID
                    fuelDataMap.put(id, new FuelData(vehicleType, containerType, currentFuel, fuelCapacity, portID, containerID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFuelDataToFile() {
        File file = new File("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt"))) {
            for (Map.Entry<String, FuelData> entry : fuelDataMap.entrySet()) {
                String id = entry.getKey();
                FuelData fuelData = entry.getValue();
                writer.write(id + "," + fuelData.getVehicleType() + "," + fuelData.getContainerType() + ","
                        + fuelData.getCurrentFuel() + "," + fuelData.getFuelCapacity() + ","
                        + fuelData.getPortID() + "," + fuelData.getContainerID());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean refuel(String id, double gallons) {
        if (gallons <= 0) {
            System.out.println("Invalid fuel amount. Please provide a positive value.");
            return false;
        }

        id = id.toLowerCase();
        FuelData fuelData = fuelDataMap.get(id);
        if (fuelData == null) {
            // Try converting the input ID to lowercase and search again
            String lowercaseId = id.toLowerCase();
            fuelData = fuelDataMap.get(lowercaseId);
            if (fuelData == null) {
                System.out.println("Vehicle with ID " + id + " not found.");
                return false;
            }
            id = lowercaseId;
        }

        double currentFuel = fuelData.getCurrentFuel();
        double fuelCapacity = fuelData.getFuelCapacity();
        double newFuel = Math.min(currentFuel + gallons, fuelCapacity);
        fuelData.setCurrentFuel(newFuel);
        fuelDataMap.put(id, fuelData);
        saveFuelDataToFile();
        System.out.println("Refueled " + gallons + " gallons for vehicle ID: " + id);
        return true;
    }

    public double getCurrentFuelLevel(String id) {
        FuelData fuelData = fuelDataMap.get(id);
        if (fuelData == null) {
            System.out.println("Vehicle with ID " + id + " not found.");
            return -1; // Return a negative value to indicate an error
        }
        return fuelData.getCurrentFuel();
    }

    public boolean fillUpFuelForTrip(String id, double fuelConsumed) {
        if (fuelConsumed < 0) {
            System.out.println("Invalid fuel consumption value.");
            return false;
        }

        FuelData fuelData = fuelDataMap.get(id);
        if (fuelData == null) {
            System.out.println("Vehicle with ID " + id + " not found.");
            return false;
        }

        double currentFuel = fuelData.getCurrentFuel();
        double fuelCapacity = fuelData.getFuelCapacity();
        if (fuelConsumed <= fuelCapacity) {
            double newFuel = Math.max(currentFuel - fuelConsumed, 0.0);
            fuelData.setCurrentFuel(newFuel);
            fuelDataMap.put(id, fuelData);
            saveFuelDataToFile();
            System.out.println("Fuel consumed for trip: " + fuelConsumed + " gallons for vehicle ID: " + id);
            return true;
        } else {
            System.out.println("Fuel consumption exceeds fuel capacity for vehicle ID: " + id);
            return false;
        }
    }

    public boolean deleteVehicleData(String id) {
        FuelData fuelData = fuelDataMap.remove(id);
        if (fuelData == null) {
            System.out.println("Vehicle with ID " + id + " not found.");
            return false;
        }

        saveFuelDataToFile();
        System.out.println("Deleted data for vehicle ID: " + id);
        return true;
    }

    public boolean calculateDistanceAndDeductFuel(String id, double distance) {
        if (distance < 0) {
            System.out.println("Invalid distance value.");
            return false;
        }

        FuelData fuelData = fuelDataMap.get(id);
        if (fuelData == null) {
            System.out.println("Vehicle with ID " + id + " not found.");
            return false;
        }

        double currentFuel = fuelData.getCurrentFuel();
        double fuelConsumed = distance / getFuelEfficiency(fuelData.getVehicleType());
        return fillUpFuelForTrip(id, fuelConsumed);
    }

    private double getFuelEfficiency(String vehicleType) {
        // Add logic to get fuel efficiency based on vehicle type if needed
        // For now, assuming a constant value
        return 10.0; // Miles per gallon (adjust as needed)
    }

    public static void main(String[] args) {
        FuelManagement fuelManager = new FuelManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Refuel");
            System.out.println("2. Get Current Fuel Level");
            System.out.println("3. Fill Up Fuel for Trip");
            System.out.println("4. Calculate Distance and Deduct Fuel");
            System.out.println("5. Delete Vehicle Data");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle ID: ");
                    String refuelId = scanner.nextLine();
                    System.out.print("Enter gallons to refuel: ");
                    double refuelGallons = scanner.nextDouble();
                    fuelManager.refuel(refuelId, refuelGallons);
                    break;

                case 2:
                    System.out.print("Enter vehicle ID: ");
                    String currentFuelId = scanner.nextLine();
                    double currentFuelLevel = fuelManager.getCurrentFuelLevel(currentFuelId);
                    System.out.println("Current Fuel Level: " + currentFuelLevel);
                    break;

                case 3:
                    System.out.print("Enter vehicle ID: ");
                    String fillUpId = scanner.nextLine();
                    System.out.print("Enter fuel consumed for trip: ");
                    double fuelConsumed = scanner.nextDouble();
                    fuelManager.fillUpFuelForTrip(fillUpId, fuelConsumed);
                    break;

                case 4:
                    System.out.print("Enter vehicle ID: ");
                    String distanceId = scanner.nextLine();
                    System.out.print("Enter distance traveled (miles): ");
                    double distance = scanner.nextDouble();
                    fuelManager.calculateDistanceAndDeductFuel(distanceId, distance);
                    break;

                case 5:
                    System.out.print("Enter vehicle ID to delete: ");
                    String deleteId = scanner.nextLine();
                    fuelManager.deleteVehicleData(deleteId);
                    break;

                case 6:
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }
}

class FuelData {
    private String vehicleType;
    private String containerType;
    private double currentFuel;
    private double fuelCapacity;
    private String portID;
    private String containerID;

    public FuelData(String vehicleType, String containerType, double currentFuel, double fuelCapacity, String portID, String containerID) {
        this.vehicleType = vehicleType;
        this.containerType = containerType;
        this.currentFuel = currentFuel;
        this.fuelCapacity = fuelCapacity;
        this.portID = portID;
        this.containerID = containerID;
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

    public String getPortID() {
        return portID;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }
}
