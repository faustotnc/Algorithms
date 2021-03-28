import java.awt.Color;

import javax.swing.JPanel;

/**
 * A node (or tile) in the grid environment.
 * 
 * Provided by UNC-Charlotte – Department of Computing and Informatics
 * 
 * @author: Prof. Daniel Jugan
 * @ModifiedBy: Fausto German
 */
public class Node implements Comparable<Node> {
    private int row, col, f, g, h;
    private boolean isTraversable = true;
    private Node parent = null;
    // The tile associated with this node.
    private JPanel _tile;

    /**
     * A node or tile in the grid environment.
     * 
     * @param row The row position for this tile.
     * @param col The column position for this tile.
     */
    public Node(int row, int col, JPanel tile) {
        this.row = row;
        this.col = col;
        this._tile = tile;
    }

    /**
     * Sets the `F` heuristic value. The f-value represents how well this node leads
     * to the end goal.
     */
    private void setF() {
        f = g + h;
    }

    /**
     * Sets the `G` heuristic value. The g-value represents the euclidean distance
     * between this node and it's parent node. Modifying this value automatically
     * updates the f-value.
     * 
     * @param value The new g-value.
     */
    public void setG(int value) {
        g = value;
        this.setF();
    }

    /**
     * Sets this node as not traversable.
     */
    public void setNotTraversable() {
        this.isTraversable = false;
    }

    /**
     * Sets the `H` heuristic value. The h-value represents the manhattan distance
     * between this node and the goal node. Modifying this value automatically
     * updates the f-value.
     * 
     * @param value The new h-value.
     */
    public void setH(int value) {
        h = value;
        this.setF();
    }

    /**
     * Sets the parent node to this node. The parent node is the node used to reach
     * this node, forming a shortest path from the start node to the end node.
     * 
     * @param n The parent node.
     */
    public void setParent(Node n) {
        parent = n;
    }

    /**
     * Gets the f-value for this node.
     * 
     * @return The f-value for this node.
     */
    public int getF() {
        return f;
    }

    /**
     * Gets the g-value for this node.
     * 
     * @return The g-value for this node.
     */
    public int getG() {
        return g;
    }

    /**
     * Gets the h-value for this node.
     * 
     * @return The h-value for this node.
     */
    public int getH() {
        return h;
    }

    /**
     * Gets the parent of this node.
     * 
     * @return The parent of this node.
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Gets the row position of this node.
     * 
     * @return the row position of this node.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column position of this node.
     * 
     * @return the column position of this node.
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the tile associated with this node.
     * 
     * @return The node's tile.
     */
    public JPanel tile() {
        return this._tile;
    }

    /**
     * Checks whether this node is traversable or nor.
     * 
     * @return True if the node is traversable; false otherwise.
     */
    public boolean isTraversable() {
        return isTraversable;
    }

    /**
     * Checks whether this node equals another node.
     * 
     * @param node The node to be compared to this node.
     * @return True if the nodes are equal; false otherwise.
     */
    public boolean equals(Object node) {
        Node n = (Node) node;
        return row == n.getRow() && col == n.getCol();
    }

    /**
     * Compares this node against another node by their f-value. The node with the
     * lower f-value is considered a smaller node.
     */
    @Override
    public int compareTo(Node o) {
        return this.getF() - o.getF();
    }

    /**
     * String version of this node.
     */
    public String toString() {
        return "Node [" + row + ", " + col + "] (F: " + f + ") – G: " + g + " H: " + h;
    }

    /**
     * Resets a node's values, as well as the tile's background color.
     */
    public void reset() {
        this.setG(0);
        this.setH(0);
        this.setParent(null);
        this.tile().setBackground(Color.decode("#eeeeee"));
    }
}
