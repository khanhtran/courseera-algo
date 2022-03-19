import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size;
    private class Node<NodeItem> {
        private NodeItem value;
        private Node<NodeItem> next;
        private Node<NodeItem> prev;
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
        if (head != null) {
            head.prev = node;
        } else {
            head = node;
            tail = head;
        }
        size++;
    }

    // add the item to the back
    public void addLast(final Item item) {
        if (item == null) { throw new IllegalArgumentException(); }

        Node<Item> node = new Node<>(item);
        node.prev = tail;
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) { throw new NoSuchElementException(); }
        final Item item = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = head; 
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) { throw new NoSuchElementException(); }
        final Item item = tail.value;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = tail;
        }
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
        final Deque<Integer> deque = new Deque<>();
        assert deque.isEmpty();

        deque.addFirst(100);
        deque.addLast(200);

        assert deque.size == 2;

        final Iterator<Integer> it = deque.iterator();
        assert it.hasNext();

        assert it.next() == 100;
        assert it.next() == 200;

        assert !it.hasNext();

        assert deque.removeFirst() == 100;
        assert deque.removeLast() == 200;
        assert deque.isEmpty();
    }

}