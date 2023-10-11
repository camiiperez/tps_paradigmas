import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

public class NemoTest {
	
	@Test public void test00CoordinatesDepthAndDirection(){
		Submarine nemo = new Submarine();
        assertArrayEquals(new int[]{0, 0}, nemo.getCoordinates());
        assertEquals(0, nemo.getDepth());
        assertEquals("North", nemo.getDirection());
	}	
	
	@Test public void test01NemoShouldNotMoveWhenNotAskedTo() {
		Submarine nemo = new Submarine();
		assertArrayEquals(new int[]{0, 0}, nemo.sendInstructions("").getCoordinates());
        assertEquals(0, nemo.sendInstructions("").getDepth());
        assertEquals("North"	, nemo.sendInstructions("").getDirection());
	}
	
	@Test public void test02AssertsNemoDescending() {
		Submarine nemo = new Submarine();
		assertEquals(-1, nemo.sendInstructions("d").getDepth());
	}
	
	@Test public void test03AssertsNemoAscends() {
		Submarine nemo = new Submarine();
		assertEquals(-1, nemo.sendInstructions("d").getDepth());
		assertEquals(0,nemo.sendInstructions("u").getDepth());
	}
	
	@Test public void test04NemoDoesntAscendWhenOnSurface(){
		Submarine nemo = new Submarine();
		assertEquals(0,nemo.sendInstructions("u").getDepth());
	}
	
	@Test public void test05AssertNemoMovesR() {
		Submarine nemo = new Submarine();
		assertEquals("East",nemo.sendInstructions("r").getDirection());
	}
	
	@Test public void test06AssertNemoMovesL() {
		Submarine nemo = new Submarine();
		assertEquals("West",nemo.sendInstructions("l").getDirection());
		
	@Test public void test07AssertNemoMovesF() {
		
	}
		
	}
	@Test public void test07NemoReleasesCapsuleAtCorrectDepth() {
		Submarine nemo = new Submarine();
		assertEquals("La capsula se ha liberado",nemo.sendInstructions("m"));
	}
	
	@Test public void test08AssertsNemoCanMoveForwardInDirection() {
		Submarine nemo = new Submarine();
		nemo.sendInstructions("f");
		assertArrayEquals(new int[]{0, 1},nemo.getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{1, 1},nemo.getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{1, 0},nemo.getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{0, 0},nemo.getCoordinates());
		
	}
	
	@Test public void test09AssertNemoCanMoveBackwardsInDirection() {
		Submarine nemo = new Submarine();
		nemo.sendInstructions("rrf");
		assertArrayEquals(new int[]{0, -1},nemo.getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{-1, -1},nemo.getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{-1, 0},nemo.getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{0, 0},nemo.getCoordinates());
	}
	
	@Test public void test10AssertsNemoCannotReleaseCapsuleAtLessThanMinusOneDepthAndStopsWorking() {
		Submarine nemo = new Submarine();
		assertThrowsLike("El submarino ha dejado de funcionar",() -> nemo.sendInstructions("ddm"));
	}
	
	@Test public void test11AssertNemoCanReceiveMultipleCommands() {
		Submarine nemo = new Submarine();
		assertEquals(-3,nemo.sendInstructions("udddrrlr").getDepth());
		assertEquals("South",nemo.getDirection());
	}
	
	  private void assertThrowsLike(String expectedMessage, ThrowingRunnable throwingRunnable) {
			assertEquals(expectedMessage,assertThrows(RuntimeException.class, throwingRunnable).getMessage());
		}

}
