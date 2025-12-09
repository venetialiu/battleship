package cell;

public class Cell {
    private CellStatus status;
    private boolean hasShip;

    public Cell() {
        this.status = CellStatus.EMPTY;
        this.hasShip = false;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }
}


