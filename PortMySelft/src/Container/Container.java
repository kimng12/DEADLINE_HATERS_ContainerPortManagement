package Container;
import Entity.Entity;
//public class Container implements Entity {
//    private String id;  // Unique ID for the container
//    private double weight;  // Weight of the container
//    private String type;  // Type of the container (e.g., "Dry storage", "Open top", etc.)
//
//    // Constructors
//    public Container(String id, double weight, String type) {
//        this.id = id;
//        this.weight = weight;
//        this.type = type;
//    }
//
//    // Getters and Setters
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(double weight) {
//        this.weight = weight;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
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
//    // CRUD methods
//    @Override
//    public void save() {
//        // Logic to save the container information to a database or a file
//    }
//
//    @Override
//    public void delete() {
//        // Logic to delete the container information from a database or a file
//    }
//
//    // Additional Container-specific methods
//    public double calculateFuelConsumption() {
//        // Logic to calculate fuel consumption based on weight and type
//        return 0;  // Placeholder, implement based on your logic
//    }
//}
public class Container {
    private String id;
    private double weight;

    // Constructor
    public Container(String id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id='" + id + '\'' +
                ", weight=" + weight +
                '}';
    }
}


