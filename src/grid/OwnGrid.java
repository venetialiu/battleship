package grid;

import java.util.List;
import ship.Orientation; 
import ship.Ship;
import cell.Cell;
import cell.CellStatus;
import java.util.ArrayList;

public abstract class OwnGrid extends Grid {

    protected List<Ship> ships = new ArrayList<>();

    public OwnGrid() {
        super();
    }

    public boolean canPlaceShip(Ship ship, int row, int col, Orientation o) {
        int len = ship.getLength();

        for (int i = 0; i < len; i++) {
            int r = row + (o == Orientation.VERTICAL ? i : 0);
            int c = col + (o == Orientation.HORIZONTAL ? i : 0);
            if (!inBounds(r, c)) return false;
            if (cells[r][c].hasShip()) return false;
        }
        return true;
    }

    public void placeShip(Ship ship, int row, int col, Orientation o) {
        if (!canPlaceShip(ship, row, col, o)) {
            throw new IllegalArgumentException("Cannot place ship here");
        }
        ship.setPosition(row, col);
        ship.setOrientation(o);
        ships.add(ship);

        int len = ship.getLength();
        for (int i = 0; i < len; i++) {
            int r = row + (o == Orientation.VERTICAL ? i : 0);
            int c = col + (o== Orientation.HORIZONTAL ? i : 0);
            cells[r][c].setShip(true);
        }
    }

    /** Applies a shot from the opponent. Returns true if hit. */
    public boolean applyShot(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException("Shot out of bounds");
        }

        Cell cell = cells[row][col];
        if (!cell.isUnguessed()) {
            // already shot here
            return cell.getStatus() == CellStatus.HIT;
        }

        if (cell.hasShip()) {
            cell.markHit();
            // you can later walk ships to call registerHit() if needed
            return true;
        } else {
            cell.markMiss();
            return false;
        }
    }

    @Override
    public void printGrid() {
        // typically: show ships + hits/misses for your own board
        System.out.print("  ");
        for (int c = 0; c < SIZE; c++) System.out.print((c + 1) + " ");
        System.out.println();
        char[] letters = "ABCDEFGHIJ".toCharArray();

        for (int r = 0; r < SIZE; r++) {
            System.out.print(letters[r] + " ");
            for (int c = 0; c < SIZE; c++) {
                Cell cell = cells[r][c];
                char out = '-';
                if (cell.hasShip() && cell.getStatus() == CellStatus.UNGUESSED) out = 'S';
                else if (cell.getStatus() == CellStatus.HIT) out = 'X';
                else if (cell.getStatus() == CellStatus.MISS) out = 'O';
                System.out.print(out + " ");
            }
            System.out.println();
        }
    }
}

