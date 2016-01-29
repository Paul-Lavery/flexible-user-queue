import org.scalatest._

class QueueSpec extends FlatSpec {

  "A queue" should "throw NoSuchElementException if an empty queue is popped" in {
    val emptyQueue = new PriorityQueue[Int]
    intercept[NoSuchElementException] {
      emptyQueue.dequeue()
    }
  }

  val elements = List(3,5,1,2,13,11,12)

  it should "add elements while keeping min queue structure" in {
    val queue = new PriorityQueue[Int]
    queue ++= elements
    val expected = PriorityQueue(3, 5, 1, 2, 13, 11, 12)
    assert(queue == expected)
  }
}