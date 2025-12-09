package grid;

import java.util.List;
import ship.Orientation; 
import ship.Ship;

public abstract class OwnGrid extends Grid {

    // the ships physically on this board
    protected List<Ship> ships;

    public List<Ship> getShips() {
        return ships;
    }

    public abstract boolean canPlaceShip(int row, int col, Orientation orientation, Ship ship);

    public abstract void placeShip(int row, int col, Orientation orientation, Ship ship);

    public abstract boolean applyShot(int row, int col); // returns true if hit

    public abstract boolean allShipsSunk();

    @Override
    public abstract void printGrid();
}
