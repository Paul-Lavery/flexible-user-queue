# flexible-user-queue

This Scala class implements a flexible priority queue using a min-heap. The code is based on the Scala collection [PriorityQueue.scala](https://github.com/scala/scala/blob/2.12.x/src/library/scala/collection/mutable/PriorityQueue.scala), but the advantage here is that all the operations are implemented so the min-heap paradigm stays true at all times. 

One of the main applications for this is to manage physical user queues. As such a few extra utility functions that you would not find in a regular priority queue implementation were thrown in for good measure (remove, getAllByPriority, etc).

## Setup

The library has been released to Maven Central so you can use it by simply listing it as a dependency in your build.sbt:
```scala
"com.github.paulporu" %% "flexible-user-queue" % "1.2"
```
You can also have a look at this simple Play application for an example of how to use this priority queue in your own project.  

## Usage

Import the class in your code:
```scala
import com.github.paulporu.PriorityQueue
```

The following functions are now available.
```scala
val emptyQueue = new PriorityQueue[Int]

// Returns true
emptyQueue.isEmpty

var queue = PriorityQueue(1)

// Returns 1, queue remains unchanged
queue.head

// Returns 1, queue is now empty
queue.dequeue()

// Adds 3 to the queue
queue += 3

// Adds the elements of the List to the queue while keeping the min priority of the heap, so now queue = PriorityQueue(1, 3, 12, 5)
val elements = List(5,12,1)
queue ++= elements

// Returns the element with index 2 and deletes it from the queue 
queue.remove(2)

// Returns a List containing all elements of the queue sorted ascendingly by priority
queue.getAllByPriority()

// Prints the queue as a String
queue.toString()
    
// Empties the queue
queue.clear()
```


## Dependencies

[Scalatest](http://www.scalatest.org/) was used in combination with the [scoverage SBT plugin](https://github.com/scoverage/sbt-scoverage) for unit testing. [Scalastyle](http://www.scalastyle.org/) was used because it's always good to follow some guidelines. The [Sonatype SBT plugin](https://github.com/xerial/sbt-sonatype) was used to release the library to the Maven Central repository.

[Scoverage](https://github.com/scoverage/scalac-scoverage-plugin) shows on the current version of this library (v1.2) a statement coverage of 89.16 % and a branch coverage of 78.57 %.

## License

MIT License
