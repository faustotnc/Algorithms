/**
 * The hill climbing algorithm looks at the possible paths a program can take,
 * and takes a small step towards the path that leads to the optimal solution.
 * 
 * This particular Hill-Climbing algorithm implements random restarts
 * (Hill-Climbing with random restarts) if the path taken leads to a local
 * minima (that is, the output cannot be improved further).
 * 
 * @author Fausto German
 * @version Feb 10th, 2021
 */
public class HillClimb {
    private Board board;
    private int currentHeuristics;
    private int resetCount = 0;
    private int stateChangeCount = 0;

    /**
     * Initializes the Hill-Climbing algorithm
     * 
     * @param board The board whose solution will be computed using the
     *              Hill-Climbing algorithm.
     */
    public HillClimb(Board board) {
        this.board = board;
        currentHeuristics = this.board.getTotalCollisions();
    }

    /**
     * Finds a solution using the Hill-Climbing algorithm.
     * 
     * @param maxSteps The max number of steps/restarts the algorithm can take.
     */
    public void climb(int maxSteps) {
        boolean isSolved = false;

        for (int step = 0; step < maxSteps; step++) {
            // Possible Paths the program can take at this step
            int[] possiblePaths = board.getNeighboringHeuristics();
            // Index of the lowest path. We start with the lowest path being
            // the first item in the list of possible paths, hence 0.
            int indexOfLowest = 0;
            // Number of paths found that will result in a lower heuristic
            int betterPathsCount = 0;

            // Gets the smallest path
            for (int i = 0; i < possiblePaths.length; i++) {
                int path = possiblePaths[i];

                if (path < currentHeuristics)
                    betterPathsCount++;
                if (path < possiblePaths[indexOfLowest])
                    indexOfLowest = i;
            }

            // Prints information about the current state
            System.out.println("\nCurrent Heuristics: " + currentHeuristics);
            System.out.println("Neighbor States With Lower Heuristics: " + betterPathsCount);
            System.out.println(
                    "Lowest Heuristic Possible: " + ((currentHeuristics == 0) ? "N/A" : possiblePaths[indexOfLowest]));
            System.out.println("---- Board State ----");
            board.print();

            // If the current heuristics is zero (i.e, there are no collisions at this
            // step), we have found a solution, and the loop can be stopped.
            if (currentHeuristics == 0) {
                System.out.println("\u001b[34;1m\nSOLUTION FOUND!\u001b[0m");
                System.out.println("Number of Resets: " + resetCount);
                System.out.println("Number of State Changes: " + stateChangeCount);
                isSolved = true;
                board.graphicBoard.showBoard();

                // stop the climb
                break;
            }

            // But if not, then we need to either restart the climb, or take a step forward.
            System.out.print("Next Action: ");

            // If there are no better paths at this step, then we start the board.
            if (betterPathsCount == 0) {
                restartClimb();
            } else {
                // If there is a lowest path, then we take a step forward in that direction.
                stepForward(indexOfLowest);
            }
        }

        // If we could not solve the problem with the given number of steps, we throw an
        // error message.
        if (!isSolved) {
            String msg = "\n\u001b[31;1m***** A SOLUTION COULD NOT BE FOUND AFTER ";
            msg += maxSteps + " STEPS.\u001b[0m";
            System.out.println(msg);
        }
    }

    /**
     * Takes a step forward in the direction of the `lowestIndex` path.
     * 
     * @param lowestIndex The path that, if taken, will produce a the lower
     *                    heuristic than the current heuristic.
     */
    private void stepForward(int lowestIndex) {
        int queen = Math.floorDiv(lowestIndex, board.size - 1);
        int currentQueensRow = board.getCurrentQueenPos(queen);
        int offset = lowestIndex - (queen * (board.size - 1));
        int newRow = (offset >= currentQueensRow) ? offset + 1 : offset;

        System.out.println("Move queen #" + queen + " to row #" + newRow + ".");
        currentHeuristics = board.moveQueenTo(queen, newRow).getTotalCollisions();
        stateChangeCount++;
    }

    /**
     * Restarts the climb, and starts the process again.
     */
    private void restartClimb() {
        System.out.println("RESTART.");
        currentHeuristics = this.board.reset().getTotalCollisions();
        resetCount++;
    }
}
