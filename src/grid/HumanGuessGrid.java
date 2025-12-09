package grid;

import cell.CellStatus;

public class HumanGuessGrid extends GuessGrid {

    @Override
    public void recordShot(int row, int col, boolean hit) {
        if (hit) {
            cells[row][col].setStatus(CellStatus.HIT);
        } else {
            cells[row][col].setStatus(CellStatus.MISS);
        }
    }

    @Override
    public void printGrid() {
        System.out.println("=== Guess Board ===");
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                switch (cells[r][c].getStatus()) {
                    case HIT -> System.out.print(" X ");
                    case MISS -> System.out.print(" O ");
                    default -> System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}
