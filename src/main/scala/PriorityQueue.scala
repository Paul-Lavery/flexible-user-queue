// The MIT License (MIT)
// Copyright (c) 2016 Paul Lavery
//
// See the LICENCE.txt file distributed with this work for additional information regarding copyright ownership.

import scala.collection.mutable.ArrayBuffer
import scala.collection.GenTraversableOnce
import scala.collection.generic.GenericOrderedCompanion
import scala.collection.generic.GenericOrderedTraversableTemplate
import scala.collection.generic.OrderedTraversableFactory
import scala.collection.generic.CanBuildFrom
import scala.collection.mutable.Builder
import scala.util.Sorting


/** This class implements priority queues using a min heap.
 *  To prioritize elements of type A there must be an implicit
 *  Ordering[A] available at creation.
 *
 * @param ord Ordering of data.
 * @tparam A Type of data.
 */
class PriorityQueue[A](implicit val ord: Ordering[A])
  extends Iterable[A]
  with GenericOrderedTraversableTemplate[A, PriorityQueue]
  with Builder[A, PriorityQueue[A]]
{

  import ord._

  override def orderedCompanion: GenericOrderedCompanion[PriorityQueue] = PriorityQueue

  protected[this] override def newBuilder = new PriorityQueue[A]

  override def result: this.type = this

  private val heap = new ArrayBuffer[A]

  override def iterator: Iterator[A] = heap.iterator

  /** Swap two elements of the queue. */
  private def swap(a: Int, b: Int) {
    val h = heap(a)
    heap(a) = heap(b)
    heap(b) = h
  }

  /** Heapify the heap up to ar[index]
   *
   *  @param  index     index of the last element to heapify up.
   */
  private def heapifyUp(index: Int): Unit = {
    var k: Int = index
    while (k > 0 && heap(k / 2) > heap(k)) {
      swap(k, k / 2)
      k = k / 2
    }
  }

  /** Heapify the heap down from heap[index]
   *
   *  @param  index     index of the first element to heapify down.
   */
  private def heapifyDown(index: Int): Unit = {
    var k: Int = index
    while (2 * k < heap.length) {
      var j = 2 * k
      //Select smallest child of heap(k) between heap(2k + 1) and heap(2k + 2)
      if (j < heap.length-1 && heap(j) > heap(j + 1))
        j += 1
      //if heap(k) is <= to its smallest child then we're done
      if (heap(k) <= heap(j))
        return
      else {
        swap(j,k)
        k = j
      }
    }
  }

  /** Returns the element with the lowest priority in the queue,
   *  or throws an error if there is no element contained in the queue.
   */
  override def head: A = if (heap.length > 0) heap(0) else throw new NoSuchElementException("queue is empty")

  /** Clears the queue. */
  def clear(): Unit = heap.clear()

  /** Returns a textual representation of a queue as a string. */
  override def toString(): String = heap.toList.mkString("PriorityQueue(", ", ", ")")

  /** Checks if the heaps of the two priority queues are equal */
  override def equals(that: Any): Boolean = that.isInstanceOf[PriorityQueue[A]] &&
    this.iterator.toList == that.asInstanceOf[PriorityQueue[A]].iterator.toList

  /** The hashCode method always yields an error, since it is not
   *  safe to use mutable queues as keys in hash tables.
   *
   *  @return never.
   */
  override def hashCode(): Int =
    throw new UnsupportedOperationException("unsuitable as hash key")

  /** Inserts a single element into the priority queue.
   *
   *  @param  elem        the element to insert.
   *  @return             the heap.
   */
  def +=(elem: A): this.type = {
    heap += elem
    heapifyUp(heap.length-1)
    this
  }

  /** Returns the element with the lowest priority in the queue,
   *  and removes this element from the queue.
   *
   *  @throws java.util.NoSuchElementException
   *  @return   the element with the lowest priority.
   */
  def dequeue(): A = {
    if (heap.length > 0) {
      swap(0, heap.length-1)
      val head = heap.remove(heap.length-1)
      heapifyDown(0)
      head
    } else
      throw new NoSuchElementException("no element to remove from heap")
  }

  /** Returns the element with the corresponding index,
   *  and removes this element from the queue.
   *
   *  @param  index     index of the element to remove
   *
   *  @throws java.util.NoSuchElementException
   *  @return   the element with the lowest priority.
   */
  def remove(index: Int): A = {
    if (heap.length > index) {
      swap(index, heap.length-1)
      val elem = heap.remove(heap.length-1)
      heapifyDown(index)
      elem
    } else
      throw new NoSuchElementException
  }

  /** Returns a List containing all the elements of the queue
   *  sorted by ascending priority
   */
  def getAllByPriority(): List[A] = this.iterator.toList.sorted(ord)

}

object PriorityQueue extends OrderedTraversableFactory[PriorityQueue] {
  def newBuilder[A](implicit ord: Ordering[A]): Builder[A, PriorityQueue[A]] = new PriorityQueue[A]
  implicit def canBuildFrom[A](implicit ord: Ordering[A]): CanBuildFrom[Coll, A, PriorityQueue[A]] = new GenericCanBuildFrom[A]
}
