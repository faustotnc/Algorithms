import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.Random;

/**
 * Shows the board as a graphic window.
 */
public class Board extends JFrame {
    /** SERIAL VERSION ID */
    private static final long serialVersionUID = -6824163824874055513L;
    private Random rand = new Random();
    private boolean hasStarted = false;
    public int colCount;
    public int rowCount;
    public int percentBlocked;

    // Display settings
    private int tileSize = 40;
    private int toolbarSize = 24;
    private int padding = tileSize / 2;

    // Tiles Container
    JPanel container = new JPanel();

    // Nodes
    public Node startNode = null;
    public Node goalNode = null;
    public final Node[][] nodes;

    public Board(int colCount, int rowCount, int percentBlocked) {
        this.colCount = colCount;
        this.rowCount = rowCount;
        this.percentBlocked = percentBlocked;

        this.nodes = new Node[rowCount][colCount];

        // Standard size for the board
        if (rowCount >= 18 || colCount >= 18) {
            tileSize = (int) ((18 * tileSize) / Math.max(rowCount, colCount));
        }

        setTitle("A* Algorithm");
        setSize(colCount * tileSize + (padding * 2), rowCount * tileSize + toolbarSize + (padding * 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container.setLayout(new GroupLayout(container));
        this.add(container);
    }

    /**
     * Shows the board window.
     */
    public void showBoard(AStar pathFinder) {
        drawBoard(pathFinder);
        setVisible(true);
    }

    /**
     * Draws the board in a window as a grid of NxN tiles, with the queens at their
     * respective position for the current state of the board.
     */
    private void drawBoard(AStar pathFinder) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                JPanel tile = new JPanel();

                Node node = new Node(row, col, tile);
                nodes[row][col] = node;

                // Draws the tiles
                int x = (col * tileSize) + padding;
                int y = (row * tileSize) + padding;
                tile.setBounds(x, y, tileSize, tileSize);

                // Adds the border and background to the tile
                Border border = BorderFactory.createLineBorder(Color.decode("#222222"));
                tile.setBorder(border);
                tile.setBackground(Color.decode("#EEEEEE"));

                // Adds a click event to traversable nodes
                this.AddTileMouseEvent(tile, node, pathFinder);

                // Adds the tile to the board
                container.add(tile);
            }
        }

        // Marks a certain percentage of tiles as blocked
        double maxBlocked = (rowCount * colCount) * ((float) this.percentBlocked / 100);
        ArrayList<Integer> chosen = new ArrayList<>();
        while (chosen.size() < maxBlocked) {
            int pos = rand.nextInt(rowCount * colCount);
            if (chosen.contains(pos))
                continue;

            int col = pos % colCount;
            int row = (int) (pos / colCount);
            Node node = nodes[row][col];

            node.tile().setBackground(Color.decode("#222222"));
            node.tile().removeMouseListener(node.tile().getMouseListeners()[0]);
            node.setNotTraversable();

            chosen.add(pos);
        }
    }

    /**
     * Adds a mouse click event to the tiles so that a start and end tile can be
     * chosen. After clicking the end tile, the A* algorithm will start looking for
     * a path.
     * 
     * @param tile       The current tile.
     * @param n          The node associated with the tile.
     * @param pathFinder An instance of the A* algorithm
     */
    private void AddTileMouseEvent(JPanel tile, Node n, AStar pathFinder) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (startNode == null) {
                    tile.setBackground(Color.decode("#1a7ce5"));
                    startNode = n;
                } else if (startNode != null && goalNode == null) {
                    tile.setBackground(Color.decode("#fee22a"));
                    goalNode = n;
                }

                // After the start and goal nodes are selected, we start
                // the A* algorithm to look for the path
                if (startNode != null && goalNode != null && !hasStarted) {
                    pathFinder.findPath();
                    hasStarted = true;
                }

                // If a path has already been found (or there was no solution),
                // the user can click any tile again to clear the board and select
                // a new star and end node.
                if (pathFinder.canSelect && startNode != null && goalNode != null) {
                    startNode = null;
                    goalNode = null;
                    hasStarted = false;
                    pathFinder.foundGoal = false;
                    clearBoard(pathFinder);
                    tile.setBackground(Color.decode("#1a7ce5"));
                    startNode = n;
                }
            }
        };

        tile.addMouseListener(listener);
    }

    /**
     * Clears the board.
     * @param pathFinder An instance to the pathFinder class.
     */
    private void clearBoard(AStar pathFinder) {
        for (Node node : pathFinder.openList)
            node.reset();
        pathFinder.openList.clear();

        for (Node node : pathFinder.closedList)
            node.reset();
        pathFinder.closedList.clear();

        for (Node node : pathFinder.finalPath)
            node.reset();
        pathFinder.finalPath.clear();

        repaint();
    }
}
