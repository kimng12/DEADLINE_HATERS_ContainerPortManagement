package Vehicle;
import Entity.Entity;
import Port.Port;
import Container.*;
public interface Vehicle extends Entity {
    void load(Container c);  // Load a container onto the vehicle
    void unload(Container c);  // Unload a container from the vehicle
    void move(Port p);  // Move the vehicle to a different port
    boolean canMove(Port p);  // Check if the vehicle can move to a different port
    void refuel();  // Refuel the vehicle

    String getName();
}

