import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

public class SubmarineTest {
	
	@Test public void test00AssertsNemoSpawnsInOriginAndNorth(){
		Submarine nemo = new Submarine(0,0,"North");
        assertArrayEquals(new int[]{0, 0}, nemo.getPosition().getCoordinates());
        assertEquals(0, nemo.getDepth().getValue());
        assertEquals("North", nemo.getDirection().getValue());
	}	
	@Test public void test01AssertsNemoSpawnsNotInAnotherPositionAndDirection() {
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

	@Test public void test03AssertsNemoDescendsOnce() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(-1, nemo.sendInstructions("d").getDepth().getValue());
	}
	
	@Test public void test04AssertsNemoDescendsMoreThanOnce() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(-4, nemo.sendInstructions("dddd").getDepth().getValue());
	}
	
	@Test public void test05AssertsNemoAscends() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(-1, nemo.sendInstructions("d").getDepth().getValue());
		assertEquals(0,nemo.sendInstructions("u").getDepth().getValue());
	}
	
	@Test public void test06AssertsNemoAscendsWhenDeeper() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("dddd");
		assertEquals(-2,nemo.sendInstructions("uu").getDepth().getValue());
	}
	
	@Test public void test07NemoDoesntAscendWhenOnSurface(){
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals(0,nemo.sendInstructions("u").getDepth().getValue());
	}
	
	@Test public void test08AssertsNemoAscendsWhenDeeperButDoesntAscendAtSurface() {
	Submarine nemo = new Submarine(0,0,"North");
	nemo.sendInstructions("dddd");
	assertEquals(0,nemo.sendInstructions("uuuuuu").getDepth().getValue());
	}
	
	@Test public void test09AssertNemoMovesRightOnce() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals("East",nemo.sendInstructions("r").getDirection().getValue());
	}
	
	@Test public void test10AssertsNemoMovesRightMultipleTimes() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals("West",nemo.sendInstructions("rrr").getDirection().getValue());
	}
	
	@Test public void test11AssertsNemoMovesRightAtDepth() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("ddd");
		assertEquals("South",nemo.sendInstructions("rr").getDirection().getValue());
		
	}
	
	@Test public void test12AssertsNemoMovesLeftOnce() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals("West",nemo.sendInstructions("l").getDirection().getValue());
		
	}
	
	@Test public void test13AssertsNemoMovesLeftMultipleTimes() {
		Submarine nemo = new Submarine(0,0,"North");
		assertEquals("East",nemo.sendInstructions("lll").getDirection().getValue());
	}
	
	@Test public void test14AssertsNemoMovesLeftAtDepth() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("ddd");
		assertEquals("South",nemo.sendInstructions("ll").getDirection().getValue());
	}
	
	@Test public void test15AssertNemoMovesForwardOnce() {
			Submarine nemo = new Submarine(0,0,"North");
			assertArrayEquals(new int[]{0, 1}, nemo.sendInstructions("f").getPosition().getCoordinates());
	}
	
	@Test public void test16AssertsNemoMovesForwardMultipleTimes() {
		Submarine nemo = new Submarine(0,0,"North");
		assertArrayEquals(new int[]{0, 3}, nemo.sendInstructions("fff").getPosition().getCoordinates());
	}
	
	@Test public void test17AssertsNemoMovesForwardAtDepth() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("ddd");
		assertArrayEquals(new int[]{0, 3}, nemo.sendInstructions("fff").getPosition().getCoordinates());
	}
	
	@Test public void test18AssertsNemoCanMoveForwardInDirection() {
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
	@Test public void test19AssertsNemoCanMoveForwardInDirectionAndDepth() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("ddd");
		nemo.sendInstructions("f");
		assertArrayEquals(new int[]{0, 1},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{1, 1},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{1, 0},nemo.getPosition().getCoordinates());
		nemo.sendInstructions("rf");
		assertArrayEquals(new int[]{0, 0},nemo.getPosition().getCoordinates());
		
	}
	//Cuanto conocimiento se gana con este test? Habria que repensarlo.
	@Test public void test20AssertNemoCanMoveBackwardsInDirection() {
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
	
	@Test public void test21AssertsNemoSpawnsWithCapsuleInside() {
		Submarine nemo = new Submarine(0,0,"North");
		assertTrue(nemo.getCapsuleState());
	}
	
	@Test public void test22AssertsNemoReleasesCapsuleAtSurface() {
		Submarine nemo = new Submarine(0,0,"North");
		assertFalse(nemo.sendInstructions("m").getCapsuleState());
	}
	
	@Test public void test23AssertsNemoReleasesCapsuleAtMinusOneDepth() {
		Submarine nemo = new Submarine(0,0,"North");
		assertFalse(nemo.sendInstructions("dm").getCapsuleState());
	}
	
	@Test public void test24AssertsNemoCannotReleaseCapsuleAtLessThanMinusOneDepthAndStopsWorking() {
		Submarine nemo = new Submarine(0,0,"North");
		assertThrowsLike("El submarino ha dejado de funcionar",() -> nemo.sendInstructions("ddm"));
		assertTrue(nemo.getCapsuleState());
	}
	
	@Test public void test25AssertNemoCanReceiveMultipleCommands() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("udddrrlrff");
		assertEquals(-3,nemo.getDepth().getValue());
		assertArrayEquals(new int[]{0, -2},nemo.getPosition().getCoordinates());
		assertEquals("South",nemo.getDirection().getValue());
	}
	
	@Test public void test26AssertNemoCanReceiveMultipleCommandsAndStopsWorkingWhenCapsuleIsReleasedAtWrongDepth() {
		Submarine nemo = new Submarine(0,0,"North");
		nemo.sendInstructions("udddrrlrff");
		assertEquals(-3,nemo.getDepth().getValue());
		assertArrayEquals(new int[]{0, -2},nemo.getPosition().getCoordinates());
		assertEquals("South",nemo.getDirection().getValue());
		assertThrowsLike("El submarino ha dejado de funcionar",() -> nemo.sendInstructions("ddm"));
		assertTrue(nemo.getCapsuleState());
	}
	
	  private void assertThrowsLike(String expectedMessage, ThrowingRunnable throwingRunnable) {
			assertEquals(expectedMessage,assertThrows(RuntimeException.class, throwingRunnable).getMessage());
		}

}