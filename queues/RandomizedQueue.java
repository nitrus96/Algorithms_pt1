import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[8];
        size = 0;
    }

    // resize array (based on the textbook example)
    private void resize(int capacity) {
        assert capacity >= size;

        Item[] arr_copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            arr_copy[i] = arr[i];
        }
        arr = arr_copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size == arr.length) resize(2 * arr.length);
        arr[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int rndIdx = StdRandom.uniform(size);
        Item item = arr[rndIdx];
        arr[rndIdx] = arr[--size];
        arr[size] = null;

        if (size > 0 && size == arr.length / 4) resize(arr.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return arr[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private int[] randomArr = new int[size];


        public RandomArrayIterator() {
            i = 0;
            for (int j = 0; j < size; j++) {
                randomArr[j] = j;
            }
            StdRandom.shuffle(randomArr);
        }

        public boolean hasNext() {
            return i < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return arr[randomArr[i++]];
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 20; i++) {
            rq.enqueue(i);
        }
        System.out.println("Size: " + rq.size());
        System.out.print("Remove items: ");
        for (int i = 0; i < 16; i++) {
            System.out.print(rq.dequeue() + " ");
        }
        System.out.println(" ");
        System.out.print("Items in random queue: ");
        for (int i : rq) {
            System.out.print(i + " ");
        }
        System.out.println("Size:" + rq.size());

        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        System.out.println("Is empty?: " + rq.isEmpty());
        for (int i = 0; i < 10; i++) {
            rq.enqueue(i * 2);
        }
        System.out.println("Size: " + rq.size);
        System.out.print("Items in random queue: ");
        for (int i : rq) {
            System.out.print(i + " ");
        }


    }

}
