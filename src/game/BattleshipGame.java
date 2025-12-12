package game;

import grid.ComputerGuessGrid;
import grid.ComputerOwnGrid;
import grid.HumanGuessGrid;
import grid.HumanOwnGrid;

import player.ComputerPlayer;
import player.HumanPlayer;

public class BattleshipGame {

    private HumanPlayer human;
    private ComputerPlayer computer;

    public void startNewGame() {
        System.out.println("=== Welcome to Battleship ===");

        HumanGuessGrid humanGuess = new HumanGuessGrid();
        HumanOwnGrid humanOwn = new HumanOwnGrid();

        ComputerGuessGrid compGuess = new ComputerGuessGrid();
        ComputerOwnGrid compOwn = new ComputerOwnGrid();

        human = new HumanPlayer(humanGuess, humanOwn);
        computer = new ComputerPlayer(compGuess, compOwn);

        System.out.println("\nPlace your ships:");
        human.placeShips();
        System.out.println("\nComputer placing ships...");
        computer.placeShips();
        System.out.println("\n=== All ships placed — Let the battle begin! ===");
        play();
    }

    private void play() {
        boolean gameOver = false;

        while (!gameOver) {
            // Human turn
            System.out.println("\n=== Your turn ===");
            int row = 0;
            int col = 0;
            boolean valid = false;

            // Loop until a valid target is given
            while (!valid) {
                int[] shot = human.askForShot();
                row = shot[0];
                col = shot[1];

                if (computer.getOwnGrid().isValidShot(row, col)) {
                    valid = true;
                } else {
                    System.out.println("Invalid target — you already shot there. Try again.");
                }
            }

            // apply valid shot
            boolean hit = computer.receiveShot(row, col);
            human.recordShot(row, col, hit);
            human.viewGuessBoard();


            if (computer.getOwnGrid().allShipsSunk()) {
                System.out.println("\nYou win!");
                break;
            }

            // Computer turn
            System.out.println("\n=== Computer's turn ===");
            new java.util.Scanner(System.in).nextLine();

            int[] move = computer.chooseMove();
            boolean hit2 = human.receiveShot(move[0], move[1]);
            computer.noteShotResult(move[0], move[1], hit2);
            System.out.println("Computer shot at (" + (move[0]+1) + "," + (move[1]+1) + ") "
                               + (hit2 ? "\nHIT!" : "\nMISS."));
            human.showOwnBoard();

            if (human.getOwnGrid().allShipsSunk()) {
                System.out.println("\nComputer wins!");
                break;
            }
        }
    }
}
