import java.util.ArrayList;

/**
 * Stores a collection of points that make up the
 * Convex Hull of a set of points.
 */
public class HullStack {
    private ArrayList<Point> stack = new ArrayList<>();

    /**
     * Pushes a new point into the stack
     * @param point The point to add to the stack
     */
    public void push(Point point) {
        this.stack.add(point);
    }

    /**
     * Pops the top element from the stack
     */
    public void pop() {
        this.stack.remove(this.stack.size() - 1);
    }

    /**
     * Gets the top element from the stack
     * without removing it
     * @return The top element (Point) on the stack
     */
    public Point getTop() {
        return this.stack.get(this.stack.size() - 1);
    }

    /**
     * Gets the second-to-top element from the stack
     * without removing it.
     * @return The second-to-top element (Point) on the stack
     */
    public Point getNextToTop() {
        return this.stack.get(this.stack.size() - 2);
    }

    /**
     * gets the number of points in the stack
     * @return The size of the stack
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * String representation of this object
     */
    @Override
    public String toString() {
        return this.stack.toString();
    }
}
