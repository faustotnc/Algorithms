import java.util.Arrays;
import java.util.Comparator;

public class GrahamScan {

    /**
     * Computes the Convex Hull of a set of points using the Graham Scan algorithm
     * @param points The set of points to compute the Convex Hull.
     * @return A {@code HullStack} containing the points that make up
     * the convex hull for the given set of points.
     */
    public static HullStack hull(Point[] points) {
        Point[] mainPoints = points.clone();

        // Sorts the points such that the lowest point is
        // the first item in the array.
        // points.sort((a, b) => a[1] - b[1]);
        Arrays.sort(mainPoints, new Comparator<Point>(){
            public int compare(Point a, Point b) {
                return Integer.compare(a.y, b.y);
            }
        });

        // Sorts the points[1...n] by the angle the
        // points make with the key item points[0]
        Point[] sortedByAngle = Arrays.copyOfRange(mainPoints, 1, mainPoints.length);
        Point key = mainPoints[0];
        Arrays.sort(sortedByAngle, new Comparator<Point>(){
            public int compare(Point a, Point b) {
                double angleA = Math.atan2(a.y - key.y, a.x - key.x);
                double angleB = Math.atan2(b.y - key.y, b.x - key.x);
                return Double.compare(angleA, angleB);
            }
        });
        
        // Reconstructs the original array
        for (int i = 1; i < (sortedByAngle.length + 1); i++) {
            mainPoints[i] = sortedByAngle[i - 1];
        }

        // The stack of points that make the Convex Hull
        HullStack hullStack = new HullStack();

        // The Graham Scan
        for (int i = 0; i < mainPoints.length; i++) {
            Point point = mainPoints[i];

            // Pops points of the stack if they do not make a clockwise turn
            while (isAntiClockwise(point, hullStack)) hullStack.pop();

            // Adds points to the stack if they
            // make a clockwise turn
            hullStack.push(point);
        }

        // Return the stack of points that
        // wraps all the points
        return hullStack;
    }

    /**
     * Computes wether the current point has made an anti-clockwise turn
     * relative to the previous point or not.
     * @param element The current point
     * @param hullStack The stack of points
     * @return True if the current element has made an anti-clockwise turn
     * relative to the previous point. False otherwise.
     */
    private static boolean isAntiClockwise(Point element, HullStack hullStack) {
        return hullStack.size() > 1 && cross(hullStack.getNextToTop(), hullStack.getTop(), element) <= 0;
    }

    /**
     * Computes the direction of z-value of the cross-product between
     * two points a, b, relative to another point c.
     * @param a The first point
     * @param b The second point.
     * @param c The "origin" or point of reference.
     * @return -1, 0, or 1
     */
    private static int cross(Point a, Point b, Point c) {
        int area = ((b.x - a.x) * (c.y - a.y)) - ((b.y - a.y) * (c.x - a.x));
        if (area < 0) return -1; // clockwise
        if (area > 0) return 1;  // counter-clockwise
        return 0;                // colinear
    }
}
