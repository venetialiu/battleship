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
    public boolean inBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public abstract void printGrid();
}
