import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Action {
    int id;
    String title;

    public Action(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public static void seeMapList(Map[] maps) {
        // Print the Map objects stored in the array.
        for (Map loop : maps) {
            System.out.println(loop);
        }
    }

    public static Optional<Map> getMap(Scanner scnr, Map[] mapList) {
        // Scanner scnr = new Scanner(System.in);
        System.out.println("Which map do you want to open ?");
        System.out.print("Map name: ");
        String name = scnr.next();

        // Find the map with the specified name from the mapList using Java streams.
        Optional<Map> singleMap = Arrays.stream(mapList)
                .filter(data -> data.name.equals(name))
                .findFirst();

        // scnr.close();
        return singleMap;
    }
    //to accept users secret and also check if the secret matches the selected map
    public static String validateSecret(Map selected, String decrypted) {
        String word = null;
        Scanner scnr = new Scanner(System.in);
        System.out.println("What is " + selected.name + " secret?" );
        String secret = scnr.nextLine();

        if(secret.equals(decrypted.trim())) {
            word = secret;
        }
        return word;
    }
    //to exit the game
    public static void exitGame() {
        Map.deleteSecret();
        System.out.println("Thank you for playing the game");
        System.out.println("The game is over");
    }
}