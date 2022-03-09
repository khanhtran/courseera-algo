import java.util.Arrays;
public class QuickUnionLazy {
    int[] root;
    
    public static void main(String[] args) {
        QuickUnionLazy quickUnion = new QuickUnionLazy(10);
        quickUnion.union(4, 3);
        quickUnion.union(3, 8);
        quickUnion.union(6, 5);
        quickUnion.union(9, 4);
        quickUnion.union(2, 1);
        System.out.println(quickUnion.connected(8,9));
        System.out.println(quickUnion.connected(5,4));
        quickUnion.union(5, 0);
        quickUnion.union(7, 2);
        quickUnion.union(6, 1);
        quickUnion.union(7, 3);
        System.out.println(Arrays.toString(quickUnion.root));
    }

    public QuickUnionLazy(int max) {
        root = new int[max];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    public int rootOf(int p) {
        int i = p;
        while (root[i] != i) {
            i = root[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root[p] == rootOf(q);
    }

    public void union(int p, int q) {
        int i = rootOf(q);
        int j = rootOf(q);
        root[i] = j;
    }
}