package grid;
import cell.Cell;
import cell.CellStatus;

public abstract class GuessGrid extends Grid {

    public GuessGrid() {
        super();
    }

    // record guess on the opponent
    public boolean recordShot(int row, int col, boolean hit) {
        Cell cell = getCell(row, col);
        if (!cell.isUnguessed()) {
            // return False if cell
            System.out.println("Cell already shot! Try again.");
            return false;
        }
        if (hit) {
            cell.markHit();
        } else {
            cell.markMiss();
        }
        return true;
    }

    @Override
    public void printGrid() {
        // generic “X/O/-” view; subclasses can override if needed
        System.out.print("  ");
        for (int c = 0; c < SIZE; c++) System.out.print(c + " ");
        System.out.println();

        for (int r = 0; r < SIZE; r++) {
            System.out.print(r + " ");
            for (int c = 0; c < SIZE; c++) {
                CellStatus s = cells[r][c].getStatus();
                char out = '-';
                if (s == CellStatus.HIT) out = 'X';
                else if (s == CellStatus.MISS) out = 'O';
                System.out.print(out + " ");
            }
            System.out.println();
        }
    }
}
