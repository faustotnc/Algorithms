
public class Main {
    public static void main(String[] args) {
        // ************************************************
        // ********* Read the README.md file for more info
        // ************************************************

        Board board = new Board(70, 70, 40);
        AStar pathFinder = new AStar(board);

        board.showBoard(pathFinder);
    }
}