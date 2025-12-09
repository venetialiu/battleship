package grid;

import cell.Cell;

public abstract class Grid {
    public static final int SIZE = 10;

    protected Cell[][] cells;

    public Grid() {
        cells = new Cell[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public abstract void printGrid();
}
