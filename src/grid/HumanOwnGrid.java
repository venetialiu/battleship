package grid;

import java.util.ArrayList;
import java.util.List;

import cell.CellStatus;
import ship.Orientation;
import ship.Ship;

public class HumanOwnGrid extends OwnGrid {

    public HumanOwnGrid() {
        ships = new ArrayList<>();
    }

    @Override
    public boolean canPlaceShip(int row, int col, Orientation orientation, Ship ship) {
        int len = ship.getLength();

        if (orientation == Orientation.HORIZONTAL) {
            return true;
        }
        
    }

    @Override
    public void placeShip(int row, int col, Orientation orientation, Ship ship) {
        ships.add(ship);
        int len = ship.getLength();

        if (orientation == Orientation.HORIZONTAL) {
            for (int c = col; c < col + len; c++) {
                cells[row][c].setHasShip(true);
                cells[row][c].setStatus(CellStatus.SHIP);
            }
        } else {
            for (int r = row; r < row + len; r++) {
                cells[r][col].setHasShip(true);
                cells[r][col].setStatus(CellStatus.SHIP);
            }
        }
    }

    @Override
    public boolean applyShot(int row, int col) {
        CellStatus status = cells[row][col].getStatus();
        if (status == CellStatus.HIT || status == CellStatus.MISS) {
            return false; // already shot
        }

        if (cells[row][col].hasShip()) {
            cells[row][col].setStatus(CellStatus.HIT);
            // TODO: update correct Ship.hit() based on position if you track mapping
            return true;
        } else {
            cells[row][col].setStatus(CellStatus.MISS);
            return false;
        }
    }

    @Override
    public boolean allShipsSunk() {
        // Simplest: scan for any cell with SHIP status
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (cells[r][c].getStatus() == CellStatus.SHIP) return false;
            }
        }
        return true;
    }

    @Override
    public void printGrid() {
        System.out.println("=== Your Board ===");
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                CellStatus s = cells[r][c].getStatus();
                switch (s) {
                    case SHIP -> System.out.print(" S ");
                    case HIT  -> System.out.print(" X ");
                    case MISS -> System.out.print(" O ");
                    default   -> System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}
