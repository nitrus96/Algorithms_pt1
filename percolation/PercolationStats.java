import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] experiments;
    private double mean;
    private double std;
    private double coef = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        experiments = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation trial = new Percolation(n);
            while (!trial.percolates()) {
                int p = StdRandom.uniform(1, n + 1);
                int q = StdRandom.uniform(1, n + 1);
                trial.open(p, q);
            }
            double threshold = trial.numberOfOpenSites() / (double) (n * n);
            experiments[i] = threshold;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(experiments);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        std = StdStats.stddev(experiments);
        return std;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - ((coef * std) / Math.sqrt(experiments.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + ((coef * std) / Math.sqrt(experiments.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        System.out.println("mean = " + stats.mean());
        System.out.println("std = " + stats.stddev());
        double[] confidence = { stats.confidenceLo(), stats.confidenceHi() };
        System.out.println(
                "95% confidence interval = " + "[" + confidence[0] + ", " + confidence[1] + "]");
    }
}
