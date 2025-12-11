package player;

import java.util.Scanner;

import grid.GuessGrid;
import grid.HumanGuessGrid;
import grid.HumanOwnGrid;
import grid.OwnGrid;
import ship.Orientation;
import ship.Ship;

public class HumanPlayer extends Player {

    private static final int[] FLEET = {2, 3, 3, 4, 5};
    private Scanner scanner;

    public HumanPlayer(GuessGrid guess, OwnGrid own) {
        super(guess, own);
        scanner = new Scanner(System.in);
    }

    private HumanOwnGrid getHumanOwnGrid() {
        return (HumanOwnGrid) own;
    }

    private HumanGuessGrid getHumanGuessGrid() {
        return (HumanGuessGrid) guess;
    }

    @Override
    public void placeShips() {
        for (int length : FLEET) {
            boolean placed = false;
            while (!placed) {
                System.out.println("Place ship of length " + length);

                // Get row
                int row = readCoordinate("Enter row (0-9):");
                if (row == -1) continue;

                // Get column
                int col = readCoordinate("Enter column (0-9):");
                if (col == -1) continue;

                // Get orientation
                Orientation orientation = readOrientation();
                if (orientation == null) continue;

                // Create ship and try to place
                Ship ship = new Ship(length);
                if (getHumanOwnGrid().canPlaceShip(ship, row, col, orientation)) {
                    getHumanOwnGrid().placeShip(ship, row, col, orientation);
                    System.out.println("Ship placed successfully!");
                    getHumanOwnGrid().printGrid();
                    placed = true;
                } else {
                    showErrorMessage("Cannot place ship there. Out of bounds or overlapping. Try again.");
                }
            }
        }
    }

    @Override
    public boolean receiveShot(int row, int col) {
        return getHumanOwnGrid().applyShot(row, col);
    }

    /**
     * Prompts the human player to enter target coordinates for their shot.
     * @return int array with [row, col]
     */
    public int[] askForShot() {
        while (true) {
            int row = readCoordinate("Enter target row (0-9):");
            if (row == -1) continue;

            int col = readCoordinate("Enter target column (0-9):");
            if (col == -1) continue;

            return new int[]{row, col};
        }
    }

    /**
     * Records the result of a shot on the guess grid.
     */
    public void recordShot(int row, int col, boolean hit) {
        getHumanGuessGrid().recordShot(row, col, hit);
        System.out.println(hit ? "HIT!" : "MISS!");
    }

    /**
     * Displays the guess grid (shots taken at opponent).
     */
    public void viewGuessBoard() {
        System.out.println("=== Your Guess Board ===");
        guess.printGrid();
    }

    /**
     * Displays the own grid (your ships and incoming shots).
     */
    public void showOwnBoard() {
        System.out.println("=== Your Board ===");
        own.printGrid();
    }

    // ========== Input helpers ==========

    /**
     * Reads a coordinate (0-9) from user input.
     * @return the coordinate, or -1 if invalid
     */
    private int readCoordinate(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine().trim();
        try {
            int value = Integer.parseInt(input);
            if (value < 0 || value > 9) {
                showErrorMessage("Value must be between 0 and 9. Try again.");
                return -1;
            }
            return value;
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid number. Please enter a digit 0-9.");
            return -1;
        }
    }

    /**
     * Reads orientation from user input.
     * @return Orientation enum, or null if invalid
     */
    private Orientation readOrientation() {
        System.out.println("Enter orientation (HORIZONTAL/VERTICAL):");
        String input = scanner.nextLine().trim().toUpperCase();
        if (input.equals("HORIZONTAL") || input.equals("H")) {
            return Orientation.HORIZONTAL;
        } else if (input.equals("VERTICAL") || input.equals("V")) {
            return Orientation.VERTICAL;
        } else {
            showErrorMessage("Invalid orientation. Enter HORIZONTAL or VERTICAL.");
            return null;
        }
    }
}

