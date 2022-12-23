package battleship;

import javax.naming.InitialContext;

public class Grid {
    private int width;
    private int height;
    public Field[][] field;
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Field[width][height];
        initializeFields();
    }
    public void displayGrid(boolean hidden) {
        System.out.print("  ");
        for (int i = 1; i <= width; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for(int y = 0; y < height; y++) {
            System.out.print((char) ('A' + y));
            for(int x = 0; x < width; x++) {
                if (hidden) {
                    System.out.print(" " + (field[x][y].getSymbol() == 'O' ?
                            '~' : field[x][y].getSymbol()));
                } else {
                    System.out.print(" " + field[x][y].getSymbol() );
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
    private void initializeFields() {
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                field[x][y] = new Field(x,y);
            }
        }
    }
    public void placeShip(Ship ship) {
        for (Field f : ship.getField()) {
            field[f.getX()][f.getY()].setState(Field.State.OCCUPIED);
        }
    }
    public boolean isFree(int x, int y) {
        boolean top = y > 0;
        boolean bottom = y < height - 1;
        boolean right = x < width - 1;
        boolean left = x > 0;
        if (top) {
            if (!field[x][y - 1].isEmpty()) return false;
            if (left) {
                if (!field[x - 1][y - 1].isEmpty()) return false;
            }
            if (right) {
                if (!field[x + 1][y - 1].isEmpty()) return false;
            }
        }
        if (bottom) {
            if (!field[x][y + 1].isEmpty()) return false;
            if (left) {
                if (!field[x - 1][y + 1].isEmpty()) return false;
            }
            if (right) {
                if (!field[x + 1][y + 1].isEmpty()) return false;
            }
        }
        if (left) {
            if (!field[x - 1][y].isEmpty()) return false;
        }
        if (right) {
            if (!field[x + 1][y].isEmpty()) return false;
        }
        return true;
    }
    public boolean collides(Ship ship) {
        for (Field f : ship.getField()) {
            if (!field[f.getX()][f.getY()].isEmpty() ||
            !isFree(f.getX(),f.getY())) {
                return true;
            }
        }
        return false;
    }
    public boolean shoot(int x, int y) {
        if (field[x][y].getState() == Field.State.SUNK) {
            return true;
        }
        if (field[x][y].getState() == Field.State.MISS) {
            return false;
        }
        if (field[x][y].getState() == Field.State.OCCUPIED) {
            field[x][y].setState(Field.State.SUNK);
            return true;
        }
        if (field[x][y].getState() == Field.State.EMPTY) {
            field[x][y].setState(Field.State.MISS);
            return false;
        }
        return false;
    }
    public Field getField(int x, int y) {
        return field[x][y];
    }
}
