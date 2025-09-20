import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // 0 is blocked, 1 is open

    int[][] q;
    private int OpenSites = 0;

    // private QuickFindUF quick;

    private WeightedQuickUnionUF quick;

    private int n;
    private int ProxyTop;
    // Generates a variable to hold the id in the 1D data structure
    private int ProxyBottom;

    private int Convert(int row, int col) {
        return (row * n) + col;
    }

    public Percolation(int n) {
        // Checks if input is valid
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        // makes n accesible throughout class
        this.n = n;
        // generates n by n grid, then fills with empty slots
        q = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int x = 0; x < n; x++) {
                q[i][x] = 0;
            }
        }
        // quickfind commented out- but can be switched in easily
        // quick = new QuickFindUF((n * n) + 2);
        // generates union object with length equal to size of grid plus 2 for proxy top and bottom
        quick = new WeightedQuickUnionUF((n * n) + 2);
        // sets ids of proxy top and bottom in 1D grid, makes accesible in class
        this.ProxyTop = n * n;
        this.ProxyBottom = (n * n) + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // throws error if argument is out of bounds
        if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
            throw new IllegalArgumentException();
        }
        // only runs if site is not already open
        if (q[row][col] == 0) {
            // adds one to track # of open sites needed for stats
            OpenSites += 1;
            q[row][col] = 1;
            // edge case of n being 1
            if (n == 1) {
            }
            else if (row == 0 && col == 0) {
                // top left
                // for all top rows, connect to proxy top
                quick.union(Convert(row, col), ProxyTop);
                if (q[row + 1][col] == 1) {
                    // down1
                    quick.union(Convert(row, col), Convert(row + 1, col));
                }
                if (q[row][col + 1] == 1) {
                    // right1
                    quick.union(Convert(row, col), Convert(row, col + 1));
                }
            }
            else if (row == 0 && col == n - 1) {
                // top right
                quick.union(Convert(row, col), ProxyTop);
                if (q[row + 1][col] == 1) {
                    // down1
                    quick.union(Convert(row, col), Convert(row + 1, col));
                }
                if (q[row][col - 1] == 1) {
                    // left1
                    quick.union(Convert(row, col), Convert(row, col - 1));
                }
            }
            else if (row == n - 1 && col == 0) {
                // bottom left
                // for all bottom rows, connect to proxy bottom
                quick.union(Convert(row, col), ProxyBottom);
                if (q[row - 1][col] == 1) {
                    // up1
                    quick.union(Convert(row, col), Convert(row - 1, col));
                }
                if (q[row][col + 1] == 1) {
                    // right1
                    quick.union(Convert(row, col), Convert(row, col + 1));
                }
            }
            else if (row == n - 1 && col == n - 1) {
                // bottom right
                quick.union(Convert(row, col), ProxyBottom);
                if (q[row - 1][col] == 1) {
                    // up1
                    quick.union(Convert(row, col), Convert(row - 1, col));
                }
                if (q[row][col - 1] == 1) {
                    // left1
                    quick.union(Convert(row, col), Convert(row, col - 1));
                }
            }
            else if (row == 0) {
                // top
                quick.union(Convert(row, col), ProxyTop);
                if (q[row + 1][col] == 1) {
                    // down1
                    quick.union(Convert(row, col), Convert(row + 1, col));
                }
                if (q[row][col + 1] == 1) {
                    // right1
                    quick.union(Convert(row, col), Convert(row, col + 1));
                }
                if (q[row][col - 1] == 1) {
                    // left1
                    quick.union(Convert(row, col), Convert(row, col - 1));
                }
            }
            else if (row == n - 1) {
                // bottom
                quick.union(Convert(row, col), ProxyBottom);
                if (q[row - 1][col] == 1) {
                    // up1
                    quick.union(Convert(row, col), Convert(row - 1, col));
                }
                if (q[row][col + 1] == 1) {
                    // right1
                    quick.union(Convert(row, col), Convert(row, col + 1));
                }
                if (q[row][col - 1] == 1) {
                    // left1
                    quick.union(Convert(row, col), Convert(row, col - 1));
                }
            }
            else if (col == 0) {
                // left
                if (q[row - 1][col] == 1) {
                    // up1
                    quick.union(Convert(row, col), Convert(row - 1, col));
                }
                if (q[row + 1][col] == 1) {
                    // down1
                    quick.union(Convert(row, col), Convert(row + 1, col));
                }
                if (q[row][col + 1] == 1) {
                    // right1
                    quick.union(Convert(row, col), Convert(row, col + 1));
                }
            }
            else if (col == n - 1) {
                // right
                if (q[row - 1][col] == 1) {
                    // up1
                    quick.union(Convert(row, col), Convert(row - 1, col));
                }
                if (q[row + 1][col] == 1) {
                    // down1
                    quick.union(Convert(row, col), Convert(row + 1, col));
                }
                if (q[row][col - 1] == 1) {
                    // left1
                    quick.union(Convert(row, col), Convert(row, col - 1));
                }
            }
            else {
                // normal
                if (q[row - 1][col] == 1) {
                    // up1
                    quick.union(Convert(row, col), Convert(row - 1, col));
                }
                if (q[row + 1][col] == 1) {
                    // down1
                    quick.union(Convert(row, col), Convert(row + 1, col));
                }
                if (q[row][col - 1] == 1) {
                    // left1
                    quick.union(Convert(row, col), Convert(row, col - 1));
                }
                if (q[row][col + 1] == 1) {
                    // right1
                    quick.union(Convert(row, col), Convert(row, col + 1));
                }
            }
            isFull(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // if out of range, throw error
        if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
            throw new IllegalArgumentException();
        }
        // return true if slot is equal to 1
        return q[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // Check if coordinate shares same Union id as one in the top row using find
        if (row < 0 || col < 0 || row > n - 1 || col > n - 1) {
            throw new IllegalArgumentException();
        }
        // returns true if the site is connected to the proxy top
        return quick.connected(ProxyTop, Convert(row, col));
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        // returns sum that is updated in open
        return OpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // returns true if the proxy top is connected to the proxy bottom
        return quick.connected(ProxyTop, ProxyBottom);
    }

    // unit testing (required)
    public static void main(String[] args) {
    }

}