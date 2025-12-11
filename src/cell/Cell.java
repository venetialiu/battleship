package cell;

public class Cell {
    private CellStatus status;
    private boolean hasShip;

    public Cell() {
        this.status = CellStatus.UNGUESSED;
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

    public void setShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public boolean isUnguessed(){
        return status == status.UNGUESSED;
    }

    public void markHit(){
        this.status = status.HIT;
    }

    public void markMiss(){
        this.status = status.MISS;
    }
}


