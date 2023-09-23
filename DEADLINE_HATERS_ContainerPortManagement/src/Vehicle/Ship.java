//package Vehicle;
//import Port.Port;
//import java.util.ArrayList;
//import java.util.List;
//import Container.Container;
//public class Ship implements Vehicle {
//    private String id;
//    private int fuel;
//    private int carryingCapacity; // Maximum weight the ship can carry
//    private List<Container> containers;
//
//    // Constructors, getters, setters...
//    public Ship(String id, int fuel, int carryingCapacity) {
//        this.id = id;
//        this.fuel = fuel;
//        this.carryingCapacity = carryingCapacity;
//    }
//    // Getters and Setters
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public int getFuel() {
//        return fuel;
//    }
//
//    public void setFuel(int fuel) {
//        this.fuel = fuel;
//    }
//
//    public int getCarryingCapacity() {
//        return carryingCapacity;
//    }
//
//    public void setCarryingCapacity(int carryingCapacity) {
//        this.carryingCapacity = carryingCapacity;
//    }
//
//    public List<Container> getContainers() {
//        return containers;
//    }
//
//    public void setContainers(List<Container> containers) {
//        this.containers = containers;
//    }
//    // Vehicle interface methods
//    @Override
//    public void load(Container c) {
//        containers.add(c);
//        System.out.println("Container loaded onto ship: " + id);
//    }
//
//    @Override
//    public void unload(Container c) {
//        containers.remove(c);
//        System.out.println("Container unloaded from ship: " + id);
//    }
//
//    @Override
//    public void move(Port p) {
//        if (canMove(p)) {
//            System.out.println("Ship " + id + " is moving to port: " + p.getName());
//            // Decrease fuel based on distance and container weight...
//        } else {
//            System.out.println("Ship " + id + " can't move to port: " + p.getName());
//        }
//    }
//
//    @Override
//    public boolean canMove(Port p) {
//        // Check if the ship has enough fuel to move to the specified port
//        // For the sake of this example, let's assume it can always move
//        return true;
//    }
//
//    @Override
//    public void refuel() {
//        // Code to refuel the ship
//        System.out.println("Refueling ship: " + id);
//    }
//
//    @Override
//    public String getName() {
//        return null;
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
//    @Override
//    public void save() {
//        // Save the ship details to a database or file
//        System.out.println("Saving ship: " + id);
//    }
//
//    @Override
//    public void delete() {
//        // Delete the ship details from a database or file
//        System.out.println("Deleting ship: " + id);
//    }
//}
//
