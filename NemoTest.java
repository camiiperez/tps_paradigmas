import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

public class NemoTest {
	
	@Test public void test00CoordinatesDepthAndDirection(){
		Nemo nemo = new Nemo();
        assertArrayEquals(new int[]{0, 0}, nemo.getCoordinates());
        assertEquals(0, nemo.getDepth());
        assertEquals("North", nemo.getDirection());
	}	
	
	@Test public void test01NemoShouldNotMoveWhenNotAskedTo() {
		Nemo nemo = new Nemo();
		assertArrayEquals(new int[]{0, 0}, nemo.move("").getCoordinates());
        assertEquals(0, nemo.move("").getDepth());
        assertEquals("North"	, nemo.move("").getDirection());
	}
	
	@Test public void test02AssertsNemoDescending() {
		Nemo nemo = new Nemo();
		assertEquals(-1, nemo.move("d").getDepth());
	}
	
	@Test public void test03AssertsNemoAscends() {
		Nemo nemo = new Nemo();
		assertEquals(-1, nemo.move("d").getDepth());
		assertEquals(0,nemo.move("u").getDepth());
	}
	
	@Test public void test04NemoDoesntAscendWhenOnSurface(){
		Nemo nemo = new Nemo();
		assertEquals(0,nemo.move("u").getDepth());
	}
	
	@Test public void test05AssertNemoMovesR() {
		Nemo nemo = new Nemo();
		assertEquals("East",nemo.move("r").getDirection());
	}
	
	@Test public void test06AssertNemoMovesL() {
		Nemo nemo = new Nemo();
		assertEquals("West",nemo.move("l").getDirection());
	}
	
	@Test public void test07AssertNemoCanReceiveMultipleCommands() {
		Nemo nemo = new Nemo();
		assertEquals(-3,nemo.move("udddrrlr").getDepth());
		assertEquals("South",nemo.move("udddrrlr").getDepth());
	}
}
