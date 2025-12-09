package player;

import grid.GuessGrid;
import grid.OwnGrid;

public abstract class Player {

    protected GuessGrid guess;
    protected OwnGrid own;

    public Player(GuessGrid guess, OwnGrid own) {
        this.guess = guess;
        this.own = own;
    }

    public GuessGrid getGuessGrid() {
        return guess;
    }

    public OwnGrid getOwnGrid() {
        return own;
    }

    public abstract void placeShips();

    public abstract boolean receiveShot(int row, int col); // true if hit

    public void showErrorMessage(String msg) {
        System.out.println("Error: " + msg);
    }
}
