import edu.princeton.cs.algs4.StdOut;
public class Permutation {
    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);
        final RandomizedQueue<String> queue = new RandomizedQueue<>();
        
        for (int i = 1; i < args.length; i++) {
            queue.enqueue(args[i]);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
 }