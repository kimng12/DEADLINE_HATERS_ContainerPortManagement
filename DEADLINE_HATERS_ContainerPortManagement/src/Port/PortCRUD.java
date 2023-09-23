package Port;

import Container.ContainerCRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PortCRUD {
    public  static  int getPortCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
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
        String portId = String.format("p-%03d", nextId);
        String fullDetails = portId + ", " + portDetails;

        try (FileWriter writer = new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt", true)) {
            writer.write(fullDetails + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static List<String> readPorts() {
        List<String> ports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
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
        try (FileWriter writer = new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt")) {
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
        try (FileWriter writer = new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt")) {
            for (String port : ports) {
                writer.write(port + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting from the file.");
            e.printStackTrace();
        }
    }


    // Method to read storingCapacity of port B from port.txt
    public static double readPortStoringCapacity(String portId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(portId)) {
                    // Assuming that storingCapacity is in the 5th column (index 4)
                    if (parts.length >= 5) {
                        String capacityStr = parts[4].trim().replaceAll("[^\\d.]", "");
                        return Double.parseDouble(capacityStr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0; // Default if not found
    }

    public static boolean checkLandingAbility(String portId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].trim().equals(portId)) {
                    // Assuming that landingAbility is in the 6th column (index 5)
                    if (parts.length >= 6) {
                        String landingAbilityStr = parts[5].trim().toLowerCase();
                        return landingAbilityStr.equals("yes");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Default if not found or "no"
    }


    // Method to check the landing ability of a port
//    public static boolean checkPortAbility(String portId) {
//        // I want the system can check the information base on the ID inputted
//        // Take the data of the port StoringCapacity and container weight
//        // Then compare them
//        double portStoringCapacity = readPortStoringCapacity(portId);
//
//    }
}
