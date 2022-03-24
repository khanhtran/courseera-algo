import java.util.Arrays;
public class QuickUnionWeighted {
    int[] parent;
    int[] size;
    public static void main(String[] args) {
        QuickUnionWeighted quickUnion = new QuickUnionWeighted(10);
        
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
        quickUnion.union(4, 3);        
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 4, 4, 5, 6, 7, 8, 9}));
        quickUnion.union(3, 8);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 4, 4, 5, 6, 7, 4, 9}));
        quickUnion.union(6, 5);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 4, 4, 6, 6, 7, 4, 9}));
        quickUnion.union(9, 4);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 1, 2, 4, 4, 6, 6, 7, 4, 4}));
        quickUnion.union(2, 1);
        assert(Arrays.equals(quickUnion.parent, new int[] {0, 2, 2, 4, 4, 6, 6, 7, 4, 4}));
        quickUnion.union(5, 0);
        assert(Arrays.equals(quickUnion.parent, new int[] {6, 2, 2, 4, 4, 6, 6, 7, 4, 4}));
        quickUnion.union(7, 2);
        assert(Arrays.equals(quickUnion.parent, new int[] {6, 2, 2, 4, 4, 6, 6, 2, 4, 4}));
        quickUnion.union(6, 1);
        assert(Arrays.equals(quickUnion.parent, new int[] {6, 2, 6, 4, 4, 6, 6, 2, 4, 4}));
        quickUnion.union(7, 3);
        assert(Arrays.equals(quickUnion.parent, new int[] {6, 2, 6, 4, 6, 6, 6, 2, 4, 4}));
    }

    public QuickUnionWeighted(int max) {
        parent = new int[max];
        size = new int[max];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rp = rootOf(p);
        int rq = rootOf(q);
        if (size[rp] < size[rq]) {
            parent[rp] = rq;
            size[rq] += size[rp];
        } else {
            parent[rq] = rp;
            size[rp] += size[rq];
        }
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

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}