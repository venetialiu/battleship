package ship;

public class Ship {
    private final int length;
    private int hits = 0;
    private Orientation orientation;
    private int row = -1;
    private int col = -1;

    public Ship(int length) {
        this.length = length;
        this.orientation = null;
    }

    public int getLength() {
        return length;
    }

    public int getRow() {
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
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

    public boolean isPlaced() {
        return orientation != null && row >= 0 && col >= 0;
    }
}




