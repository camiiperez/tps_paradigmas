package queue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;


public class QueueTest {

  private static String Second = "Second";
  private static String First = "First";
  private static String Something = "Something";

@Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWith(Something).isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( Something, queueWith(Something).head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWith(Something);
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
	  assertEquals( Something, queueWith(Something).take() );
  }

  @Test public void test06QueueBehavesFIFO() {
	  Queue queue = queueWith(First,Second);

    assertEquals( queue.take(), First );
    assertEquals( queue.take(), Second );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
	  assertEquals( queueWith(First,Second).head(), First );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
	assertEquals( 1, queueWith(Something).size() );
    queueWith(Something).head();
    assertEquals( 1, queueWith(Something).size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWith(First,Second).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike(ToxicBox.QueueIsEmpty, () -> new Queue().take());
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWith(Something);
    queue.take();
    assertThrowsLike(ToxicBox.QueueIsEmpty,() -> queue.take());
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike(ToxicBox.QueueIsEmpty,() -> new Queue().head());
    }
  
  private void assertThrowsLike(String expectedMessage, ThrowingRunnable throwingRunnable) {
		assertEquals(expectedMessage,assertThrows(Error.class, throwingRunnable).getMessage());
	}

  private Queue queueWith(String message) {
		return new Queue().add( message );
	}
  
  private Queue queueWith(String message1, String message2) {
		return new Queue().add( message1 ).add(message2);
	}
}