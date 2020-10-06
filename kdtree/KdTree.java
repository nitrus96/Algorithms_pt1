import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {
    // construct an empty set of points
    private Node root;
    private int treeSize;
    private ArrayList<Point2D> insidePoints;

    public KdTree() {
        treeSize = 0;
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb = null;
        private Node rt = null;
        private boolean isHorizontal;

        private Node(Point2D p, Boolean orientation) {
            this.p = p;
            isHorizontal = orientation;
        }

        private void draw() {

            if (lb != null) {
                lb.draw();
            }
            if (rt != null) {
                rt.draw();
            }
            StdDraw.setPenRadius(0.02);
            p.draw();
            StdDraw.setPenRadius(0.01);
            rect.draw();
        }
    }

    // is the set empty?
    public boolean isEmpty() {
        return treeSize == 0;
    }

    // number of points in the set
    public int size() {
        return treeSize;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Can't insert() with a null key");
        if (!contains(p)) {
            treeSize++;
            root = insert(root, null, p, true);
        }
    }

    private Node insert(Node newNode, Node oldNode, Point2D p, boolean horizontal) {
        if (newNode == null) {
            Node x = new Node(p, horizontal);
            nodeRectangle(x, oldNode);
            return x;
        }
        if (newNode.isHorizontal) {
            if (p.x() < newNode.p.x()) {
                newNode.lb = insert(newNode.lb, newNode, p, !horizontal);
            }
            else {
                newNode.rt = insert(newNode.rt, newNode, p, !horizontal);
            }
        }
        else {
            if (p.y() < newNode.p.y()) {
                newNode.lb = insert(newNode.lb, newNode, p, !horizontal);
            }
            else {
                newNode.rt = insert(newNode.rt, newNode, p, !horizontal);

            }
        }
        return newNode;
    }

    private void nodeRectangle(Node newNode, Node oldNode) {
        if (oldNode == null) {
            newNode.rect = new RectHV(0, 0, 1, 1);
            return;
        }
        if (!newNode.isHorizontal) {
            if (newNode.p.x() < oldNode.p.x()) {
                newNode.rect = new RectHV(oldNode.rect.xmin(), oldNode.rect.ymin(),
                                          oldNode.p.x(), oldNode.rect.ymax());
            }
            else {
                newNode.rect = new RectHV(oldNode.p.x(), oldNode.rect.ymin(),
                                          oldNode.rect.xmax(), oldNode.rect.ymax());
            }
        }
        else {
            if (newNode.p.y() < oldNode.p.y()) {
                newNode.rect = new RectHV(oldNode.rect.xmin(), oldNode.rect.ymin(),
                                          oldNode.rect.xmax(), oldNode.p.y());
            }
            else {
                newNode.rect = new RectHV(oldNode.rect.xmin(), oldNode.p.y(),
                                          oldNode.rect.xmax(), oldNode.rect.ymax());
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("Can't be null argument");
        insidePoints = new ArrayList<>();
        range(root, rect);
        return insidePoints;
    }

    private void range(Node x, RectHV rect) {
        if (x == null) return;
        if (x.rect == null) return;
        if (rect.contains(x.p)) insidePoints.add(x.p);
        if (x.lb != null && rect.intersects(x.lb.rect))
            range(x.lb, rect);
        if (x.rt != null && rect.intersects(x.rt.rect))
            range(x.rt, rect);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Can't be a null point");
        return get(root, p) != null;
    }

    private Point2D get(Node newNode, Point2D p) {
        if (newNode == null) return null;
        if (p.equals(newNode.p)) return p;
        if ((newNode.isHorizontal && p.x() < newNode.p.x()) ||
                (!newNode.isHorizontal && p.y() < newNode.p.y())) {
            return get(newNode.lb, p);
        }
        else {
            return get(newNode.rt, p);
        }
    }

    // draw all points to standard draw
    public void draw() {
        root.draw();
    }


    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Can't be null argument");
        if (root == null) return null;
        Node nearestN = nearest(root, p, root);
        return nearestN.p;
    }

    private Node nearest(Node x, Point2D p, Node nearest) {
        if (x == null)
            return nearest;
        if (x.p.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p))
            nearest = x;

        Node left = nearest(x.lb, p, nearest);
        Node right = nearest(x.rt, p, nearest);

        if (left.p.distanceSquaredTo(p) < right.p.distanceSquaredTo(p)) {
            if (left.p.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p))
                nearest = left;
        }
        else {
            if (right.p.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p))
                nearest = right;
        }
        return nearest;
    }
    
    // unit testing of the methods (optional)
    public static void main(String[] args) {
        // initialize the two data structures with point from file
        String filename = args[0];
        In in = new In(filename);
        KdTree tree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            tree.insert(p);
        }
    }
}
