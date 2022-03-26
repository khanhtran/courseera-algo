
public class BruteCollinearPoints {
    private final Point points[];
    private int segmentCount;
    public BruteCollinearPoints(Point[] points) {
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

        //LineSegment[] result = new LineSegment[points.length];
        java.util.List<LineSegment> lst = new java.util.ArrayList<>();        
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        System.out.printf("\n*%d:%d:%d:%d*\n", i, j, k, l);
                        Point p = points[i];

                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        System.out.println(p);
                        System.out.println("\t" + q);
                        System.out.println("\t" + r);
                        System.out.println("\t" + s);
                        if (p.slopeTo(q) == p.slopeTo(r) &&
                            p.slopeTo(q) == p.slopeTo(s)) {
                                //result[count++] = new LineSegment(p, s);
                                lst.add(new LineSegment(p, s));
                                System.out.printf("\tadd(%d, %d) %s->%s\n", i, l, p, s);
                        }
                    }
                }
            }
        }
        segmentCount = lst.size();
        return lst.toArray(new LineSegment[0]);
    }
 }