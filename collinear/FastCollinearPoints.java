import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    /**
     * finds all line segments containing 4 or more points
     */
    public FastCollinearPoints(Point[] points) {
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

        // sort points by natural order
        Arrays.sort(points);
        Point[] pointsCopy = points.clone();

        for (Point p : points) {
            // sort points wrt to p
            Arrays.sort(pointsCopy, p.slopeOrder());

            // store points that form a line
            ArrayList<Point> slopeList = new ArrayList<>();
            double currentSlope;
            double previousSlope = Double.NEGATIVE_INFINITY;

            // i = 0 would compare a point with itself, so indexing starts from 1
            for (int i = 1; i < n; i++) {
                currentSlope = p.slopeTo(pointsCopy[i]);
                slopeList.add(pointsCopy[i - 1]);

                if (currentSlope != previousSlope) {
                    addLine(slopeList, p, tmpLineSegments);
                    slopeList.clear();
                }
                previousSlope = currentSlope;
            }
            slopeList.add(pointsCopy[n - 1]);
            addLine(slopeList, p, tmpLineSegments);
        }
        // convert arraylist to array
        lineSegments = tmpLineSegments.toArray(new LineSegment[0]);
    }


    /**
     * constructor helper function
     * decides whether a given line should be added to the line segment array
     */
    private void addLine(ArrayList<Point> slopeList, Point p,
                         ArrayList<LineSegment> tmpLineSegments) {
        if (slopeList.size() >= 3) {
            Collections.sort(slopeList);
            Point minPoint = slopeList.get(0);
            if (p.compareTo(minPoint) < 0) {
                Point maxPoint = slopeList.get(slopeList.size() - 1);
                tmpLineSegments.add(new LineSegment(p, maxPoint));
            }
        }
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

    /**
     * test client
     */
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
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
