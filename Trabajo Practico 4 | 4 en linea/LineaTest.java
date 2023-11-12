import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class LineaTest {
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
		assertFalse(game.redsTurn());
	}
	
	@Test
	public void test03AfterBluePlaysItsRedTurn() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		game.playBlueAt(1);
		
		assertTrue(game.redsTurn());
		assertFalse(game.bluesTurn());
	}
	
	@Test
	public void test04RedCannotPlayTwoTimesInARow() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		assertThrowsLike(GameState.itsNotYourTurnMessage, () -> game.playRedAt(1));
		assertTrue(game.bluesTurn());
	}
	
	@Test
	public void test05BlueCannotPlayTwoTimesInARow() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		game.playRedAt(1);
		
		game.playBlueAt(1);
		
		assertThrowsLike(GameState.itsNotYourTurnMessage, () -> game.playBlueAt(1));
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
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(1), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test12BlueWinsVerticallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		game.playRedAt(3);
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(2), () -> game.playRedAt(2));
	}
	
	@Test
	public void test13RedWinsHorizontallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		
		redFillsFirstRowFromColumnOneToThreeAndBlueFillsSecondRowFromColumnOneToThree(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(4), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test14BlueWinsHorizontallyInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		blueFillsFirstRowFromColumnTwoToFour(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(5), () -> game.playRedAt(2));
	}
	
	@Test
	public void test15RedDoesntWinWithPositiveSlopeDiagonalInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsColumnsOneToThreeDiagonally(game);
		
		assertsGameHasntFinishedAndIsPossibleToKeepPlayingForRed(game, 4, 2);
	}
	
	@Test
	public void test16RedDoesntWinWithNegativeSlopeDiagonalInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsColumnsSixToFourDiagonallyBlueFillsColumnsFiveToThreeDiagonally(game);
		
		assertsGameHasntFinishedAndIsPossibleToKeepPlayingForRed(game, 3, 2);
	}
	
	@Test
	public void test17BlueDoesntWinWithPositiveSlopeDiagonalInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		blueFillsColumnsOneToThreeDiagonally(game);
		
		assertsGameHasntFinishedAndIsPosibbleToKeepPlayingForBlue(game, 4, 2);
	}
	
	@Test
	public void test18BlueDoesntWinWithNegativeSlopeDiagonalInGameModeA() {
		Linea game = newFourInLineGameWith(6, 7, 'A');
		redFillsColumnsSixToFourDiagonallyBlueFillsColumnsFiveToThreeDiagonally(game);
		
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(2);
		
		assertsGameHasntFinishedAndIsPosibbleToKeepPlayingForBlue(game, 2, 2);
		
	}
	
	@Test
	public void test19GameFinishesIfBoardIsFullInGameModeA() {
		Linea game = newFourInLineGameWith(2,2,'A');
		fillsBoardAndAssertsGameWithTieMessagesAreThrownGameFinishesAndItIsNotPossibleToKeepPlaying(game);
	}
	
	@Test
	public void test20RedDoesntWinVerticallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		assertsGameHasntFinishedAndIsPossibleToKeepPlayingForRed(game, 1, 2);

	}
	
	@Test
	public void test21BlueDoesntWinVerticallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		game.playRedAt(3);
		
		assertsGameHasntFinishedAndIsPosibbleToKeepPlayingForBlue(game, 2, 2);
	}
	
	@Test
	public void test22RedDoesntWinHorizontallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsFirstRowFromColumnOneToThreeAndBlueFillsSecondRowFromColumnOneToThree(game);
		
		assertsGameHasntFinishedAndIsPossibleToKeepPlayingForRed(game, 4, 3);
	}
	
	@Test
	public void test23BlueDoesntWinHorizontallyInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		blueFillsFirstRowFromColumnTwoToFour(game);
		
		assertsGameHasntFinishedAndIsPosibbleToKeepPlayingForBlue(game, 5, 2);
	}
	
	@Test
	public void test24RedWinsWithPositiveSlopeDiagonalInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		redFillsColumnsOneToThreeDiagonally(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(4), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test25RedWinsWithNegativeSlopeDiagonalInGameModeB() {
		Linea game = newFourInLineGameWith(6, 7, 'B');
		redFillsColumnsSixToFourDiagonallyBlueFillsColumnsFiveToThreeDiagonally(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(3), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test26BlueWinsPositiveSlopeDiagonalInGameModeB() {
		Linea game = newFourInLineGameWith(6,7,'B');
		blueFillsColumnsOneToThreeDiagonally(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(4), () -> game.playRedAt(2));
	}
	
	@Test
	public void test27BlueWinsNegativeSlopeDiagonalInGameModeB() {
		Linea game = newFourInLineGameWith(6, 7, 'B');
		redFillsColumnsSixToFourDiagonallyBlueFillsColumnsFiveToThreeDiagonally(game);
		
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(2);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(2), () -> game.playRedAt(2));
	}
	
	@Test
	public void test28GameFinishesIfBoardIsFullInGameModeB() {
		Linea game = newFourInLineGameWith(2,2,'B');
		fillsBoardAndAssertsGameWithTieMessagesAreThrownGameFinishesAndItIsNotPossibleToKeepPlaying(game);
	}
	
	@Test
	public void test29RedWinsVerticallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(1), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test30BlueWinsVerticallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsThreeRowsOnColumnOneAndBlueFillsThreeRowsInColumnTwo(game);
		
		game.playRedAt(3);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(2), () -> game.playRedAt(2));
	}
	
	@Test
	public void test31RedWinsHorizontallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsFirstRowFromColumnOneToThreeAndBlueFillsSecondRowFromColumnOneToThree(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(4), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test32BlueWinsHorizontallyInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		blueFillsFirstRowFromColumnTwoToFour(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(5), () -> game.playRedAt(2));
	}
	
	@Test
	public void test33RedWinsPositiveSlopeDiagonalInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		redFillsColumnsOneToThreeDiagonally(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(4), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test34RedWinsNegativeSlopeDiagonalInGameModeC() {
		Linea game = newFourInLineGameWith(6, 7, 'C');
		redFillsColumnsSixToFourDiagonallyBlueFillsColumnsFiveToThreeDiagonally(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, RedTurn.redIsTheWinnerMessage, () -> game.playRedAt(3), () -> game.playBlueAt(2));
	}
	
	@Test
	public void test35BlueWinsPositiveSlopeDiagonalInGameModeC() {
		Linea game = newFourInLineGameWith(6,7,'C');
		blueFillsColumnsOneToThreeDiagonally(game);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(4), () -> game.playRedAt(2));
	}
	
	@Test
	public void test36BlueWinsNegativeSlopeDiagonalInGameModeC() {
		Linea game = newFourInLineGameWith(6, 7, 'C');
		redFillsColumnsSixToFourDiagonallyBlueFillsColumnsFiveToThreeDiagonally(game);
		
		game.playRedAt(1);
		
		game.playBlueAt(2);
		
		game.playRedAt(2);
		
		assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying
		(game, BlueTurn.blueIsTheWinnerMessage, () -> game.playBlueAt(2), () -> game.playRedAt(2));
	}
	
	@Test
	public void test37GameFinishesIfBoardIsFullInGameModeC() {
		Linea game = newFourInLineGameWith(2,2,'C');
		fillsBoardAndAssertsGameWithTieMessagesAreThrownGameFinishesAndItIsNotPossibleToKeepPlaying(game);
	}
	
	private Linea newFourInLineGameWith(int width, int height, char typeOfGame) {
		Linea game = new Linea(width,height,typeOfGame);
		return game;
	}
	
	private void fillsBoardAndAssertsGameWithTieMessagesAreThrownGameFinishesAndItIsNotPossibleToKeepPlaying(Linea game) {
		game.playRedAt(1);	
		
		game.playBlueAt(2);
		
		game.playRedAt(1);
		
		assertFalse(game.finished());
		
		assertThrowsLike(GameEndedWithTie.theGameEndedWithATieMessage,() ->game.playBlueAt(2));
		
		assertTrue(game.finished());
		
		assertThrowsLike(GameEndedWithTie.gameEndedWithATieYouCannotPlayMessage, () -> game.playRedAt(2));
	}
	
	private void assertsGameHasntFinishedAndIsPosibbleToKeepPlayingForBlue(Linea game, int apparentWinnerMove, int nextMove) {
		assertFalse(game.finished());
		game.playBlueAt(apparentWinnerMove);
		assertFalse(game.finished());
		
		game.playRedAt(nextMove);
	}
	
	private void assertsGameHasntFinishedAndIsPossibleToKeepPlayingForRed(Linea game, int apparentWinnerMove, int nextMove) {
		assertFalse(game.finished());
		game.playRedAt(apparentWinnerMove);	
		assertFalse(game.finished());
		
		game.playBlueAt(nextMove);
	}
	
	private void assertsGameFinishesExceptionsAreThrownAndItsNotPossibleToKeepPlaying(Linea game, String messageExpected, ThrowingRunnable winnersPlay, ThrowingRunnable illegalPlay) {
		assertFalse(game.finished());
		
		assertThrowsLike(messageExpected,winnersPlay);
		
		assertTrue(game.finished());
		
		assertThrowsLike(GameEndedWithWinner.theresAWinnerYouCannotPlayMessage,illegalPlay);
	}
	
	private void redFillsColumnsSixToFourDiagonallyBlueFillsColumnsFiveToThreeDiagonally(Linea game) {
		game.playRedAt(6);
		
		game.playBlueAt(5);
		
		game.playRedAt(5);
		
		game.playBlueAt(4);
		
		game.playRedAt(3);
		
		game.playBlueAt(4);
		
		game.playRedAt(4);
		
		game.playBlueAt(2);
		
		game.playRedAt(3);
		
		game.playBlueAt(3);
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