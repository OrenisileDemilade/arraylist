import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Map {
    public String name;
    public int year;
    public String mapNum;
    public boolean encrypted;

    // Constructor to initialize the Map object with name, year, and map number.
    public Map(String n, int y, String m) {
        name = n;
        year = y;
        mapNum = m;
        encrypted = true;
    }

    // Override the toString method to provide a string representation of the Map
    // object.
    public String toString() {
        return "Map: " + name + ", Year: " + year + " , Is Encrypted ? " + encrypted;
    }

    // Reads data from a file and returns an array of Map objects.
    public static Map[] readFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int num = sc.nextInt(); // Read the number of map entries.
            Map[] maps = new Map[num]; // Create an array to store Map objects.
            sc.nextLine(); // Consume the newline character after reading 'num'.

            for (int i = 0; i < num; i++) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                String name = tokens[0];
                int year = Integer.parseInt(tokens[1]);
                String mapNum = tokens[2];
                Map storeMap = new Map(name, year, mapNum); // Create a Map object.
                maps[i] = storeMap; // Store the Map object in the array.
            }
            return maps; // Return the array of Map objects.

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Reads data from a file and does not return any value.
    public void readFile2(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int num = sc.nextInt();
            Map[] maps = new Map[num];
            sc.nextLine();

            for (int i = 0; i < num; i++) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                String name = tokens[0];
                int year = Integer.parseInt(tokens[1]);
                String mapNum = tokens[2];
                Map storeMap = new Map(name, year, mapNum);
                maps[i] = storeMap;
            }

            for (Map loop : maps) {
                System.out.println(loop);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads and prints the content of a file whose name is stored in 'mapNum'.
    public void readMap() {
        try {
            File file = new File(mapNum);
            Scanner rm = new Scanner(file);
            while (rm.hasNextLine()) {
                String line = rm.nextLine();
                System.out.println(line);
            }
            rm.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads and decrypts ASCII-encoded content from a file.
    public String decrypted() {
        try {
            Scanner scanner = new Scanner(new File(mapNum));
            String decrypted = "";
            while (scanner.hasNextLine()) {
                String asciis = scanner.nextLine();
                if (!asciis.equals("")) {
                    String[] splitBySpace = asciis.split("\\s+");
                    for (String split : splitBySpace) {
                        if (!split.equals("")) {
                            String[] asciiArray = split.split(",");
                            for (String asci : asciiArray) {
                                int asciNum = Integer.parseInt(asci.trim());
                                char ch = (char) asciNum;
                                decrypted += ch;
                            }
                            decrypted += " ";
                        }
                    }
                }
            }
            // Print the decrypted content.
            scanner.close();
            return decrypted;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveSecret(String secret) {
        try {
            FileWriter fw = new FileWriter("secrets.txt");
            fw.write(secret);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSecret() {
        File file = new File("secrets.txt");
        if (file.exists()) {
            file.delete();
        }

    }

}
