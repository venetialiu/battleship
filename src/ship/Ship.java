package ship;

public class Ship {
    private final int length;
    private int hits = 0;
    private Orientation orientation;

    public Ship(int length, Orientation orientation) {
        this.length = length;
        this.orientation = orientation;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits >= length;
    }
}




