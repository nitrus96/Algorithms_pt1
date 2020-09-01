import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF grid;
    private int width;
    private boolean[] openSites;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        grid = new WeightedQuickUnionUF(n * n + 2);
        openSites = new boolean[n * n + 2];
        width = n;
    }

    private int xyTo1D(int row, int column) {
        return width * (row - 1) + column;
    }

    private boolean isValid(int row, int col) {
        return !((row < 1) || (row > width) || (col < 1) || (col > width));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return;
        }
        int idx = xyTo1D(row, col);
        openSites[idx] = true;

        if (row != 1 && isOpen(row - 1, col)) {
            grid.union(idx, xyTo1D(row - 1, col));
        }
        if (row != width && isOpen(row + 1, col)) {
            grid.union(idx, xyTo1D(row + 1, col));
        }
        if (col != 1 && isOpen(row, col - 1)) {
            grid.union(idx, xyTo1D(row, col - 1));
        }
        if (col != width && isOpen(row, col + 1)) {
            grid.union(idx, xyTo1D(row, col + 1));
        }
        if (row == 1) {
            grid.union(0, idx);
        }
        if (row == width) {
            grid.union(openSites.length - 1, idx);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
        int idx = xyTo1D(row, col);
        return openSites[idx];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return grid.find(0) == grid.find(xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (boolean i : openSites) {
            if (i) count++;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(0) == grid.find(openSites.length - 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation ohm = new Percolation(5);
        ohm.open(1, 3);
        ohm.open(2, 3);
        ohm.open(3, 3);
        ohm.open(3, 4);
        ohm.open(4, 4);
        ohm.open(5, 3);
        System.out.println(ohm.percolates());
    }
}


