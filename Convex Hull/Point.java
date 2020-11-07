/**
 * A point in 2-dimensional space.
 * 
 * <pre>
 *Point p = new Point(1, 2);
 *p.x // 1
 *p.y // 2
 * </pre>
 */
public class Point {
    /** The x-coordinates of the 2d point */
    public int x;
    /** The y-coordinates of the 2d point */
    public int y;

    /**
     * Stores a point in 2-dimensional space
     * @param x The x-coordinate of the point
     * @param y The y-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * String representation of this object
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
