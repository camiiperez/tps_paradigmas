import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

public class SubmarineTest {
	
	@Test public void test00CoordinatesDepthAndDirection(){
		Submarine nemo = new Submarine(0,0,"North");
        assertArrayEquals(new int[]{0, 0}, nemo.getPosition().getCoordinates());
        assertEquals(0, nemo.getDepth().getValue());
        assertEquals("North", nemo.getDirection().getValue());
	}	
	@Test public void test01NemoShouldSpawnCorrectly() {
		Submarine nemo = new Submarine (3,5,"South");
        assertArrayEquals(new int[]{3, 5}, nemo.getPosition().getCoordinates());
        assertEquals(0, nemo.getDepth().getValue());
        assertEquals("South", nemo.getDirection().getValue());
	}
	
	@Test public void test02NemoShouldNotMoveWhenNotAskedTo() {
		Submarine nemo = new Submarine(0,0,"North");
		assertArrayEquals(new int[]{0, 0}, nemo.sendInstructions("").getPosition().getCoordinates());
        assertEquals(0, nemo.sendInstructions("").getDepth().getValue());
        assertEquals("North", nemo.sendInstructions("").getDirection().getValue());
	}	
	
	@Test public void test03AssertsNemoDescending() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(-1, nemo.sendInstructions("d").getDepth().getValue());
	}
	
	@Test public void test04AssertsNemoAscends() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(-1, nemo.sendInstructions("d").getDepth().getValue());
		assertEquals(0,nemo.sendInstructions("u").getDepth().getValue());
	}
	
	@Test public void test05NemoDoesntAscendWhenOnSurface(){
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(0,nemo.sendInstructions("u").getDepth().getValue());
	}
	
	@Test public void test06AssertNemoMovesR() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals("East",nemo.sendInstructions("r").getDirection().getValue());
	}
	
	@Test public void test07AssertNemoMovesL() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals("West",nemo.sendInstructions("l").getDirection().getValue());
		
		
	}
		@Test public void test08AssertNemoMovesF() {
			Submarine nemo = new Submarine(0,0,"North");
			assertArrayEquals(new int[]{0, 1}, nemo.sendInstructions("f").getPosition().getCoordinates());
	}
	
	@Test public void test09AssertsNemoCanMoveForwardInDirection() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("f");
		assertArrayEquals(new int[]{0, 1},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{1, 1},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{1, 0},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{0, 0},nemo.getPosition().getCoordinates());
		
	}
	
	@Test public void test10AssertNemoCanMoveBackwardsInDirection() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("rrf");
		assertArrayEquals(new int[]{0, -1},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{-1, -1},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{-1, 0},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{0, 0},nemo.getPosition().getCoordinates());
	}
	@Test public void test11AssertsNemoSpawnsWithCapsuleInside() {
		Submarine nemo = new Submarine(0,0,"North");
		assertTrue(nemo.getCapsuleState());
	}
	
	@Test public void test12AssertsNemoReleasesCapsuleAtSurface() {
		Submarine nemo = new Submarine(0,0,"North");
		assertFalse(nemo.sendInstructions("m").getCapsuleState());
	}
	
	@Test public void test13AssertsNemoReleasesCapsuleAtMinusOneDepth() {
		Submarine nemo = new Submarine(0,0,"North");
		assertFalse(nemo.sendInstructions("dm").getCapsuleState());
	}
	
	@Test public void test14AssertsNemoCannotReleaseCapsuleAtLessThanMinusOneDepthAndStopsWorking() {
		Submarine nemo = new Submarine(0,0,"North");
		assertThrowsLike("El submarino ha dejado de funcionar",() -> nemo.sendInstructions("ddm"));
	}
	
	@Test public void test15AssertNemoCanReceiveMultipleCommands() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(-3,nemo.sendInstructions("udddrrlr").getDepth().getValue());
		assertEquals("South",nemo.getDirection().getValue());
	}
	
	  private void assertThrowsLike(String expectedMessage, ThrowingRunnable throwingRunnable) {
			assertEquals(expectedMessage,assertThrows(RuntimeException.class, throwingRunnable).getMessage());
		}

}