Programming assignments for Princeton University's "Algorithms, Part 1" course 

https://www.coursera.org/learn/algorithms-part1

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

# Week 4: Slider Puzzle (8 Puzzle)
The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so that they are in row-major order, using as few moves as possible. You are permitted to slide tiles either horizontally or vertically into the blank square. The solution to the 8-puzzle problem illustrates a general artificial intelligence methodology known as the A* search algorithm implemented with a priority queue.

Set-up:

* Load *8puzzle* folder as a project in Intellij IDEA (sets path to algs4 API)
* Build project
  
To get the sequence of board states corresponding to the shortest path to the solution:

* Open *Solver.java*, click LIFT > Run with Arguments
* Input "resources/file_name_here.txt" as an argument to print the sequence to STDOUT

# Week 5: Kd-Trees

***range* and *nearest* methods in KdTree.java are based on the solution by GitHub user Megha Jakhotia https://github.com/MeghaJakhotia**

Kd-Tree is a data type used to represent a set of points in the unit square (all points have x- and y-coordinates between 0 and 1) using a 2d-tree to support efficient range search (find all of the points contained in a query rectangle) and nearest-neighbor search (find a closest point to a query point). 2d-trees have numerous applications, ranging from classifying astronomical objects to computer animation to speeding up neural networks to mining data to image retrieval. 

Set-up:

* Load *kdtree* folder as a project in Intellij IDEA (sets path to algs4 API)
* Build project
  
To visualise the set of points contained within a user-defined rectangle:

* Open *RangeSearchVisualizer.java*, click LIFT > Run with Arguments
* Input "resources/file_name_here.txt" as an argument to run an interactive StdDraw session

To visualise the nearest neighbor of a user-defined point on a plane:

* Open *NearestNeighborVisualizer.java*, click LIFT > Run with Arguments
* Input "resources/file_name_here.txt" as an argument to run an interactive StdDraw session
