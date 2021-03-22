import java.util.Random;

/**
 * The board in an NxN chess board with N queens.
 * 
 * The queens are allowed to move along the rows of their columns (i.e.,
 * vertically), but not horizontally nor diagonally.
 * 
 * We are interested in finding a state of the board where none of the queens
 * collide with each other diagonally or horizontally. We don't care about
 * vertical collisions since the queens are unique to their columns.
 * 
 * @author Fausto German
 * @version Feb 10th, 2021
 */
public class Board {
    public final int size;
    GraphicBoard graphicBoard;

    // The board is represented with a 1-dimensional array, where
    // the indices represent a queen's column, while the items
    // represent a queen's row.
    private int[] board;

    /**
     * Initializes the board.
     * 
     * @param size The size of the board.
     */
    public Board(int size) {
        this.size = size;
        this.board = new int[size];
        this.reset();
        this.graphicBoard = new GraphicBoard(this);
    }

    /**
     * Resets the board.
     * 
     * @return The current state of the board.
     */
    public Board reset() {
        for (int col = 0; col < size; col++) {
            // index = column
            // item = row
            int row = new Random().nextInt(size);
            board[col] = row;
        }

        return this;
    }

    /**
     * Returns the current position of a queen.
     * 
     * @param queen The queen's whose position will be computed.
     * @return The queen's position.
     */
    public int getCurrentQueenPos(int queen) {
        return board[queen];
    }

    /**
     * Moves a queen to a row position along its column.
     * 
     * @param queen The queen to be moved.
     * @param row   The row where the queen will be moved.
     * @return The current state of the board.
     */
    public Board moveQueenTo(int queen, int row) {
        if (row >= size) {
            System.out.println("Tried moving queen #" + queen + " to row #" + row + ".");
            throw new Error("A queen can only be between rows 0 and " + (size - 1) + ".");
        }
        board[queen] = row;
        return this;
    }

    /**
     * Gets the heuristics of a move without changing the state of the board.
     * 
     * @param queen The queen that would be moved.
     * @param row   The row where the queen would be moved.
     * @return The heuristics of the board if the move is performed.
     */
    public int getMoveHeuristics(int queen, int row) {
        int originalRow = board[queen];

        int heuristics = this.moveQueenTo(queen, row).getTotalCollisions();
        this.moveQueenTo(queen, originalRow);

        return heuristics;
    }

    /**
     * Gets the heuristics for the neighboring states of the board.
     * 
     * @return The heuristics for the neighboring states of the board.
     */
    public int[] getNeighboringHeuristics() {
        int[] states = new int[(size * size) - size];

        for (int queen = 0; queen < size; queen++) {
            int qRow = board[queen];

            for (int nRow = 0; nRow < size; nRow++) {
                int base = queen * (size - 1);
                if (nRow < qRow) states[base + nRow] = getMoveHeuristics(queen, nRow);
                if (nRow > qRow) states[base + (nRow - 1)] = getMoveHeuristics(queen, nRow);
            }
        }

        return states;
    }

    /**
     * Computes the total number of collisions in the board.
     * 
     * @return The total number of collision in the board.
     */
    public int getTotalCollisions() {
        int collisions = 0;
        for (int q = 0; q < size; q++) {
            collisions += getQueenCollisions(q);
        }

        // We divide by 2 because if A collides with B, then B also collides with A.
        // We only take into account one of the collisions between A and B.
        return collisions / 2;
    }

    /**
     * Gets the collisions count for the current queen.
     * 
     * @param queen The queen whose collisions will be computed.
     * @return The number of collisions for the current queen.
     */
    public int getQueenCollisions(int queen) {
        int collisions = 0;
        int CurrQueenRow = board[queen];

        for (int j = 0; j < size; j++) {
            if (queen != j) {
                int jthQueenRow = board[j];

                // Horizontal collision
                if (jthQueenRow == CurrQueenRow) {
                    collisions++;
                    continue;
                }

                // Collision along the negative diagonal, that is, the diagonal line that starts
                // at the top left and ends at the bottom right
                boolean collides1stDiagonal = jthQueenRow == CurrQueenRow + (j - queen);
                // Collision along the positive diagonal, that is, the diagonal line that starts
                // at the bottom left and ends at the top right
                boolean collides2ndDiagonal = jthQueenRow == CurrQueenRow - (j - queen);
                // Checks for diagonal collisions
                if (collides1stDiagonal || collides2ndDiagonal)
                    collisions++;
            }
        }

        return collisions;
    }

    /**
     * Prints the current state of the board.
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int item = board[j];

                if (i == item) {
                    System.out.print("\u001b[33;1m1\u001b[0m");
                } else {
                    System.out.print(0);
                }

                if (j == size - 1) {
                    System.out.print("\n");
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

}
