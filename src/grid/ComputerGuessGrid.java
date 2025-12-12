package grid;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class ComputerGuessGrid extends GuessGrid {

    private final Random random = new Random();

    // cells we want to try next (because they are near a hit)
    private final Deque<int[]> targetQueue = new ArrayDeque<>();

    public ComputerGuessGrid() {
        super();
    }

    /**
     * Pick a valid (row, col) target for the computer.
     * Prefers queued neighbor cells (around previous hits).
     * Falls back to a random unguessed cell.
     *
     * Returns 0-based indices: int[]{ row, col }.
     */
    public int[] pickValidTarget() {
        while (true) {
            int row;
            int col;

            // 1) Target mode: use queued neighbors first
            if (!targetQueue.isEmpty()) {
                int[] next = targetQueue.pollFirst();
                row = next[0];
                col = next[1];
            } else {
                // 2) Hunt mode: random unguessed cell
                row = random.nextInt(SIZE); // 0..SIZE-1
                col = random.nextInt(SIZE);
            }

            // If already guessed, skip and try again
            if (!cells[row][col].isUnguessed()) {
                continue;
            }

            // Return a truly unguessed cell
            return new int[] { row, col };
        }
    }

    /**
     * Call this after the computer fires at (row, col) and you know
     * whether it was a hit. this updates the guess grid and on a hit,
     * enqueues neighboring cells as future targets.
     */
    public void noteShotResult(int row, int col, boolean hit) {
        // Update our guess grid (this sets HIT or MISS)
        recordShot(row, col, hit);

        if (hit) {
            // Enqueue the four neighbors (if valid and unguessed)
            addNeighborIfCandidate(row - 1, col); // up
            addNeighborIfCandidate(row + 1, col); // down
            addNeighborIfCandidate(row, col - 1); // left
            addNeighborIfCandidate(row, col + 1); // right
        }
    }

    private void addNeighborIfCandidate(int row, int col) {
        // Check bounds
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return;
        }
        // Only add cells we haven't guessed yet
        if (!cells[row][col].isUnguessed()) {
            return;
        }
        targetQueue.addLast(new int[] { row, col });
    }
}
