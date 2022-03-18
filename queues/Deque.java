import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size;
    private class Node<NodeItem> {
        private NodeItem value;
        private Node<NodeItem> next;
        public Node(final NodeItem value) {
            this.value = value;

        }
    }
    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
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
    public void addFirst(final Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        
        Node<Item> node = new Node<>(item);
        node.next = head;
        head = node;
        
        if (tail == null) { tail = head; }
    }

    // add the item to the back
    public void addLast(final Item item) {
        if (item == null) { throw new IllegalArgumentException(); }

        Node<Item> node = new Node<>(item);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) { throw new NoSuchElementException(); }
        return null;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) { throw new NoSuchElementException(); }
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> currentNode = head;

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public Item next() {
                // TODO Auto-generated method stub
                return null;
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