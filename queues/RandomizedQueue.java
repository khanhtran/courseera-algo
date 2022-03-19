import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {    
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private Item[] data;

    // construct an empty deque
    public RandomizedQueue() {
        size = 0;
        data = (Item[]) new Object[INITIAL_CAPACITY];
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
        if (size == data.length) {
            resize(2*data.length);
        }
        data[size] = item;
        size++;
    }

    private void resize(final int newLength) {
        final Item[] copy = (Item[]) new Object[newLength];
        for (int i = 0; i < data.length; i++) {
            copy[i] = data[i];
        }
        data = copy;
    }
    // remove and return the item from the front
    public Item dequeue() {
        if (size == 0) { throw new NoSuchElementException(); }
        final int index = StdRandom.uniform(size);
        final Item item = data[index];
        data[index] = null;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;
        return item;
    }

    public Item sample() {
        if (size == 0) { throw new NoSuchElementException(); }
        final int index = StdRandom.uniform(size);
        return data[index];
    }
    // random order
    public Iterator<Item> iterator() {
        final Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < size; i++) {
            copy[i] = data[i];
        }
        StdRandom.shuffle(copy);

        return new Iterator<Item>() {
            int index = -1;
            @Override
            public boolean hasNext() {
                return (index + 1) < copy.length;
            }

            @Override
            public Item next() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                index = index + 1;
                return copy[index];
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        final RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        System.out.println(queue.sample());
        assert queue.size == 12;
        queue.dequeue();
        assert queue.size == 11;
        final Iterator<Integer> it = queue.iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            System.out.println(it.next());
        }
        assert count == queue.size;
    }
}