import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;

public class AStar {
    private Board board;
    public boolean foundGoal = false;
    public boolean canSelect = true;

    // The open list represents nodes that have been discovered,
    // but are yet to be visited. We use a priority queue (a min-heap)
    // because that will guarantee the node at the top of the queue
    // has the lowest f-value, and therefore is the best move.
    public PriorityQueue<Node> openList = new PriorityQueue<>();
    // The closed list represents the nodes that we have visited.
    public ArrayList<Node> closedList = new ArrayList<>();
    // The final path generated once the search is done
    public ArrayList<Node> finalPath = new ArrayList<>();

    /**
     * A new instance of the A* path-finding algorithm.
     * 
     * @param board An N*N board used for path-finding.
     */
    public AStar(Board board) {
        this.board = board;
    }

    /**
     * Starts the A* path-finding algorithm.
     */
    public void findPath() {
        System.out.println("LOOKING FOR PATH...");
        this.canSelect = false;

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
                    if (current.equals(board.goalNode)) {
                        System.out.println("FOUND A SOLUTION!!!\n\nSolution Path:");
                        foundGoal = true;

                        // Shows the path on the board
                        drawFinalPath(current);

                        // Prints the path to the console
                        for (int i = finalPath.size() - 1; i >= 0; i--) {
                            Node node = finalPath.get(i);
                            System.out.println((finalPath.size() - i) + ") " + node);
                        }

                        // Do one last repaint and cancel the TimerTask
                        board.repaint();
                        this.cancel();
                        canSelect = true;
                    }

                    closedList.add(current);
                    addNeighborsToOpenList(current);
                    // Color nodes that have already been visited
                    if (current != board.startNode && current != board.goalNode) {
                        current.tile().setBackground(Color.decode("#c04e10"));
                    }
                }

                // If it has not found a goal, but the open list is now empty, then
                // cancel the timer and display a message to the user.
                if (!foundGoal && openList.isEmpty()) {
                    System.out.println("A SOLUTION COULD NOT BE FOUND!!");

                    // Do one last repaint and cancel the TimerTask
                    board.repaint();
                    this.cancel();
                    canSelect = true;
                }
            }
        }, 0, 100);
    }

    /**
     * Adds the neighbors of the current node to the open list. This function also
     * calculates the G, H, and F values for neighboring nodes.
     * 
     * @param current The current node.
     */
    private void addNeighborsToOpenList(Node current) {
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
                    if (!neighbor.isTraversable() || closedList.contains(neighbor))
                        continue;

                    // If the neighbor is not in the open list already,
                    // compute it's values and add it to the list.
                    if (!openList.contains(neighbor)) {
                        neighbor.setParent(current);
                        this.computeValues(neighbor);

                        openList.add(neighbor);

                        // Set color for nodes to be visited.
                        neighbor.tile().setBackground(Color.decode("#e6a475"));
                    } else {
                        Node prevParent = neighbor.getParent();
                        int prevG = neighbor.getG();

                        // Re-compute the values of the neighbor node
                        // relative to the current node
                        neighbor.setParent(current);
                        int newG = this.getNodeGVal(neighbor);

                        // If moving through this node with the current node as its
                        // parent does not produce a better path, discard the changes
                        // and revert back to the previous state.
                        if (newG >= prevG) {
                            neighbor.setG(prevG);
                            neighbor.setParent(prevParent);
                        } else {
                            // Otherwise, if the new path produces a better g-value,
                            // the we update the neighbor node while keeping the current
                            // node as its parent.
                            neighbor.setG(newG);

                            // Reposition the node in the MinHeap according
                            // to the new G value
                            openList.remove(neighbor);
                            openList.add(neighbor);
                        }
                    }
                }
            }
        }
    }

    /**
     * Computes the G, H and F values of the neighbors.
     * 
     * @param neighbor The node whose values are being computed.
     */
    private void computeValues(Node neighbor) {
        // Compute the heuristics
        int h = Math.abs(neighbor.getCol() - board.goalNode.getCol());
        h += Math.abs(neighbor.getRow() - board.goalNode.getRow());
        h *= 10;
        neighbor.setH(h);

        // Computes the G-Value
        neighbor.setG((neighbor.getParent() != null) ? this.getNodeGVal(neighbor) : 0);
    }

    /**
     * Calculates the distance between the provided node and the starting node.
     * 
     * @param node The node whose distance will be computed.
     * @return The distance between the provided node and the starting node.
     */
    private int getNodeGVal(Node node) {
        double xDist = Math.pow(node.getRow() - node.getParent().getRow(), 2);
        double yDist = Math.pow(node.getCol() - node.getParent().getCol(), 2);
        double dist = 10 * Math.sqrt(xDist + yDist);
        return (int) (node.getParent().getG() + dist);
    }

    /**
     * Recursively colors the tiles of the path from the goal node to the start
     * node.
     * 
     * @param board The board.
     * @param node  The current node in the recursive step.
     */
    private void drawFinalPath(Node node) {
        finalPath.add(node);

        // If there are no more parents to travel through, stop the recursive calls.
        if (node.getParent() == null)
            return;

        Color color = Color.decode((node == board.goalNode) ? "#fee22a" : "#6db26d");
        node.tile().setBackground(color);
        drawFinalPath(node.getParent());
    }
}
