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
    // Used to change the colors of the tiles later
    public JPanel[][] tiles;

    // Nodes
    public Node startNode = null;
    public Node endNode = null;
    public final Node[][] nodes;

    public Board(int colCount, int rowCount, int percentBlocked) {
        this.colCount = colCount;
        this.rowCount = rowCount;
        this.percentBlocked = percentBlocked;

        this.nodes = new Node[rowCount][colCount];
        tiles = new JPanel[rowCount][colCount];

        // Three standard sizes for the board
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
     * Draws the board in a window as a grid of NxN tiles, with the queens at their
     * respective position for the current state of the board.
     */
    private void drawBoard(AStar pathFinder) {
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                JPanel tile = new JPanel();

                Node node = new Node(row, col);
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
                tiles[row][col] = tile;
            }
        }

        // Marks a certain percentage of tiles as not blocked
        double maxBlocked = (rowCount * colCount) * ((float) this.percentBlocked / 100);
        ArrayList<Integer> chosen = new ArrayList<>();
        while (chosen.size() < maxBlocked) {
            int pos = rand.nextInt(rowCount * colCount);
            if (chosen.contains(pos))
                continue;

            int col = pos % colCount;
            int row = (int) (pos / colCount);

            tiles[row][col].setBackground(Color.decode("#222222"));
            tiles[row][col].removeMouseListener(tiles[row][col].getMouseListeners()[0]);

            nodes[row][col].setNotTraversable();
            chosen.add(pos);
        }
    }

    /**
     * Adds a mouse click event to the tiles so that a start and end tile can be
     * chosen. After clicking the end tile, the A* algorithm will start looking for
     * a path.
     * 
     * @param tile The current tile.
     * @param n    The node associated with the tile.
     */
    private void AddTileMouseEvent(JPanel tile, Node n, AStar pathFinder) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (startNode == null) {
                    tile.setBackground(Color.decode("#2945a1"));
                    startNode = n;
                    repaint();
                } else if (startNode != null && endNode == null) {
                    tile.setBackground(Color.decode("#1a7ce5"));
                    endNode = n;
                    repaint();
                }

                // After the start and goal nodes are selected, we start
                // the A* algorithm to look for the path
                if (startNode != null && endNode != null && !hasStarted) {
                    pathFinder.findPath();
                    hasStarted = true;
                }
            }
        };

        tile.addMouseListener(listener);
    }

    /**
     * Shows the board window.
     */
    public void showBoard(AStar pathFinder) {
        drawBoard(pathFinder);
        setVisible(true);
    }
}
