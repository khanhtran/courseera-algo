import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> head;
    private int size;
    private class Node<NodeItem> {
        private NodeItem value;
        private Node<NodeItem> next;
        public Node(final NodeItem value) {
            this.value = value;
        }
    }
    // construct an empty deque
    public RandomizedQueue() {
        head = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void enqueue(final Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        size++;
    }

    // remove and return the item from the front
    public Item dequeue() {
        if (size == 0) { throw new NoSuchElementException(); }
        final Item item = head.value;
        
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> currentNode;

            @Override
            public boolean hasNext() {
                if (currentNode == null) {
                    return head != null;
                }

                return currentNode.next != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                if (currentNode == null) {
                    currentNode = head;
                } else {
                    currentNode = currentNode.next;
                }
                return currentNode.value;
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        
    }

}