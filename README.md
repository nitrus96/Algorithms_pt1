Programming assignments for Princeton University's "Algorithms, Part 1" course

# Week 1: Percolation

Set-up:

* Load *percolation* folder as a project in Intellij IDEA (sets path to algs4 API)
  
To visualise percolation grid:


* Open *PercolationVisualizer.java*, click LIFT > Compile, then LIFT > Run with Arguments
* Input "resources/file_name_here.txt" as an argument to visualise the percolation grid
  
# Week 2: Queues

Deque API:
```
public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    public Deque()

    // is the deque empty?
    public boolean isEmpty()

    // return the number of items on the deque
    public int size()

    // add the item to the front
    public void addFirst(Item item)

    // add the item to the back
    public void addLast(Item item)

    // remove and return the item from the front
    public Item removeFirst()

    // remove and return the item from the back
    public Item removeLast()

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing 
    public static void main(String[] args)

}
```

Randomized Queue API: 
```
public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue
    public RandomizedQueue()

    // is the randomized queue empty?
    public boolean isEmpty()

    // return the number of items on the randomized queue
    public int size()

    // add the item
    public void enqueue(Item item)

    // remove and return a random item
    public Item dequeue()

    // return a random item (but do not remove it)
    public Item sample()

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()

    // unit testing 
    public static void main(String[] args)

}
```
