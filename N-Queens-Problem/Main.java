
/**
 * Proposes a solution to the N-Queens Problem using a Hill-Climbing algorithm
 * with random restarts.
 * 
 * @author Fausto German
 * @version Feb 10th, 2021
 */
public class Main {
    // Creates a board
    private static Board board = new Board(32);
    // Initializes the Hill-Climbing algorithm
    private static HillClimb hillClimb = new HillClimb(board);

    public static void main(String[] args) {
        // Tries to find a solution using the Hill-Climbing with random restarts
        // algorithm within `n` steps.
        int n = 2000;
        hillClimb.climb(n);
    }
}