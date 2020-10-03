import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solver {

    private final ArrayList<Board> solutions = new ArrayList<>();

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("Can't be null");

        MinPQ<Node> pq = new MinPQ<>(new PriorityCompare());
        MinPQ<Node> pqTwin = new MinPQ<>(new PriorityCompare());
        Node current = new Node(initial, null, 0);
        Node currentTwin = new Node(initial.twin(), null, 0);

        while (!current.board.isGoal() && !currentTwin.board.isGoal()) {
            for (Board neighbor : current.board.neighbors()) {
                if (current.previous == null ||
                        !current.previous.board.equals(neighbor)) {
                    pq.insert(new Node(neighbor, current, current.moves + 1));
                }
            }

            for (Board neighbor : currentTwin.board.neighbors()) {
                if (currentTwin.previous == null ||
                        !currentTwin.previous.board.equals(neighbor)) {
                    pqTwin.insert(new Node(neighbor, currentTwin, currentTwin.moves + 1));
                }
            }

            current = pq.delMin();
            currentTwin = pqTwin.delMin();
        }

        if (current.board.isGoal()) {
            while (current != null) {
                solutions.add(current.board);
                current = current.previous;
            }
            Collections.reverse(solutions);
        }
    }

    // inner class for use with the priority queue
    private class Node {
        private final Board board;
        private final Node previous;
        private final int priority;
        private final int moves;

        private Node(Board board, Node previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
            priority = moves + board.manhattan();
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solutions.size() != 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solutions.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (solutions.isEmpty()) return null;
        return solutions;
    }

    // use moves + manhattan to sort the priority queue
    private static class PriorityCompare implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return Integer.compare(n1.priority, n2.priority);
        }
    }

    // test client
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
