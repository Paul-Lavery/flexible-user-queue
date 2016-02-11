import org.scalatest._
import com.github.paulporu.PriorityQueue

class QueueSpec extends FlatSpec {

  "A queue" should "throw NoSuchElementException if an empty queue is popped" in {
    val emptyQueue = new PriorityQueue[Int]
    intercept[NoSuchElementException] {
      emptyQueue.dequeue()
    }
  }

  it should "throw UnsupportedOperationException if a queue is used as hashcode" in {
    val queue = PriorityQueue(1,2,3)
    intercept[UnsupportedOperationException] {
      queue.hashCode()
    }
  }

  it should "throw NoSuchElementException if an empty queue is peeked at" in {
    val emptyQueue = new PriorityQueue[Int]
    intercept[NoSuchElementException] {
      emptyQueue.head
    }
  }

  val elements = List(3,5,1,2,13,11,12)

  it should "print the queue" in {
    val queue = new PriorityQueue[Int]
    queue ++= elements
    val expected = "PriorityQueue(1, 2, 5, 3, 13, 11, 12)"
    assert(queue.toString() == expected)
  }

  it should "add elements while keeping min queue structure" in {
    val queue = new PriorityQueue[Int]
    queue ++= elements
    val expected = PriorityQueue(3, 5, 1, 2, 13, 11, 12)
    assert(queue == expected)
  }

  it should "remove elements while keeping min queue structure" in {
    val queue = new PriorityQueue[Int]
    queue ++= elements
    queue.remove(2)
    val expected = PriorityQueue(1, 2, 11, 3, 13, 12)
    assert(queue == expected)
  }

  it should "return the head of the queue" in {
    val queue = new PriorityQueue[Int]
    queue += 3
    var head = queue.head
    assert(head == 3)
    queue += 1
    head = queue.head
    assert(head == 1)
  }

  it should "pop the head of the queue" in {
    val queue = new PriorityQueue[Int]
    queue ++= elements
    var head = queue.dequeue()
    assert(head == 1)
    val expected = PriorityQueue(2, 3, 5, 12, 13, 11)
    assert(queue == expected)
  }

  it should "clear the queue" in {
    val queue = new PriorityQueue[Int]
    queue ++= elements
    assert(queue.isEmpty == false)
    queue.clear()
    assert(queue.isEmpty == true)
  }

  it should "return elements sorted by priority" in {
    val queue = new PriorityQueue[Int]
    queue ++= elements
    val elems = queue.getAllByPriority()
    val expected = List(1, 2, 3, 5, 11, 12, 13)
    assert(elems == expected)
  }
}