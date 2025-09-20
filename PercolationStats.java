import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private int trials;

    private double[] arrayTrials;

    private Stopwatch stopwatch;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        // array of doubles to track trials
        arrayTrials = new double[trials];
        // starts tracking time
        stopwatch = new Stopwatch();
        this.trials = trials;
        // for amount of trials
        for (int i = 0; i < trials; i++) {
            Percolation percTest = new Percolation(n);
            // while the percolation object does not percolate
            while (!percTest.percolates()) {
                // selects random valid row and col
                int randomRow = StdRandom.uniformInt(0, n);
                int randomCol = StdRandom.uniformInt(0, n);
                // opens that random coordinate, if already open nothing happens
                percTest.open(randomRow, randomCol);
            }
            int numberOpenSites = percTest.numberOfOpenSites();
            // stores P threshold
            arrayTrials[i] = (double) numberOpenSites / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(arrayTrials);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(arrayTrials);
    }

    // low endpoint of 95% confidence interval
    // uses equation from directions
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(trials);
    }

    // returns the time of a set of trials
    private double time() {
        // returns the time from stopwatch object in constructor
        return stopwatch.elapsedTime();
    }

    // test client (see below)
    public static void main(String[] args) {
    }

}
