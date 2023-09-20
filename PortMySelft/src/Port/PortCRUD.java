package Port;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PortCRUD {
    public  static  int getPortCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("PortMySelft/src/Data/Port.txt"))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e){
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
        return count;
    }

    public static void createPort(String portDetails) {
        int nextId = getPortCount() + 1;
        String portId = "p-" + nextId;
        String fullDetails = portId + ", " + portDetails;

        try (FileWriter writer = new FileWriter("PortMySelft/src/Data/Port.txt", true)) {
            writer.write(fullDetails + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static List<String> readPorts() {
        List<String> ports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("PortMySelft/src/Data/Port.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ports.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return ports;
    }

    public static void updatePort(String oldPortDetails, String newPortDetails) {
        List<String> ports = readPorts();
        try (FileWriter writer = new FileWriter("port_data.txt")) {
            for (String port : ports) {
                if (port.equals(oldPortDetails)) {
                    writer.write(newPortDetails + "\n");
                } else {
                    writer.write(port + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
            e.printStackTrace();
        }
    }

    public static void deletePort(String portToDelete) {
        List<String> ports = readPorts();
        ports.remove(portToDelete);
        try (FileWriter writer = new FileWriter("port_data.txt")) {
            for (String port : ports) {
                writer.write(port + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting from the file.");
            e.printStackTrace();
        }
    }
}
