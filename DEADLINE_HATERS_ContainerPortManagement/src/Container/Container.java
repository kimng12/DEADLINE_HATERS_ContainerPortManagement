package Container;
import Entity.Entity;

public class Container {
    private String id;
    private String type; // Type of container (e.g., Standard, Refrigerated, etc.)
    private int weight; // Weight of the container in tons
    private int storingCapacity; // Storing capacity of the container in tons

    // Constructor
    public Container(String id, String type, int weight, int storingCapacity) {
        this.id = id;
        this.type = type;
        this.weight = weight;
        this.storingCapacity = storingCapacity;
    }

    // Getters and setters for the new attributes
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStoringCapacity() {
        return storingCapacity;
    }

    public void setStoringCapacity(int storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    // Getters and setters for the existing ID attribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", storingCapacity=" + storingCapacity +
                '}';
    }
}


