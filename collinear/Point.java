import java.util.Comparator;
// https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
public class Point {
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        System.out.println("draw");
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
    public int compareTo(Point that) {
        if (Integer.compare(x, that.x) == 0) {
            return Integer.compare(y, that.y);
        }
        return Integer.compare(x, that.x);
    }
    
    // the slope between this point and that point
    public double slopeTo(Point that) {
        return (that.y - y)/(that.x - x);
    }

    public Comparator<Point> slopeOrder() {
        return (p1, p2) -> Double.compare(slopeTo(p1), slopeTo(p2));
    }
}