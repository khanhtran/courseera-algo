import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private final WeightedQuickUnionUF quickUnion;
    private final boolean [][] sites;
    private int numOpenSites;
    private final int dimension;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        validateDimension(n);
        // number of points: n*n
        // adding two virtual points: one at the begining, one at the end
        // virtual point 1: 0
        // virtual point 2: n*n + 1
        dimension = n;
        quickUnion = new WeightedQuickUnionUF(dimension*dimension + 2);
        sites = new boolean[dimension + 1][dimension + 1];

        // connect top virtual point to top row
        for (int i = 1; i <= dimension; i++) {
            quickUnion.union(0, toPoint(1, i));
        }
        // connect bottom virtual point to bottom row

        for (int i = dimension*dimension; i > dimension*dimension - dimension; i--) {
            quickUnion.union(dimension*dimension + 1, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateSite(row, col);
        if (isOpen(row, col)) return;
        sites[row][col] = true;
        numOpenSites++;
        // left
        if (col > 1 && isOpen(row, col - 1)) {
            quickUnion.union(toPoint(row, col), toPoint(row, col - 1));
        }
        // right
        if (col < dimension && isOpen(row, col + 1)) {
            quickUnion.union(toPoint(row, col), toPoint(row, col + 1));
        }
        // up
        if (row > 1 && isOpen(row - 1, col)) {
            quickUnion.union(toPoint(row, col), toPoint(row - 1, col));
        }
        // down
        if (row < dimension && isOpen(row + 1, col)) {
            quickUnion.union(toPoint(row, col), toPoint(row + 1, col));
        }
    }

    private int toPoint(int row, int col) {
        return (row - 1) * dimension + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateSite(row, col);
        return sites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateSite(row, col);
        return isOpen(row, col) &&
            fromTop(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return numOpenSites > 0 && 
            quickUnion.find(0) == quickUnion.find(dimension*dimension + 1);
    }

    private boolean fromTop(int row, int col) {
        return quickUnion.find(0) == quickUnion.find(toPoint(row, col));
    }

    private void validateDimension(int d) {
        if (d <= 0) throw new IllegalArgumentException();
    }

    private void validateSite(int row, int col) {
        if (row < 1 || row > dimension || col < 1 || col > dimension) {
            throw new IllegalArgumentException();
        }
    }
    // test client (optional)
    public static void main(String[] args) {
        final Percolation p = new Percolation(10);
        assert !p.percolates();
        assert p.fromTop(1, 1);
        p.open(1, 1);
        assert !p.fromTop(2, 1);
        p.open(2, 1);
        assert p.fromTop(2, 1);
        p.open(3, 1);
        assert p.fromTop(3, 1);
        p.open(4, 1);
        assert p.fromTop(4, 1);
        p.open(5, 1);
        p.open(6, 1);
        p.open(7, 1);
        p.open(8, 1);
        p.open(9, 1);
        assert !p.percolates();
        p.open(10, 1);
        assert p.fromTop(10, 1);
        assert p.percolates();
        assert p.numberOfOpenSites() == 10;

        // corner case 1
        final Percolation pc1 = new Percolation(1);

        assert !pc1.percolates();

        final int n = 200;
        final Percolation p2 = new Percolation(n);        
        while (!p2.percolates()) {
            final int row = StdRandom.uniform(1, n + 1);
            final int col = StdRandom.uniform(1, n + 1);
            p2.open(row, col);
        }

        System.out.println(p2.numOpenSites*1.0 / (n*n));
    }
}