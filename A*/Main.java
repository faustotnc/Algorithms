

public class Main {
    
    public static void main(String[] args) {
        Board board = new Board(75, 75, 40);
        AStar pathFinder = new AStar(board);
        board.showBoard(pathFinder);
    }
}