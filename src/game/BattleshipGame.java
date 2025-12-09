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
        HumanGuessGrid humanGuess = new HumanGuessGrid();
        HumanOwnGrid humanOwn = new HumanOwnGrid();

        ComputerGuessGrid compGuess = new ComputerGuessGrid();
        ComputerOwnGrid compOwn = new ComputerOwnGrid();

        human = new HumanPlayer(humanGuess, humanOwn);
        computer = new ComputerPlayer(compGuess, compOwn);

        System.out.println("Place your ships:");
        human.placeShips();
        System.out.println("Computer placing ships...");
        computer.placeShips();

        play();
    }

    private void startNewGame() {
        boolean gameOver = false;
        //TODO: Implement game logic
        while (!gameOver) {
            // Human turn
            System.out.println("=== Your turn ===");
            int[] shot = human.askForShot();
            boolean hit = computer.receiveShot(shot[0], shot[1]);
            human.recordShot(shot[0], shot[1], hit);
            human.viewGuessBoard();

            if (computer.getOwnGrid().allShipsSunk()) {
                System.out.println("You win!");
                break;
            }

            // Computer turn
            System.out.println("=== Computer's turn ===");
            int[] move = computer.chooseMove();
            boolean hit2 = human.receiveShot(move[0], move[1]);
            System.out.println("Computer shot at (" + move[0] + "," + move[1] + ") "
                               + (hit2 ? "HIT" : "MISS"));
            human.showOwnBoard();

            if (human.getOwnGrid().allShipsSunk()) {
                System.out.println("Computer wins!");
                break;
            }
        }
    }
}
