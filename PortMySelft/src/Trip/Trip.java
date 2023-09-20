package Trip;
import java.util.Date;
import Port.Port;
import Vehicle.*;
public class Trip {
    private String id; // Unique identifier for the trip
    private Vehicle vehicle; // Vehicle involved in the trip
    private Date departureDate; // Departure date
    private Date arrivalDate; // Arrival date
    private Port departurePort; // Departure port
    private Port arrivalPort; // Arrival port
    private String status; // Status of the trip e.g., "Completed", "In Progress", "Cancelled", etc.

    // Constructors
    public Trip(String id, Vehicle vehicle, Date departureDate, Port departurePort, Port arrivalPort, String status) {
        this.id = id;
        this.vehicle = vehicle;
        this.departureDate = departureDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.status = status;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Port departurePort) {
        this.departurePort = departurePort;
    }

    public Port getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(Port arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Additional methods for CRUD operations could be added here, if necessary
}

