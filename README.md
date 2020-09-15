Programming assignments for Princeton University's "Algorithms, Part 1" course

# Week 1: Percolation

Set-up:

* Load *percolation* folder as a project in Intellij IDEA (sets path to algs4 API)
* Build project
  
To visualise percolation grid:

* Open *PercolationVisualizer.java*, then LIFT > Run with Arguments
* Input "resources/file_name_here.txt" as an argument to visualise the percolation grid
  
# Week 2: Queues

Deque API:
```
public class Deque<Item> implements Iterable<Item> {

    public Deque()    // construct an empty deque

    public boolean isEmpty()    // is the deque empty?
    
    public int size()    // return the number of items on the deque

    public void addFirst(Item item)    // add the item to the front
    
    public void addLast(Item item)    // add the item to the back

    public Item removeFirst()    // remove and return the item from the front

    public Item removeLast()    // remove and return the item from the back

    public Iterator<Item> iterator()    // return an iterator over items in order from front to back
    
    public static void main(String[] args)    // unit testing 
 
}
```

Randomized Queue API: 
```
public class RandomizedQueue<Item> implements Iterable<Item> {

    public RandomizedQueue()     // construct an empty randomized queue

    public boolean isEmpty()    // is the randomized queue empty?

    public int size()    // return the number of items on the randomized queue

    public void enqueue(Item item)    // add the item

    public Item dequeue()    // remove and return a random item

    public Item sample()    // return a random item (but do not remove it)

    public Iterator<Item> iterator()    // return an independent iterator over items in random order

    public static void main(String[] args)    // unit testing 
    
}
```
# Week 3: Collinear Points
Given a set of n distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points. 

Set-up:

* Load *collinear* folder as a project in Intellij IDEA (sets path to algs4 API)
* Build project
  
To visualise line segments:

* Open *FastCollinearPoints.java*, click LIFT > Run with Arguments
* Input "resources/file_name_here.txt" as an argument to visualise line segments (try out rs1423.txt!)

