
public class GameEndedWithTie extends GameState {

	public static final String gameEndedWithATieYouCannotPlayMessage = "Game ended with tie, you can't play!";
	public static final String theGameEndedWithATieMessage = "The game has ended with a tie!";
	
	public void changeTurn() {		
	}

	protected void playRedAt(int columnToPlay, Linea game) {
		throw new RuntimeException(gameEndedWithATieYouCannotPlayMessage);
	}

	protected void playBlueAt(int columnToPlay, Linea game) {
		throw new RuntimeException(gameEndedWithATieYouCannotPlayMessage);
	}

	protected GameEndedWithWinner winner() {
		return null;
	}
	protected GameEndedWithTie tied() {
		return null;
	}

	protected void whosTheWinner() {
		throw new RuntimeException(theGameEndedWithATieMessage);
	}

}
