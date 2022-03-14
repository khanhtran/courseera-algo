import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int mSize;
    private final int mTrials;
    private final double[] mFractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
            throw new IllegalArgumentException();
        mSize = n;
        mTrials = trials;
        mFractions = new double[trials];
        runTrials();
    }

    private void runTrials() {
        for (int i = 0; i < mTrials; i++) {
            final Percolation p = new Percolation(mSize);
            while (!p.percolates()) {
                final int row = StdRandom.uniform(1, mSize + 1);
                final int col = StdRandom.uniform(1, mSize + 1);
                p.open(row, col);
            }

            mFractions[i] = p.numberOfOpenSites() * 1.0 / (mSize * mSize);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(mFractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(mFractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 / Math.sqrt(mTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 / Math.sqrt(mTrials));
    }

    // test client (see below)
    public static void main(String[] args) {
        final PercolationStats pStat = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.printf("%-30s = %.16f\n", "mean", pStat.mean());
        System.out.printf("%-30s = %.16f\n", "stddev", pStat.stddev());
        System.out.printf("%-30s = [%.16f, %.16f]\n", "95% confidence interval", pStat.confidenceLo(), pStat.confidenceHi());
    }

}