package battleship;

public class Ship {
    public enum Type {
        CARRIER("Aircraft Carrier",5),BATTLESHIP("Battleship",4),
        SUBMARINE("Submarine",3),CRUISER("Cruiser",3),DESTROYER("Destroyer",2);
        String name;
        int cells;
        Type(String name,int cells) {
            this.name = name;
            this.cells = cells;
        }
        public String getName() {
            return name;
        }
        public int getCells() {
            return cells;
        }
    }
    private int cells;
    private Field[] field;
    private Type type;
    String name;
    public Ship(Type type, int x0, int y0, int x1, int y1) {
        this.type = type;
        this.buildShip();
        this.initializeShip(x0,y0,x1,y1);
    }
    private void buildShip() {
        switch (type) {
            case CARRIER:
                cells = 5;
                field = new Field[cells];
                name = "Aircraft Carrier";
                break;
            case BATTLESHIP:
                cells = 4;
                field = new Field[cells];
                name = "Battleship";
                break;
            case SUBMARINE:
                cells = 3;
                field = new Field[cells];
                name = "Submarine";
                break;
            case CRUISER:
                cells = 3;
                field = new Field[cells];
                name = "Cruiser";
                break;
            case DESTROYER:
                cells = 2;
                field = new Field[cells];
                name = "Destroyer";
                break;
            default:
                System.out.println("Error, wrong type of ship");
                break;
        }
    }
    public void initializeShip(int x0, int y0, int x1, int y1) {
        if (x0 == x1) {
            if (y1 > y0) {
                if (y1 - y0 + 1 == cells) {
                    for (int i = 0; i < cells; i++) {
                        field[i] = new Field(x0, y0 + i);
                    }
                }
            } else {
                if (y0 - y1 + 1 == cells) {
                    for (int i = 0; i < cells; i++) {
                        field[i] = new Field(x0, y0 - i);
                    }
                }
            }
        } else if (y0 == y1) {
            if (x1 > x0) {
                if (x1 - x0 + 1 == cells) {
                    for (int i = 0; i < cells; i++) {
                        field[i] = new Field(x0 + i, y0);
                    }
                }
            } else {
                if (x0 - x1 + 1 == cells) {
                    for (int i = 0; i < cells; i++) {
                        field[i] = new Field(x0 - i, y0);
                    }
                }
            }
        } else {
            System.out.println("Error wrong ship cords");
        }
    }
    public Field[] getField() {
        return field;
    }
    public boolean isSunk(Grid grid) {
        for (Field f : field) {
            if (grid.getField(f.getX(),f.getY()).getState() == Field.State.OCCUPIED) {
                return false;
            }
        }
        return true;
    }
}
