package Port;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PortCRUD {
    public static final String PORT_FILE = "src/Data/Port.txt";

    public static void createPort(String portDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PORT_FILE, true))) {
            writer.write(portDetails);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<String> readPorts() {
        List<String> ports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PORT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ports.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return ports;
    }

    public static void updatePort(String oldPortDetails, String newPortDetails) {
        List<String> ports = readPorts();
        int index = ports.indexOf(oldPortDetails);
        if (index != -1) {
            ports.set(index, newPortDetails);
            writeToFile(ports);
        } else {
            System.out.println("Port not found!");
        }
    }

    public static void deletePort(String portDetails) {
        List<String> ports = readPorts();
        ports.remove(portDetails);
        writeToFile(ports);
    }

    private static void writeToFile(List<String> ports) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PORT_FILE))) {
            for (String port: ports) {
                writer.write(port);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
