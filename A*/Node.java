

// TODO: Add Comments
public class Node implements Comparable<Node> {
	private int row, col, f, g, h;
	private boolean type = false;
	private Node parent;

	public Node(int r, int c) {
		row = r;
		col = c;
		parent = null;
	}

	private void setF() {
		f = g + h;
	}

	public void setG(int value) {
		g = value;
		this.setF();
	}

	public void setH(int value) {
		h = value;
		this.setF();
	}

	public void setParent(Node n) {
		parent = n;
	}

	// accessor methods to get values
	public int getF() {
		return f;
	}

	public int getG() {
		return g;
	}

	public int getH() {
		return h;
	}

	public Node getParent() {
		return parent;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean equals(Object in) {
		// typecast to Node
		Node n = (Node) in;

		return row == n.getRow() && col == n.getCol();
	}


	public boolean isNotTraversable() {
		return type;
	}

	public void setNotTraversable() {
		this.type = true;
	}

	public String toString() {
		return "Node [" + row + ", " + col + "](" + f + ") – G: " + g + " H: " + h;
	}

	@Override
	public int compareTo(Node o) {
		return this.getF() - o.getF();
	}


	public Node clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }

}
