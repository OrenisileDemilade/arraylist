/*
*
* [CoffeeShop]
*
* COMP 1020 SECTION [A02]
* INSTRUCTOR: [Lauren Himbeault ]
* NAME: [Demilade Orenisile]
* ASSIGNMENT: [Assignment #02]
* QUESTION: [question #]
*
* PURPOSE: [To create a game that reads data from a text file and sends data to another text file]
*/

import java.util.Optional;
import java.util.Scanner;

public class TheGame {

    public static void main(String[] args) {
        String secret = null;
        String decrypted = "";
        Action[] actions = new Action[6];

        // Initialize an array of Action objects, representing different game actions.
        actions[0] = new Action(1, "See all Maps");
        actions[1] = new Action(2, "Open map");
        actions[2] = new Action(3, "Decrypt map");
        actions[3] = new Action(4, "Write down secret");
        actions[4] = new Action(5, "Send information to Captain Lila");
        actions[5] = new Action(0, "Exit");

        // Get map list
        Map[] mapList = Map.readFile("folio.txt");
        Map selectedMap = new Map("", 0, "");
        
        // Display a welcome message and the available game actions.
        System.out.println("Welcome to the Secret of the Island! Choose an option to start the game.");
        Scanner scnr = new Scanner(System.in);
        int option = -1;

        // int option = getUserOption(scnr);

        while (option < actions.length) {
            if (option == 1) {
                Action.seeMapList(mapList);
            }

            if (option == 2) {
                Optional<Map> map = Action.getMap(scnr, mapList);
                 if (map.isPresent()) {
                    selectedMap = map.get();
                    selectedMap.readMap();
                } else {
                    System.out.println("Map not found. Please kindly try again.\n");
                    continue;
                }

            }

            if (option == 3) {
                if (selectedMap.name != "") {
                    decrypted = selectedMap.decrypted();
                    System.out.println(decrypted);
                } else {
                    System.out.println("You've not selected any map");
                }
            }

            if (option == 4) {
                if (selectedMap.name != "") {
                    decrypted = selectedMap.decrypted();
                    secret = Action.validateSecret(selectedMap, decrypted);
                    if (secret != null) {
                        selectedMap.encrypted = false;
                        System.out.println(selectedMap.toString());
                    } else {
                        System.out.println("Invalid Secret, Kindly try again or decrypt the map.");
                        continue;
                    }
                } else {
                    System.out.println("You've not selected any map");
                }

            }

            if(option == 5) {
                if(secret != null) {
                    Map.saveSecret(decrypted);
                    System.out.println("Successfully wrote secret to the file.");
                    System.out.println(secret);
                } else {
                    System.out.println("You have no secret for captian lila.");
                }
            }

            if (option == 0) {
                Action.exitGame();
                scnr.close();
                break; // exit the loop .... i don't know any other way to exit the loop
            }

            displayAction(actions);
            option = getUserOption(scnr);
        }
    }

    // Method to get the user's input option.
    public static int getUserOption(Scanner scanner) {
        System.out.print("Option: ");
        int option = scanner.nextInt();
        return option;
    }

    // Method to display available game actions.
    public static void displayAction(Action[] actions) {
        for (Action action : actions) {
            System.out.println(action.id + " - " + action.title);
        }
    }
}
