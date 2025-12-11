package grid;
import cell.Cell;
import cell.CellStatus;

public abstract class GuessGrid extends Grid {

    public GuessGrid() {
        super();
    }

    // record your guess on the opponent
    public void recordShot(int row, int col, boolean hit) {
        Cell cell = getCell(row, col);
        if (!cell.isUnguessed()) {
            // you can throw or ignore; up to your design
            throw new IllegalArgumentException("Cell already guessed");
        }
        if (hit) {
            cell.markHit();
        } else {
            cell.markMiss();
        }
    }

    @Override
    public void printGrid() {
        // generic “X/O/-” view; subclasses can override if needed
        System.out.print("  ");
        for (int c = 0; c < SIZE; c++) System.out.print((c + 1) + " ");
        System.out.println();
        char[] letters = "ABCDEFGHIJ".toCharArray();

        for (int r = 0; r < SIZE; r++) {
            System.out.print(letters[r] + " ");
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
