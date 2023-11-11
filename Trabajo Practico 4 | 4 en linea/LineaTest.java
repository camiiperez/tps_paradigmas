import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class LineaTest {
	private static final char blue = 'B';
	private static final char red = 'R';

	@Test
    public void test00BoardStartsEmpty() {
        for (int column = 0; column < 6; column++) {
            assertTrue(newFourInLineGameWith(6, 7, 'A').board.get(column).isEmpty());
        }
    }
	
	@Test
	public void test01RedStartsPlaying() {
		assertTrue(newFourInLineGameWith(6, 7, 'A').redsTurn());
	}
	
	@Test
	public void test02AfterRedPlaysItsBluesTurn() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		assertTrue(game.bluesTurn());
	}
	
	@Test
	public void test03AfterBluePlaysItsRedTurn() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		game.playBlueAt(1);
		
		assertTrue(game.redsTurn());
	}
	
	@Test
	public void test04RedCannotPlayTwoTimesInARow() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		assertThrowsLike(Turn.itsNotYourTurnMessage, () -> game.playRedAt(1));
		assertTrue(game.bluesTurn());
	}
	
	@Test
	public void test05BlueCannotPlayTwoTimesInARow() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		game.playBlueAt(1);
		
		assertThrowsLike(Turn.itsNotYourTurnMessage, () -> game.playBlueAt(1));
		assertTrue(game.redsTurn());
	}
	
	@Test
	public void test06RedAndBlueTokenFallOnTheRightSpot() {
		assertsTokensFallInTheRightSpot(newFourInLineGameWith(6,2,'A'));
	}
	
	@Test
	public void test07RedCannotPlayOutsideTheWidthOfTheBoard() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		assertsTheseMovesAreInvalidWithRed(game, -1, 0, 7);
		
		assertTrue(game.redsTurn());
	}
	
	@Test
	public void test08BlueCannotPlayOutsideTheWidthOfTheBoard() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		assertsTheseMovesAreInvalidWithBlue(game, -1, 0, 7);
		
		assertTrue(game.bluesTurn());
	}
	
	@Test
	public void test09RedCannotPlayOutsideTheHeightOfTheBoard() {
		Linea game = newFourInLineGameWith(6,2,'A');
		game.playRedAt(1);
		
		game.playBlueAt(1);
		
		assertThrowsLike(Linea.fullColumnMessage, () -> game.playRedAt(1));
	}
	
	@Test
	public void test10BlueCannotPlayOutsideTheHeightOfTheBoard() {
		Linea game = newFourInLineGameWith(6,2,'A');
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(2);
		
		assertThrowsLike(Linea.fullColumnMessage, () -> game.playBlueAt(2));
	}
			
	
	
	@Test
	public void test11RedWinsVerticallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		assertFalse(game.finished());
		
		game.playRedAt(1);
		
		assertTrue(game.finished());
		assertEquals(red + Linea.isTheWinner,game.winner());
	}
	
	@Test
	public void test12BlueWinsVerticallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		game.playRedAt(3);
		
		assertFalse(game.finished());
		
		game.playBlueAt(2);
		
		assertTrue(game.finished());
		assertEquals(blue + Linea.isTheWinner,game.winner());
		
	}
	
	@Test
	public void test13RedWinsHorizontallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsFirstRowFromColumnOneToThreeAndBlueFillsSecondRowFromColumnOneToThree(game);
		
		assertFalse(game.finished());
		
		game.playRedAt(4);
		
		assertTrue(game.finished());
		assertEquals(red + Linea.isTheWinner,game.winner());
	}
	
	@Test
	public void test14BlueWinsHorizontallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		blueFillsFirstRowFromColumnTwoToFour(game);
		
		assertFalse(game.finished());
		
		game.playBlueAt(5);
		
		assertTrue(game.finished());
		assertEquals(blue + Linea.isTheWinner,game.winner());
	}
	
	@Test
	public void test15RedDoesntWinDiagonallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsColumnsOneToThreeDiagonally(game);
		
		assertFalse(game.finished());
		game.playRedAt(4);
		assertFalse(game.finished());
		
		assertEquals(Linea.noWinnerYetMessage,game.winner());
		
	}
	
	@Test
	public void test16BlueDoesntWinDiagonallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		blueFillsColumnsOneToThreeDiagonally(game);
		
		assertFalse(game.finished());
		game.playBlueAt(4);
		assertFalse(game.finished());
		assertEquals(Linea.noWinnerYetMessage,game.winner());
	}
	
	@Test
	public void test17GameFinishesIfBoardIsFullInGameModeA() {
		Linea game = newFourInLineGameWith(2,2,'A');
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(1);
		
		assertFalse(game.finished());
		
		game.playBlueAt(2);
		
		assertTrue(game.finished());
		assertEquals(Linea.tieMessage,game.winner());
	}
	
	@Test
	public void test18RedDoesntWinVerticallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		assertFalse(game.finished());
		
		game.playRedAt(1);
		
		assertFalse(game.finished());
		assertEquals(Linea.noWinnerYetMessage,game.winner());
		
	}
	
	@Test
	public void test19BlueDoesntWinVerticallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		game.playRedAt(3);
		
		assertFalse(game.finished());
		
		game.playBlueAt(2);
		
		assertFalse(game.finished());
		assertEquals(Linea.noWinnerYetMessage,game.winner());
		
	}
	
	@Test
	public void test20RedDoesntWinHorizontallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsFirstRowFromColumnOneToThreeAndBlueFillsSecondRowFromColumnOneToThree(game);
		
		assertFalse(game.finished());
		
		game.playRedAt(4);
		
		assertFalse(game.finished());
		assertEquals(Linea.noWinnerYetMessage,game.winner());
		
	}
	
	@Test
	public void test21BlueDoesntWinHorizontallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		blueFillsFirstRowFromColumnTwoToFour(game);
		
		assertFalse(game.finished());
		
		game.playBlueAt(5);
		
		assertFalse(game.finished());
		assertEquals(Linea.noWinnerYetMessage,game.winner());
		
	}
	
	@Test
	public void test22RedWinsDiagonallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsColumnsOneToThreeDiagonally(game);
		
		assertFalse(game.finished());
		
		game.playRedAt(4);
		
		assertTrue(game.finished());
		assertEquals(red + Linea.isTheWinner,game.winner());
	}
	
	@Test
	public void test23BlueWinsDiagonallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		blueFillsColumnsOneToThreeDiagonally(game);
		
		assertFalse(game.finished());
		
		game.playBlueAt(4);
		
		assertTrue(game.finished());
		assertEquals(blue + Linea.isTheWinner,game.winner());
	}
	
	@Test
	public void test24GameFinishesIfBoardIsFullInGameModeB() {
		Linea game = newFourInLineGameWith(2,2,'B');
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(1);
		
		assertFalse(game.finished());
		
		game.playBlueAt(2);
		
		assertTrue(game.finished());
		assertEquals(Linea.tieMessage,game.winner());
	}
	
	@Test
	public void test25RedWinsVerticallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		assertFalse(game.finished());
		
		game.playRedAt(1);
		
		assertTrue(game.finished());
		assertEquals(red + Linea.isTheWinner,game.winner());
		
	}
	
	@Test
	public void test26BlueWinsVerticallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		game.playRedAt(3);
		
		assertFalse(game.finished());
		
		game.playBlueAt(2);
		
		assertTrue(game.finished());
		assertEquals(blue + Linea.isTheWinner,game.winner());
		
	}
	
	@Test
	public void test27RedWinsHorizontallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsFirstRowFromColumnOneToThreeAndBlueFillsSecondRowFromColumnOneToThree(game);
		
		assertFalse(game.finished());
		
		game.playRedAt(4);
		
		assertTrue(game.finished());
		assertEquals(red + Linea.isTheWinner,game.winner());
		
	}
	
	@Test
	public void test28BlueWinsHorizontallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		blueFillsFirstRowFromColumnTwoToFour(game);
		
		assertFalse(game.finished());
		
		game.playBlueAt(5);
		
		assertTrue(game.finished());
		assertEquals(blue + Linea.isTheWinner,game.winner());
	}
	
	@Test
	public void test29RedWinsDiagonallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsColumnsOneToThreeDiagonally(game);
		
		assertFalse(game.finished());
		game.playRedAt(4);
		assertTrue(game.finished());
		assertEquals(red + Linea.isTheWinner,game.winner());
		
	}
	
	@Test
	public void test30BlueWinsDiagonallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		blueFillsColumnsOneToThreeDiagonally(game);
		
		assertFalse(game.finished());
		
		game.playBlueAt(4);
		
		assertTrue(game.finished());
		assertEquals(blue + Linea.isTheWinner,game.winner());
		
	}
	
	@Test
	public void test31GameFinishesIfBoardIsFullInGameModeC() {
		Linea game = newFourInLineGameWith(2,2,'C');
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(1);
		
		assertFalse(game.finished());
		
		game.playBlueAt(2);
		
		assertTrue(game.finished());
		assertEquals(Linea.tieMessage,game.winner());
	}
	
	private Linea newFourInLineGameWith(int width, int height, char typeOfGame) {
		Linea game = new Linea(width,height,typeOfGame);
		return game;
	}
	
	private void blueFillsColumnsOneToThreeDiagonally(Linea game) {
		game.playRedAt(6);
		
		game.playBlueAt(1);
		
		game.playRedAt(2);
		
		game.playBlueAt(2);
		
		game.playRedAt(3);
		
		game.playBlueAt(4);
		
		game.playRedAt(3);
		
		game.playBlueAt(3);
		
		game.playRedAt(4);
		
		game.playBlueAt(5);
		
		game.playRedAt(4);
	}
	
	private void redFillsColumnsOneToThreeDiagonally(Linea game) {
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(2);
		
		game.playBlueAt(3);
		
		game.playRedAt(4);
		
		game.playBlueAt(3);
		
		game.playRedAt(3);
		
		game.playBlueAt(4);
		
		game.playRedAt(5);
		
		game.playBlueAt(4);
	}
	
	private void blueFillsFirstRowFromColumnTwoToFour(Linea game) {
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(1);
		
		game.playBlueAt(3);
		
		game.playRedAt(2);
		
		game.playBlueAt(4);
		
		game.playRedAt(3);
	}
	
	private void redFillsFirstRowFromColumnOneToThreeAndBlueFillsSecondRowFromColumnOneToThree(Linea game) {
		game.playRedAt(1);
		
		game.playBlueAt(1);
		
		game.playRedAt(2);
		
		game.playBlueAt(2);
		
		game.playRedAt(3);
		
		game.playBlueAt(3);
	}
	
	private void redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(Linea game) {
		game.playRedAt(1);
			
		game.playBlueAt(2);
		
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(1);
		
		game.playBlueAt(2);
	}
	
	private void assertsTokensFallInTheRightSpot(Linea game) {
		game.playRedAt(1);
		assertTrue(game.isThereARedToken(1,1));
		
		game.playBlueAt(2);
		assertTrue(game.isThereABlueToken(2,1));
		
		game.playRedAt(1);
		assertTrue(game.isThereARedToken(1,2));
		
		game.playBlueAt(6);
		assertTrue(game.isThereABlueToken(6,1));
	}
	
	private void assertsTheseMovesAreInvalidWithRed(Linea game, int firstMove, int secondMove, int thirdMove) {
		assertThrowsLike(Linea.invalidMoveMessage,() -> game.playRedAt(firstMove));
		assertThrowsLike(Linea.invalidMoveMessage,() -> game.playRedAt(secondMove));
		assertThrowsLike(Linea.invalidMoveMessage,() -> game.playRedAt(thirdMove));
	}
	
	private void assertsTheseMovesAreInvalidWithBlue(Linea game, int firstMove, int secondMove, int thirdMove) {
		assertThrowsLike(Linea.invalidMoveMessage,() -> game.playBlueAt(firstMove));
		assertThrowsLike(Linea.invalidMoveMessage,() -> game.playBlueAt(secondMove));
		assertThrowsLike(Linea.invalidMoveMessage,() -> game.playBlueAt(thirdMove));
	}
	
	private void assertThrowsLike( String errorMessage, ThrowingRunnable throwingRunnable) {
		assertEquals(errorMessage,
				assertThrows(RuntimeException.class, throwingRunnable).getMessage());
	}
	
	
}