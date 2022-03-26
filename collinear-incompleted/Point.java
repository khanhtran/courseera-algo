import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

// https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
public class Point implements Comparable<Point> {
    final int x;
    final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        System.out.println("drawTo " + that);
    }
    // string representation
    public String toString() {
        return String.format("p(%s, %s)", x, y);
    }
 
    // compare two points by y-coordinates, breaking ties by x-coordinates
    @Override
    public int compareTo(Point that) {
        if (Integer.compare(x, that.x) == 0) {
            return Integer.compare(y, that.y);
        }
        return Integer.compare(x, that.x);
    }
    
    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (that.x == x) return Double.NEGATIVE_INFINITY;
        return (that.y - y)*1.0/(that.x - x);
    }

    public Comparator<Point> slopeOrder() {
        return (p1, p2) -> Double.compare(slopeTo(p1), slopeTo(p2));
    }

    public static void main(String[] args) {
//        Point p0 = new Point(0, 0);
//        Point p1 = new Point(1, 1);
//        Point p2 = new Point(2, 2);
//
//        assert p0.compareTo(p1) < 0;
//        assert p1.compareTo(p2) < 0;
//
//        assert p0.slopeTo(p1) == p0.slopeTo(p2);

        Point p = new Point(19000, 10000);

        Point q = new Point(18000, 10000);
        Point r = new Point(32000, 10000);
        Point s = new Point(1234, 5678);

//        System.out.println(p.slopeTo(r));
        System.out.println(p.slopeTo(s));

        assert p.slopeTo(r) != p.slopeTo(s);
    }
}