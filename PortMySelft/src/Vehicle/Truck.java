//package Vehicle;
//
//import java.util.ArrayList;
//import java.util.List;
//import Port.Port;
//import Container.Container;
//public class Truck implements Vehicle {
//    private String id; // Unique ID for the truck
//    private int fuel; // Current fuel level
//    private int carryingCapacity; // Maximum weight the truck can carry
//    private List<Container> containers = new ArrayList<>(); // List of containers currently on the truck
//
//    public Truck(String id, int fuel, int carryingCapacity) {
//        this.id = id;
//        this.fuel = fuel;
//        this.carryingCapacity = carryingCapacity;
//    }
//
////    @Override
////    public void load(Container c) {
////        if ("Dry Storage".equals(c.getType()) || "Open Top".equals(c.getType()) || "Open Side".equals(c.getType())) {
////            containers.add(c);
////        } else {
////            System.out.println("This truck can't carry this type of container.");
////        }
////    }
////
////    @Override
////    public void unload(Container c) {
////        containers.remove(c);
////    }
//
//    @Override
//    public void move(Port p) {
//        if (canMove(p)) {
//            // Update port and fuel status...
//        }
//    }
//
//    @Override
//    public boolean canMove(Port p) {
//        return fuel > 0; // Simplified for example
//    }
//
//    @Override
//    public void refuel() {
//        this.fuel = 100; // Reset fuel to 100%
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
//        // Save to database or file
//    }
//
//    @Override
//    public void delete() {
//        // Delete from database or file
//    }
//
//    // Additional Truck-specific methods can go here
//}
//
