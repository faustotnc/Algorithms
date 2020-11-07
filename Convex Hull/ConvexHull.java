
public class ConvexHull {
    public static void main(String[] args) {

        Point[] points = {
            new Point(300, 400),
            new Point(600, 500),
            new Point(700, 600),
            new Point(900, 400),
            new Point(700, 200),
            new Point(400, 200)
        };

        HullStack wrappingPoints = GrahamScan.hull(points);
        System.out.println(wrappingPoints);
    }
}