/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.In;

public class Board {

    private final int n;
    private final int[][] tiles;

    private int hammingVal;
    private int manhattanVal;

    // private int hamming;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = tiles.clone();

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
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return true;
    }

    // all neighboring boards
    // public Iterable<Board> neighbors()

    // a board that is obtained by exchanging any pair of tiles
    // public Board twin()

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
        System.out.println("Manhattan: " + initial.manhattan());


    }

}
