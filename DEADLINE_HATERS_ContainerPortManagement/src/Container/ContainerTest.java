package Container;  // Add this line if it's in the Container package

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContainerTest {

    public static void transferContainerFromPortToVehicle(String containerId, String portId, String vehicleId) throws IOException {
        // Read the port.txt file and remove the container from the specified port
        List<String> portLines = Files.readAllLines(Paths.get("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"));
        List<String> updatedPortLines = new ArrayList<>();

        for (String line : portLines) {
            if (line.contains(containerId)) {
                line = line.replace("," + containerId, ""); // Remove the container ID from the line
            }
            updatedPortLines.add(line);
        }
        Files.write(Paths.get("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"), updatedPortLines);

        // Read the Vehicle.txt file and add the container to the specified vehicle
        List<String> vehicleLines = Files.readAllLines(Paths.get("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt"));
        List<String> updatedVehicleLines = new ArrayList<>();

        for (String line : vehicleLines) {
            if (line.startsWith(vehicleId + ",")) {
                line = line + "," + containerId; // Add the container ID to the vehicle line
            }
            updatedVehicleLines.add(line);
        }
        Files.write(Paths.get("DEADLINE_HATERS_ContainerPortManagement/src/Data/Vehicle.txt"), updatedVehicleLines);
    }
}
