    package Weight;
    import java.io.*;
    import java.util.*;
    //jioasidasniodn
        public class CRUD_Vehicle_port_toPort {
        public static void runPortToVehicleTransfer() throws IOException {
            // Simulate the checkWeightLimit function returning true
            boolean isWithinLimit = true;

            if (isWithinLimit) {
                System.out.println("About to call updateData...");
                updateData("c-001", "p-001");
            } else {
                System.out.println("isWithinLimit is false, not calling updateData.");
            }
        }

        public static void updateData(String containerId, String portBID) throws IOException {
            try {
                // Read current data from Container.txt and Port.txt
                Map<String, Integer> containerData = readContainerData();
                Map<String, Integer> portData = readPortData();

                // Get the full weight from the specified container
                int weight = containerData.get(containerId);

                // Set the container's weight to 0 in the containerData map
                containerData.put(containerId, 0);

                // Add the weight to the current weight of the port
                portData.put(portBID, portData.get(portBID) + weight);

                // Write updated data back to Container.txt and Port.txt
                writeContainerData(containerData);
                writePortData(portData);

                System.out.println("Data updated successfully.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        public static Map<String, Integer> readContainerData() throws IOException {
            Map<String, Integer> data = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    data.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            return data;
        }

        public static Map<String, Integer> readPortData() throws IOException {
            Map<String, Integer> data = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    data.put(parts[0], Integer.parseInt(parts[3]));
                }
            }
            return data;
        }

        public static void updatePortData(String portBID, int newWeight) throws IOException {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(portBID)) {
                        parts[3] = String.valueOf(newWeight);
                        line = String.join(",", parts);
                    }
                    lines.add(line);
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        public static void writeContainerData(Map<String, Integer> data) throws IOException {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Container.txt"))) {
                for (Map.Entry<String, Integer> entry : data.entrySet()) {
                    bw.write(entry.getKey() + "," + entry.getValue());
                    bw.newLine();
                }
            }
        }

        public static void writePortData(Map<String, Integer> data) throws IOException {
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (data.containsKey(parts[0])) {
                        parts[3] = String.valueOf(data.get(parts[0]));
                        line = String.join(",", parts);
                    }
                    lines.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading Port.txt: " + e.getMessage());
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("DEADLINE_HATERS_ContainerPortManagement/src/Data/Port.txt"))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error writing to Port.txt: " + e.getMessage());
            }
        }

    }