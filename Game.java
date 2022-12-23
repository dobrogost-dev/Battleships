package battleship;

import java.util.Scanner;

public class Game {
    private String name;
    private Grid grid;
    private Scanner scanner;
    boolean allShipsPlaced;
    int shipsCount;
    Ship[] ships;
    Game(String name, Grid grid, int shipsAmount) {
        this.name = name;
        this.grid = grid;
        scanner = new Scanner(System.in);
        allShipsPlaced = false;
        shipsCount = 0;
        this.ships = new Ship[shipsAmount];
    }
    public void placeShips(Ship.Type[] types) {
        System.out.println("Enter the coordinates of the " + types[shipsCount].getName()
                + " (" + types[shipsCount].getCells() + " cells):\n");
        boolean choosing = true;
        do {
            String[] input = scanner.nextLine().split(" ");
            System.out.println();
            if (input[0] != null ? input[0].matches("([A-J][1-9])|([A-J]10)") : false
                    && input[1] != null ? input[1].matches("([A-J][1-9])|([A-J]10)") : false) {
                int x0 = Integer.parseInt(input[0].substring(1)) - 1;
                int x1 = Integer.parseInt(input[1].substring(1)) - 1;
                int y0 = (int) input[0].charAt(0) - 'A';
                int y1 = (int) input[1].charAt(0) - 'A';
                // FOR TESTING System.out.println("input[0]: " + input[0]);
                // FOR TESTING System.out.println("input[1]: " + input[1]);
                // FOR TESTING System.out.println("x: " + x0 + " " + x1);
                // FOR TESTING System.out.println("y: " + y0 + " " + y1);
                if (y0 == y1) {
                    if (Math.abs(x0 - x1) + 1 != types[shipsCount].getCells()) {
                        System.out.println("Error! Wrong length of the " +
                                types[shipsCount].getName() + "! Try again:\n");
                        continue;
                    }
                } else if (x0 == x1) {
                    if (Math.abs(y0 - y1) + 1 != types[shipsCount].getCells()) {
                        System.out.println("Error! Wrong length of the " +
                                types[shipsCount].getName() + "! Try again:\n");
                        continue;
                    }
                } else {
                    System.out.println("Error! Wrong ship location! Try again:\n");
                    continue;
                }
                Ship newShip = new Ship(types[shipsCount], x0, y0, x1, y1);
                if (grid.collides(newShip)) {
                    System.out.println("Error! You placed it too close to another one. Try again:\n");
                    continue;
                }
                ships[shipsCount] = newShip;
                grid.placeShip(newShip);
                shipsCount++;
                choosing = false;
            } else {
                System.out.println("Error! Wrong coordinates! Try again:\n");
            }
        } while(choosing);

        if (shipsCount == types.length) {
            allShipsPlaced = true;
        }
    }
    public boolean process(Game enemy) {
        enemy.getGrid().displayGrid(true);
        System.out.println("---------------------");
        grid.displayGrid(false);
        String action = "";
        System.out.println("Take a shot!\n");
        boolean repeat = false;
        do {
            repeat = false;
            action = scanner.next();
            while (!action.matches("([A-J][1-9])|([A-J]10)")) {
                System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                action = scanner.next();
            }
            int x = Integer.parseInt(action.substring(1)) - 1;
            int y = (int) action.charAt(0) - 'A';
            int prevStandingShips = countStandingShips(enemy.getShips(), enemy.getGrid());
            boolean hit = enemy.getGrid().shoot(x,y);
            int newStandingShips = countStandingShips(enemy.getShips(), enemy.getGrid());
            if (newStandingShips == 0) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                return true;
            } else if (prevStandingShips != newStandingShips) {
                System.out.println("You sank a ship!\n");
                //repeat = true;
            } else if (hit) {
                System.out.println("You hit a ship!\n");
            } else {
                System.out.println("You missed.\n");
            }
        } while (repeat);
        return false;
    }
    public void display() {
        System.out.println(name);
        grid.displayGrid(false);
    }
    public int countStandingShips(Ship[] ships, Grid grid) {
        int sum = 0;
        for (Ship s : ships) {
            if (!s.isSunk(grid)) {
                sum++;
            }
        }
        return sum;
    }
    public String getName() {
        return name;
    }
    public Ship[] getShips() {
        return ships;
    }
    public Grid getGrid() {
        return grid;
    }
}
