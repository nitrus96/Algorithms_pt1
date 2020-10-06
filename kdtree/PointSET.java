import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class PointSET {
    // construct an empty set of points
    private final SET<Point2D> pointSet;

    public PointSET() {
        pointSet = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    // number of points in the set
    public int size() {
        return pointSet.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        pointSet.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return pointSet.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : pointSet) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> inner = new ArrayList<>();
        for (Point2D p : pointSet) {
            double pX = p.x();
            double pY = p.y();
            if (pX <= rect.xmax() && pX >= rect.xmin() &&
                    pY <= rect.ymax() && pY >= rect.ymin()) {
                inner.add(p);
            }
        }
        return inner;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        double minDistance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;
        for (Point2D point : pointSet) {
            double pointDistance = p.distanceSquaredTo(point);
            if (pointDistance < minDistance) {
                minDistance = pointDistance;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }

        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        brute.draw();
        StdDraw.show();

        RectHV rect = new RectHV(0.1, 0.1, 0.6, 0.8);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        rect.draw();

        System.out.println("Points inside rectangle: " + brute.range(rect));
    }
}
