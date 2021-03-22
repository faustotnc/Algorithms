import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;

public class AStar {
    private Board board;

    private PriorityQueue<Node> openList = new PriorityQueue<>();
    private ArrayList<Node> closedList = new ArrayList<>();

    private boolean foundGoal = false;

    public AStar(Board board) { this.board = board; }

    /**
     * Do
     * 
     * @param board
     */
    public void findPath() {
        System.out.println("Looking for path...");

        // Adds the start node to the open list
        this.computeValues(board.startNode);
        openList.add(board.startNode);

        // Instead of using a while-loop for the iterations of the
        // algorithm, I used a scheduler to show the steps the agent takes.
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Loop until a solution is found or there are no more
                // items to look for in the open list.
                if (!openList.isEmpty() && !foundGoal) {
                    Node current = openList.poll();

                    // If the node returned by addNeighborsToOpenList()
                    // is not null, then we have found the goal
                    if (current.equals(board.endNode)) {
                        System.out.println("FOUND A SOLUTION!!!");
                        drawFinalPath(current);
                        foundGoal = true;
                        this.cancel();
                    }

                    closedList.add(current);
                    addNeighborsToOpenList(current);
                }

                // If it has not found a goal, but the open list is now empty, then
                // cancel the timer and display a message to the user.
                if (!foundGoal && openList.isEmpty()) {
                    System.out.println("A SOLUTION COULD NOT BE FOUND!!");
                    this.cancel();
                }
            }
        }, 0, 100);
    }

    /**
     * Adds the neighbors of the current node to the open list. This function also
     * calculates the G, H, and F values for neighboring nodes.
     * 
     * @param current
     * @param board
     */
    private void addNeighborsToOpenList(Node current) {
        // Set the color visited nodes (nodes in the closed list)
        if (current != board.startNode && current != board.endNode) {
            board.tiles[current.getRow()][current.getCol()].setBackground(Color.decode("#c04e10"));
        }

        // Gets the neighbors
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int nRow = current.getRow() + i;
                int nCol = current.getCol() + j;

                // We only add the neighbor to the openList if
                // it is inside the bounds of the board
                if ((nRow >= 0) && (nCol >= 0) && (nRow < board.rowCount) && (nCol < board.colCount)) {
                    Node neighbor = board.nodes[nRow][nCol];

                    // Skip the node if it is not traversable or it is in the closed list.
                    if (neighbor.isNotTraversable() || closedList.contains(neighbor))
                        continue;

                    // If the neighbor is not in the open list already,
                    // compute it's values and add it to the list.
                    if (!openList.contains(neighbor)) {
                        neighbor.setParent(current);
                        this.computeValues(neighbor);

                        openList.add(neighbor);

                        // Set color for nodes to be visited.
                        board.tiles[nRow][nCol].setBackground(Color.decode("#e6a475"));
                        board.repaint();
                    }
                }
            }
        }
    }

    private void computeValues(Node neighbor) {
        // Compute the heuristics
        int h = Math.abs(neighbor.getCol() - board.endNode.getCol());
        h += Math.abs(neighbor.getRow() - board.endNode.getRow());
        h *= 10;
        neighbor.setH(h);

        if (neighbor.getParent() != null) {
            double xDist = Math.pow(neighbor.getRow() - neighbor.getParent().getRow(), 2);
            double yDist = Math.pow(neighbor.getCol() - neighbor.getParent().getCol(), 2);
            double dist = 10 * Math.sqrt(xDist + yDist);
            neighbor.setG((int) (neighbor.getParent().getG() + dist));
        } else {
            neighbor.setG(0);
        }
    }

    /**
     * Recursively colors the tiles of the path from the
     * start node to the goal node.
     * @param board The board.
     * @param node The 
     */
    private void drawFinalPath(Node node) {
        if (node.getParent() == null)
            return;

        Color color = Color.decode((node == board.endNode) ? "#1a7ce5" : "#6db26d");
        board.tiles[node.getRow()][node.getCol()].setBackground(color);
        drawFinalPath(node.getParent());
    }
}
