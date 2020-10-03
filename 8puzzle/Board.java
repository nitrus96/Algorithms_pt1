import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Objects;

public class Board {

    private final int n;
    private final int[][] tiles;

    private int hammingVal;
    private int manhattanVal;

    private int zeroRow;
    private int zeroCol;

    // private int hamming;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];

        // set priorities
        for (int i = 0; i < n * n; i++) {
            int row = i / n;
            int col = i % n;
            int tile = tiles[row][col];

            if (tile > 0) {
                // hamming error
                if (tile != i + 1) hammingVal++;
                // manhattan row error
                manhattanVal += Math.abs((tile - 1) / n - row);
                // manhattan column error
                manhattanVal += Math.abs((tile - 1) % n - col);
            }
            else {
                zeroRow = row;
                zeroCol = col;
            }
            this.tiles[row][col] = tile;
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return hammingVal;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattanVal;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return (hammingVal == 0 && manhattanVal == 0);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return false;
        if (getClass() != y.getClass()) return false;

        Board that = (Board) y;
        return Objects.deepEquals(this.tiles, that.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighborsList = new ArrayList<>();
        if (zeroCol > 0) {
            // swap with tile at the top
            neighborsList.add(getNeighbour(zeroRow, zeroCol - 1));
        }
        if (zeroCol < n - 1) {
            // swap with tile at the bottom
            neighborsList.add(getNeighbour(zeroRow, zeroCol + 1));
        }
        if (zeroRow > 0) {
            // swap with tile on the left
            neighborsList.add(getNeighbour(zeroRow - 1, zeroCol));
        }
        if (zeroRow < n - 1) {
            // swap with tile on the right
            neighborsList.add(getNeighbour(zeroRow + 1, zeroCol));
        }
        return neighborsList;
    }

    private Board getNeighbour(int row, int col) {
        int[][] neighbour = tilesCopy();
        int targetTile = tiles[row][col];
        neighbour[zeroRow][zeroCol] = targetTile;
        neighbour[row][col] = 0;
        return new Board(neighbour);
    }

    private int[][] tilesCopy() {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = tilesCopy();
        int[] firstLoc = new int[2];
        int first = 0;
        int[] secondLoc = new int[2];
        int second = 0;

        while (first == 0) {
            firstLoc[0] = StdRandom.uniform(n);
            firstLoc[1] = StdRandom.uniform(n);
            first = tiles[firstLoc[0]][firstLoc[1]];
        }
        while (second == 0) {
            secondLoc[0] = StdRandom.uniform(n);
            secondLoc[1] = StdRandom.uniform(n);
            second = tiles[secondLoc[0]][secondLoc[1]];
        }

        twinTiles[firstLoc[0]][firstLoc[1]] = second;
        twinTiles[secondLoc[0]][secondLoc[1]] = first;
        return new Board(twinTiles);
    }


    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        System.out.println(initial.toString());
        System.out.println("Hamming: " + initial.hamming());
        System.out.println("Manhattan: " + initial.manhattan() + "\n");

        System.out.println("Neighbor boards");
        for (Board i : initial.neighbors()) {
            System.out.println(i);
        }

        System.out.println("Twin board");
        System.out.println(initial.twin());
    }
}
