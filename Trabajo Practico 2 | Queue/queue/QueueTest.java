package queue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QueueTest {

  private static final String QUEUE_IS_EMPTY = "Queue is empty";
  private static final String SECOND = "Second";
  private static final String FIRST = "First";
  private static final String SOMETHING = "Something";

@Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( new Queue().add( SOMETHING ).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( SOMETHING, new Queue().add( SOMETHING ).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithSomething();
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }


  @Test public void test05TakeReturnsLastAddedObject() {
	  assertEquals( SOMETHING, queueWithSomething().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
	Queue queue = queueWithFirstAndSecond();

    assertEquals( queue.take(), FIRST );
    assertEquals( queue.take(), SECOND );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
	  assertEquals( queueWithFirstAndSecond().head(), FIRST );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
	assertEquals( 1, queueWithSomething().size() );
    queueWithSomething().head();
    assertEquals( 1, queueWithSomething().size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWithFirstAndSecond().size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertEquals(QUEUE_IS_EMPTY,assertThrows(Error.class, () -> new Queue().take()).getMessage());
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithSomething();
    queue.take();
    Error e = assertThrows(Error.class,() -> queue.take());
    assertEquals(QUEUE_IS_EMPTY,e.getMessage());
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Error e = assertThrows(Error.class,() -> new Queue().head());
    assertEquals(QUEUE_IS_EMPTY,e.getMessage());
  }
  
  private Queue queueWithSomething() {
		Queue queue = new Queue().add( SOMETHING );
		return queue;
	}
  
  private Queue queueWithFirstAndSecond() {
		return new Queue().add( FIRST ).add(SECOND);
	}

}