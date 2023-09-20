package Port;



//public class Port implements Entity {
//    private String id; // Unique identifier for the port (formatted as p-number)
//    private String name;
//    private double latitude;
//    private double longitude;
//    private int storingCapacity; // Maximum weight the port can store
//    private boolean hasLandingAbility; // Indicates if the port can handle trucks
//    private List<Container> containers; // List to store containers in the port
//    private List<Vehicle> vehicles; // List to store vehicles currently in the port
//    private List<Trip> tripHistory; // List to store trip history for the port
//
//    // Constructor
//    public Port(String id, String name, double latitude, double longitude, int storingCapacity, boolean hasLandingAbility) {
//        this.id = id;
//        this.name = name;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.storingCapacity = storingCapacity;
//        this.hasLandingAbility = hasLandingAbility;
//        this.containers = new ArrayList<>();
//        this.vehicles = new ArrayList<>();
//        this.tripHistory = new ArrayList<>();
//    }
//
//
//    // Method to add a container to the port
//    public void addContainer(Container container) {
//        if (getTotalContainerWeight() + container.getWeight() <= storingCapacity) {
//            containers.add(container);
//            System.out.println("Container added to port: " + name);
//        } else {
//            System.out.println("Port's storage capacity exceeded. Cannot add container.");
//        }
//    }
//
//    // Method to remove a container from the port
//    public void removeContainer(Container container) {
//        if (containers.remove(container)) {
//            System.out.println("Container removed from port: " + name);
//        } else {
//            System.out.println("Container not found in port: " + name);
//        }
//    }
//
//    // Method to add a vehicle to the port
//    public void addVehicle(Vehicle vehicle) {
//        vehicles.add(vehicle);
//        System.out.println("Vehicle added to port: " + name);
//    }
//
//    // Method to remove a vehicle from the port
//    public void removeVehicle(Vehicle vehicle) {
//        if (vehicles.remove(vehicle)) {
//            System.out.println("Vehicle removed from port: " + name);
//        } else {
//            System.out.println("Vehicle not found in port: " + name);
//        }
//    }
//
//    // Method to calculate total weight of containers in the port
//    public int getTotalContainerWeight() {
//        int totalWeight = 0;
//        for (Container container : containers) {
//            totalWeight += container.getWeight();
//        }
//        return totalWeight;
//    }
//
//    // Method to calculate distance between this port and another port
//    public double calculateDistance(Port otherPort) {
//        // Using a simplified method to calculate distance based on latitude and longitude differences
//        // In real-world applications, a more accurate method like the Haversine formula might be used
//        return Math.sqrt(Math.pow((this.latitude - otherPort.latitude), 2) + Math.pow((this.longitude - otherPort.longitude), 2));
//    }
//
//// Getters and Setters
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public int getStoringCapacity() {
//        return storingCapacity;
//    }
//
//    public void setStoringCapacity(int storingCapacity) {
//        this.storingCapacity = storingCapacity;
//    }
//
//    public boolean isHasLandingAbility() {
//        return hasLandingAbility;
//    }
//
//    public void setHasLandingAbility(boolean hasLandingAbility) {
//        this.hasLandingAbility = hasLandingAbility;
//    }
//
//    public List<Container> getContainers() {
//        return containers;
//    }
//
//    public void setContainers(List<Container> containers) {
//        this.containers = containers;
//    }
//
//    public List<Vehicle> getVehicles() {
//        return vehicles;
//    }
//
//    public void setVehicles(List<Vehicle> vehicles) {
//        this.vehicles = vehicles;
//    }
//
//    public List<Trip> getTripHistory() {
//        return tripHistory;
//    }
//
//    public void setTripHistory(List<Trip> tripHistory) {
//        this.tripHistory = tripHistory;
//    }
//
//
//    @Override
//    public void create() {
//
//    }
//
//    @Override
//    public void read() {
//
//    }
//
//    @Override
//    public void update() {
//
//    }
//
//    @Override
//    public void save() {
//        // Save the port details to a database or other storage
//        System.out.println("Saving port details for: " + name);
//    }
//
//    @Override
//    public void delete() {
//        // Delete the port details from a database or other storage
//        System.out.println("Deleting port: " + name);
//    }
//}

import java.util.HashMap;
import java.util.ArrayList;
public class Port {
    private String portId; // Unique ID (p-number)
    private String name; // Name of the port
    private double latitude; // Latitude
    private double longitude; // Longitude
    private double storingCapacity; // Storing capacity
    private boolean landingAbility; // Landing ability for trucks

    private String containerInfor_ID;
    private HashMap<String, Double> containers; // Dictionary to store container IDs and their weights
    private HashMap<String, Boolean> vehicles; // Dictionary to store vehicle IDs
    private ArrayList<String> trafficHistory; // List to store past and current traffic

    public Port(String portId, String name, double latitude, double longitude, double storingCapacity, boolean landingAbility, String containerInfor_ID) {
        this.portId = portId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.containers = new HashMap<>();
        this.vehicles = new HashMap<>();
        this.trafficHistory = new ArrayList<>();
    }

    public String addContainer(String containerId, double weight) {
        double currentWeight = containers.values().stream().mapToDouble(Double::doubleValue).sum();
        if (currentWeight + weight > storingCapacity) {
            return "Insufficient storage capacity.";
        }
        containers.put(containerId, weight);
        return "Container added successfully.";
    }

    public String removeContainer(String containerId) {
        if (containers.containsKey(containerId)) {
            containers.remove(containerId);
            return "Container removed successfully.";
        }
        return "Container not found.";
    }

    public String addVehicle(String vehicleId) {
        vehicles.put(vehicleId, true);
        return "Vehicle added successfully.";
    }

    public String removeVehicle(String vehicleId) {
        if (vehicles.containsKey(vehicleId)) {
            vehicles.remove(vehicleId);
            return "Vehicle removed successfully.";
        }
        return "Vehicle not found.";
    }

    public void addTraffic(String tripInfo) {
        trafficHistory.add(tripInfo);
    }

    public double calculateDistance(Port otherPort) {
        double earthRadius = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(otherPort.latitude - this.latitude);
        double dLon = Math.toRadians(otherPort.longitude - this.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(otherPort.latitude)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return distance;
    }

    // Getter Setter

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }
}

