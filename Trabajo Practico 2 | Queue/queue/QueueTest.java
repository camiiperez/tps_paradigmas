package queue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class QueueTest {

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
    Queue queue = new Queue().add( SOMETHING );
    queue.take();
    
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
	  Queue queue = new Queue().add(SOMETHING);
	  //    Queue queue = new Queue();
//    String addedObject = SOMETHING;
//    queue.add( addedObject );
    
    assertEquals( SOMETHING, queue.take() );
  }

  @Test public void test06QueueBehavesFIFO() {
	  Queue queue = new Queue().add( FIRST ).add(SECOND);
//    Queue queue = new Queue();
//    String firstAddedObject = FIRST;
//    String secondAddedObject = SECOND;
//
//    queue.add( firstAddedObject );
//    queue.add( secondAddedObject );

    assertEquals( queue.take(), FIRST );
    assertEquals( queue.take(), SECOND );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
	  Queue queue = new Queue().add(FIRST).add(SECOND);
	  //    Queue queue = new Queue();
//    String firstAddedObject = FIRST;
//
//    queue.add( firstAddedObject );
//    queue.add( SECOND );

    assertEquals( queue.head(), FIRST );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
	  Queue queue = new Queue().add(SOMETHING);
	  //    Queue queue = new Queue();
//    queue.add( SOMETHING );
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, new Queue().add( FIRST ).add( SECOND ).size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    assertEquals("Queue is empty",assertThrows(Error.class, () -> queue.take()).getMessage());
    //    try {
//      queue.take();
//      fail( "Expected Error was not thrown." );
//    } catch (Error e) {
//      assertTrue( e.getMessage().equals( "Queue is empty" ) );
//    }
  }

  @Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = new Queue().add(SOMETHING);
    //queue.add( SOMETHING );
    queue.take();
    Error e = assertThrows(Error.class,() -> queue.take());
    assertEquals("Queue is empty",e.getMessage());
//    try {
//      queue.take();
//      fail( "Expected Error was not thrown." );
//    } catch (Error e) {
//      assertTrue( e.getMessage().equals( "Queue is empty" ) );
//    }
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    Queue queue = new Queue();
    Error e = assertThrows(Error.class,() -> queue.head());
    assertEquals("Queue is empty",e.getMessage());
    //    try {
//      queue.head();
//      fail( "Expected Error was not thrown." );
//    } catch (Error e) {
//      assertTrue( e.getMessage().equals( "Queue is empty" ) );
//    }
  }   
}