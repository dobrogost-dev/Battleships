package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Ship.Type[] ships = new Ship.Type[] { Ship.Type.CARRIER, Ship.Type.BATTLESHIP,
        Ship.Type.SUBMARINE, Ship.Type.CRUISER, Ship.Type.DESTROYER};
        Grid gridA = new Grid(10,10);
        Grid gridB = new Grid(10,10);
        Game player1 = new Game("Player 1",gridA, ships.length);
        Game player2 = new Game("Player 2",gridB, ships.length);
        System.out.println(player1.getName() + ", place your ships on the game field\n");
        player1.getGrid().displayGrid(false);
        while (!player1.allShipsPlaced) {
                player1.placeShips(ships);
                player1.display();
        }
        System.out.println("Press Enter and pass the move to another player\n");
        String enter = new Scanner(System.in).nextLine();

        System.out.println(player2.getName() + ", place your ships on the game field\n");
        player2.getGrid().displayGrid(false);
        while (!player2.allShipsPlaced) {
            player2.placeShips(ships);
            player2.display();
        }
        System.out.println("Press Enter and pass the move to another player\n");
        enter = new Scanner(System.in).nextLine();
        System.out.println("The game starts!\n");
        while (true) {
            if (player1.process(player2)) {
                System.out.println(player1.getName() + " won!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player\n");
            enter = new Scanner(System.in).nextLine();
            if (player2.process(player1)) {
                System.out.println(player2.getName() + " won!");
                break;
            }
            System.out.println("Press Enter and pass the move to another player\n");
            enter = new Scanner(System.in).nextLine();
        }
    }
}
