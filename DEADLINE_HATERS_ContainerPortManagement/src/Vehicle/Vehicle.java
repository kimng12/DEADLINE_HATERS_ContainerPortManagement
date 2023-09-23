package Vehicle;
import Entity.Entity;
import Port.Port;
import Container.*;


public class Vehicle {
//    void load(Container c)  // Load a container onto the vehicle
//    {
//
//    }
//
//    void unload(Container c)  // Unload a container from the vehicle
//    {
//
//    }
//
//    void move(Port p)  // Move the vehicle to a different port
//    {
//
//    }
//
//    boolean canMove(Port p)  // Check if the vehicle can move to a different port
//    {
//
//
//    }
//
//    void refuel()  // Refuel the vehicle
//    {
//    }

    private String id;
    private String type;
    private int storingCapacity;

    public Vehicle(String id, String type, int storingCapacity) {
        this.id = id;
        this.type = type;
        this.storingCapacity = storingCapacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStoringCapacity() {
        return storingCapacity;
    }

    public void setStoringCapacity(int storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Type: " + type + ", Storing Capacity: " + storingCapacity;
    }
}


