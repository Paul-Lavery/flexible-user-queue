# flexible-user-queue

This Scala class implements a flexible priority queue using a min-heap. The code is based on the Scala collection [PriorityQueue.scala](https://github.com/scala/scala/blob/2.12.x/src/library/scala/collection/mutable/PriorityQueue.scala), but the advantage here is that all the operations are implemented so the min-heap paradigm stays true at all times. 

One of the main applications for this is to manage physical user queues. As such a few extra utility functions were thrown in for good measure (remove, getAllByPriority, etc).

[Scalatest](http://www.scalatest.org/) was used in combination with the [scoverage SBT plugin](https://github.com/scoverage/sbt-scoverage) for unit testing. [Scalastyle](http://www.scalastyle.org/) was used because it's always good to follow some guidelines.

