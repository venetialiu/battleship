package player;

import java.util.Random;

import grid.ComputerGuessGrid;
import grid.ComputerOwnGrid;
import grid.GuessGrid;
import grid.OwnGrid;
import ship.Orientation;
import ship.Ship;

public class ComputerPlayer extends Player {

    private static final int[] FLEET = {2, 3, 3, 4, 5};
    private Random random;

    public ComputerPlayer(GuessGrid guess, OwnGrid own) {
        super(guess, own);
        random = new Random();
    }

    // Helper methods to avoid repeated casting
    private ComputerOwnGrid getComputerOwnGrid() {
        return (ComputerOwnGrid) own;
    }

    private ComputerGuessGrid getComputerGuessGrid() {
        return (ComputerGuessGrid) guess;
    }

    @Override
    public void placeShips() {
        for (int length : FLEET) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(10); // 0-9
                int col = random.nextInt(10); // 0-9
                Orientation orientation = random.nextBoolean() 
                    ? Orientation.HORIZONTAL 
                    : Orientation.VERTICAL;

                Ship ship = new Ship(length);
                if (getComputerOwnGrid().canPlaceShip(ship, row, col, orientation)) {
                    getComputerOwnGrid().placeShip(ship, row, col, orientation);
                    placed = true;
                }
                // If invalid, loop tries again with new random values
            }
        }
    }

    @Override
    public boolean receiveShot(int row, int col) {
        return getComputerOwnGrid().applyShot(row, col);
    }

    /**
     * Chooses a move for the computer using the ComputerGuessGrids targeting logic
     * @return int array with [row, col]
     */
    public int[] chooseMove() {
        return getComputerGuessGrid().pickValidTarget();
    }

    public void noteShotResult(int row, int col, boolean hit) {
        getComputerGuessGrid().noteShotResult(row, col, hit);
    }
}

