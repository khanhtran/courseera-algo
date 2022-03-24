public class QuickUnionEager {
    private static int MAX = 1000;
    int[] ids;
    
    public static void main(String[] args) {
        QuickUnionEager quickUnion = new QuickUnionEager();
        quickUnion.union(0, 5);
        quickUnion.union(5, 6);
        quickUnion.union(6, 8);

        System.out.println(quickUnion.connected(0, 8));
        System.out.println(quickUnion.connected(0, 7));
    }

    public QuickUnionEager() {
        ids = new int[MAX];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return ids[p] == ids[q];
    }

    public void union(int p, int q) {
        int replaceWith = ids[p];
        int toBeReplaced = ids[q];
        
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == toBeReplaced) {
                ids[i] = replaceWith;
            }
        }
    }
}