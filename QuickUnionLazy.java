import java.util.Arrays;
public class QuickUnionLazy {
    int[] parent;
    
    public static void main(String[] args) {
        QuickUnionLazy quickUnion = new QuickUnionLazy(10);
        
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
        quickUnion.union(4, 3);        
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 3, 3, 5, 6, 7, 8, 9}));
        quickUnion.union(3, 8);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 8, 3, 5, 6, 7, 8, 9}));
        quickUnion.union(6, 5);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 8, 3, 5, 5, 7, 8, 9}));
        quickUnion.union(9, 4);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 8, 3, 5, 5, 7, 8, 8}));
        quickUnion.union(2, 1);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 1, 8, 3, 5, 5, 7, 8, 8}));
        assert(quickUnion.connected(8, 9));
        assert(!quickUnion.connected(5, 4));
    }

    public QuickUnionLazy(int max) {
        parent = new int[max];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        parent[rootOf(p)] = rootOf(q);
    }

    public int rootOf(int p) {
        int i = p;
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return parent[p] == rootOf(q);
    }

    
}