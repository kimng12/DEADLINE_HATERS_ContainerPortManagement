package Port;
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

