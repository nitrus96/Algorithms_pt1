/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    /**
     * finds all line segments containing 4 points
     */
    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment> tmpLineSegments = new ArrayList<>();

        if (points == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        int n = points.length;

        for (Point point : points) {
            if (point == null)
                throw new IllegalArgumentException("Array cannot contain a null point");
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Array cannot contain duplicate points");
            }
        }

        Arrays.sort(points);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int m = k + 1; m < n; m++) {
                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[m];

                        double s1 = p1.slopeTo(p2);
                        double s2 = p1.slopeTo(p3);
                        double s3 = p1.slopeTo(p4);

                        if (s1 != s2) continue;
                        if (s2 == s3) {
                            tmpLineSegments.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
        lineSegments = tmpLineSegments.toArray(new LineSegment[0]);
    }

    /**
     * the number of line segments
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    /**
     * the line segments
     */
    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
