package grid;

public abstract class GuessGrid extends Grid {

    public abstract void recordShot(int row, int col, boolean hit);

    @Override
    public abstract void printGrid();
}
