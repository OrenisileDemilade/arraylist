import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App app = new App(); // Create an instance of App
        app.readFile();
    }

    class Map {
        private String name;
        private int year;
        private String mapNum;

        public Map(String n, int y, String m) {
            name = n;
            year = y;
            mapNum = m;
        }

        public String toString() {
            return "Map: " + name + ", Year: " + year + " , " + mapNum;
        }
    }

    public void readFile() {
        try {
            Scanner sc = new Scanner(new File("folio.txt"));
            int num = sc.nextInt();
            Map[] maps = new Map[num];
            sc.nextLine();

            for (int i = 0; i < num; i++) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                System.out.println(tokens.length);
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
}
