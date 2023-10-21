import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.Test;

public class SubmarineTest {
	
	private static final String north = "North";
	private static final String east = "East";
	private static final String south = "South";
	private static final String west = "West";
	
	private static final int minusFour = -4;
	private static final int minusThree = -3;
	private static final int minusTwo = -2;
	private static final int minusOne = -1;
	private static final int zero = 0;
	private static final int one = 1;
	private static final int three = 3;
	private static final int five = 5;
	
	private static final int[] zeroMinusTwo = new int[]{zero, minusTwo};
	private static final int[] minusOneZero = new int[]{minusOne, zero};
	private static final int[] minusOneMinusOne = new int[]{minusOne, minusOne};
	private static final int[] zeroMinusOne = new int[]{zero, minusOne};
	private static final int[] oneZero = new int[]{one, zero};
	private static final int[] oneOne = new int[]{one, one};
	private static final int[] zeroThree = new int[]{zero, three};
	private static final int[] zeroOne = new int[]{zero, one};
	private static final int[] threeFive = new int[]{three, five};
	private static final int[] zeroZero = new int[]{zero, zero};

	@Test public void test00AssertsNemoSpawnsInOriginAndNorth(){
		assertArrayEquals(zeroZero, submarineWithPosAndDirection(zero, zero, north).getPosition().getCoordinates());
        assertEquals(zero, submarineWithPosAndDirection(zero, zero, north).getDepth().getValue());
        assertEquals(north, submarineWithPosAndDirection(zero, zero, north).getDirection().getValue());
	}
		
	@Test public void test01AssertsNemoSpawnsNotInAnotherPositionAndDirection() {
		assertArrayEquals(threeFive, submarineWithPosAndDirection(three, five, south).getPosition().getCoordinates());
        assertEquals(zero, submarineWithPosAndDirection(three, five, south).getDepth().getValue());
        assertEquals(south, submarineWithPosAndDirection(three, five, south).getDirection().getValue());
	}
	
	@Test public void test02NemoShouldNotMoveWhenNotAskedTo() {
		assertArrayEquals(zeroZero, submarineWithPosAndDirection(zero, zero, north).sendInstructions("").getPosition().getCoordinates());
        assertEquals(zero, submarineWithPosAndDirection(zero, zero, north).sendInstructions("").getDepth().getValue());
        assertEquals(north, submarineWithPosAndDirection(zero, zero, north).sendInstructions("").getDirection().getValue());
	}	

	@Test public void test03AssertsNemoDescendsOnce() {
		assertEquals(minusOne,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("d").getDepth().getValue());
	}
	
	@Test public void test04AssertsNemoDescendsMoreThanOnce() {
		assertEquals(minusFour,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("dddd").getDepth().getValue());
	}
	
	@Test public void test05AssertsNemoAscends() {
		assertEquals(minusOne,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("d").getDepth().getValue());
		assertEquals(zero,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("u").getDepth().getValue());
	}
	
	@Test public void test06AssertsNemoAscendsWhenDeeper() {
		assertEquals(minusTwo,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("dddduu").getDepth().getValue());
	}
	
	@Test public void test07NemoDoesntAscendWhenOnSurface(){
		assertEquals(zero,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("u").getDepth().getValue());
	}
	
	@Test public void test08AssertsNemoAscendsWhenDeeperButDoesntAscendAtSurface() {
	assertEquals(zero,
			submarineWithPosAndDirection(zero, zero, north).sendInstructions("dddduuuuuu").getDepth().getValue());
	}
	
	@Test public void test09AssertNemoMovesRightOnce() {
		assertEquals(east,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("r").getDirection().getValue());
	}
	
	@Test public void test10AssertsNemoMovesRightMultipleTimes() {
		assertEquals(west,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("rrr").getDirection().getValue());
	}
	
	@Test public void test11AssertsNemoMovesRightAtDepth() {
		assertEquals(south,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("dddrr").getDirection().getValue());
		
	}
	
	@Test public void test12AssertsNemoMovesLeftOnce() {
		assertEquals(west,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("l").getDirection().getValue());
		
	}
	
	@Test public void test13AssertsNemoMovesLeftMultipleTimes() {
		assertEquals(east,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("lll").getDirection().getValue());
	}
	
	@Test public void test14AssertsNemoMovesLeftAtDepth() {
		assertEquals(south,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("dddll").getDirection().getValue());
	}
	
	@Test public void test15AssertNemoMovesForwardOnce() {
			assertArrayEquals(zeroOne, 
					submarineWithPosAndDirection(zero, zero, north).sendInstructions("f").getPosition().getCoordinates());
	}
	
	@Test public void test16AssertsNemoMovesForwardMultipleTimes() {
		assertArrayEquals(zeroThree, 
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("fff").getPosition().getCoordinates());
	}
	
	@Test public void test17AssertsNemoMovesForwardAtDepth() {
		assertArrayEquals(zeroThree,
				submarineWithPosAndDirection(zero, zero, north).sendInstructions("dddfff").getPosition().getCoordinates());
	}
	
	@Test public void test18AssertsNemoCanMoveForwardInDirection() {
		Submarine nemo = submarineWithPosAndDirection(zero, zero, north);
		assertArrayEquals(zeroOne,nemo.sendInstructions("f").getPosition().getCoordinates());
		assertArrayEquals(oneOne,nemo.sendInstructions("rf").getPosition().getCoordinates());
		assertArrayEquals(oneZero,nemo.sendInstructions("rf").getPosition().getCoordinates());
		assertArrayEquals(zeroZero,nemo.sendInstructions("rf").getPosition().getCoordinates());
		
	}
	@Test public void test19AssertsNemoCanMoveForwardInDirectionAndDepth() {
		Submarine nemo = submarineWithPosAndDirection(zero,zero, north);
		
		assertArrayEquals(zeroOne,nemo.sendInstructions("dddf").getPosition().getCoordinates());
		assertArrayEquals(oneOne,nemo.sendInstructions("rf").getPosition().getCoordinates());
		assertArrayEquals(oneZero,nemo.sendInstructions("rf").getPosition().getCoordinates());
		assertArrayEquals(zeroZero,nemo.sendInstructions("rf").getPosition().getCoordinates());
		
	}

	@Test public void test20AssertNemoCanMoveBackwardsInDirection() {
		Submarine nemo = submarineWithPosAndDirection(zero, zero, north);
		assertArrayEquals(zeroMinusOne,nemo.sendInstructions("rrf").getPosition().getCoordinates());
		assertArrayEquals(minusOneMinusOne,nemo.sendInstructions("rf").getPosition().getCoordinates());
		assertArrayEquals(minusOneZero,nemo.sendInstructions("rf").getPosition().getCoordinates());
		assertArrayEquals(zeroZero,nemo.sendInstructions("rf").getPosition().getCoordinates());
	}
	
	@Test public void test21AssertsNemoSpawnsWithCapsuleInside() {
		assertTrue(submarineWithPosAndDirection(zero, zero, north).getCapsuleState());
	}
	
	@Test public void test22AssertsNemoReleasesCapsuleAtSurface() {
		assertFalse(submarineWithPosAndDirection(zero, zero, north).sendInstructions("m").getCapsuleState());
	}
	
	@Test public void test23AssertsNemoReleasesCapsuleAtMinusOneDepth() {
		assertFalse(submarineWithPosAndDirection(zero, zero, north).sendInstructions("dm").getCapsuleState());
	}
	
	@Test public void test24AssertsNemoCannotReleaseCapsuleAtLessThanMinusOneDepthAndStopsWorking() {
		assertThrowsLike(BeyondMinusOne.elSubmarinoHaDejadoDeFuncionarError,
				() -> submarineWithPosAndDirection(zero, zero, north).sendInstructions("ddm"));
		assertTrue(submarineWithPosAndDirection(zero, zero, north).getCapsuleState());
	}
	
	@Test public void test25AssertNemoCanReceiveMultipleCommands() {
		Submarine nemo = submarineWithPosAndDirection(zero, zero, north);
		nemo.sendInstructions("udddrrlrff");
		assertEquals(minusThree,nemo.getDepth().getValue());
		assertArrayEquals(zeroMinusTwo,nemo.getPosition().getCoordinates());
		assertEquals(south,nemo.getDirection().getValue());
	}
	
	@Test public void test26AssertNemoCanReceiveMultipleCommandsAndStopsWorkingWhenCapsuleIsReleasedAtWrongDepth() {
		Submarine nemo = submarineWithPosAndDirection(zero, zero, north);
		nemo.sendInstructions("udddrrlrff");
		assertEquals(minusThree,nemo.getDepth().getValue());
		assertArrayEquals(zeroMinusTwo,nemo.getPosition().getCoordinates());
		assertEquals(south,nemo.getDirection().getValue());
		assertThrowsLike(BeyondMinusOne.elSubmarinoHaDejadoDeFuncionarError,() -> nemo.sendInstructions("ddm"));
		assertTrue(nemo.getCapsuleState());
	}
	
	private Submarine submarineWithPosAndDirection(int x, int y, String direction) {
		Submarine nemo = new Submarine(x,y,direction);
		return nemo;	
	}
	
	  private void assertThrowsLike(String expectedMessage, ThrowingRunnable throwingRunnable) {
			assertEquals(expectedMessage,assertThrows(RuntimeException.class, throwingRunnable).getMessage());
		}

}