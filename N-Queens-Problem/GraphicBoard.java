import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * Shows the board as a graphic window.
 */
public class GraphicBoard extends JFrame {
    private Board board;
    private int tileSize = 48;
    private int toolbarSize = 24;
    private int padding = tileSize / 2;
    /** SERIAL VERSION ID */
    private static final long serialVersionUID = -6824163824874055513L;

    public GraphicBoard(Board b) {
        this.board = b;

        // Three standard sizes for the board
        if (b.size >= 16)
            tileSize = (int) ((16 * tileSize) / b.size);
        if (b.size > 13 && b.size < 16)
            tileSize = (int) ((13 * tileSize) / b.size);
        if (b.size < 8)
            tileSize = (int) ((7 * tileSize) / b.size);

        setTitle("N-Queens Problem — " + b.size + "x" + b.size + " board");
        setSize(b.size * tileSize + (padding * 2), b.size * tileSize + toolbarSize + (padding * 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawBoard((Graphics2D) g);
    }

    /**
     * Draws the board in a window as a grid of NxN tiles, with the queens at their
     * respective position for the current state of the board.
     * 
     * @param g An instance of the graphic engine.
     */
    private void drawBoard(Graphics2D g) {
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                int item = board.getCurrentQueenPos(j);

                // Determines the color of the tile, in a chess board pattern
                if (!((i % 2 == 0) ^ (j % 2 == 0))) { // !(a XOR b)
                    g.setColor(Color.decode("#DFD4C5"));
                } else {
                    g.setColor(Color.decode("#cb6309"));
                }

                // Draws the tiles
                int x = (j * tileSize) + padding;
                int y = (i * tileSize) + toolbarSize + padding;
                g.fillRect(x, y, tileSize, tileSize);

                // Draws the queens
                if (i == item) {
                    g.setColor(Color.decode("#623505"));
                    int size = (int) (tileSize / 1.5);
                    g.fillOval(x + (int) (size / 4), y + (int) (size / 4), size, size);
                }
            }
        }
    }

    /**
     * Shows the board window.
     */
    public void showBoard() {
        setVisible(true);
    }
}
