package battleship;

public class Field {
    public enum State {
        EMPTY,OCCUPIED,SUNK,MISS
    }
    int x;
    int y;
    private State state;
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = State.EMPTY;
    }
    public char getSymbol() {
        switch (state) {
            case EMPTY:
                return '~';
            case OCCUPIED:
                return 'O';
            case SUNK:
                return 'X';
            case MISS:
                return 'M';
            default:
                return 'E';
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    public boolean isEmpty() {
        return state == State.EMPTY;
    }
}