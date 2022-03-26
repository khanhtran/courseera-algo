import java.util.Arrays;

public class FastCollinearPoints {
    private final Point[] points;
    private int segmentCount;
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point p: points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].equals(points[j])) {
                    throw new IllegalArgumentException();
                }
            }
        }
        this.points = points;
    }

    public int numberOfSegments() {
        return segmentCount;
    }

    public LineSegment[] segments() {
        java.util.List<LineSegment> lines = new java.util.ArrayList<>();
        
        for (Point p: points) {
            final Point[] tmp = copyAndSort(p);
            int startIndex = 0;
            Point startPoint = tmp[0];
            double currentSlope = p.slopeTo(startPoint);
            int i = startIndex;
            while (i < tmp.length) {
                if (p.slopeTo(tmp[i]) > currentSlope) {
                    if ((i - startIndex) >= 4) {
                        lines.add(new LineSegment(startPoint, tmp[i - 1]));
                    }
                    currentSlope = p.slopeTo(tmp[i]);
                    startIndex = i;
                }
                i++;
            }
            if ((i - startIndex) >= 4) {
                lines.add(new LineSegment(startPoint, tmp[i - 1]));
            }
        }

        return lines.toArray(new LineSegment[0]);
    }

    private Point[] copyAndSort(Point origin) {
        final Point[] result = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = points[i];
        }
        Arrays.sort(result, origin.slopeOrder());
        return result;
    }
 }