
public class GameEndedWithWinner extends GameState {
	public static final String theresAWinnerYouCannotPlayMessage = "There's a winner, you can't play!";
	String whosTheWinner;
	
	public GameEndedWithWinner(String whosTheWinner) {
		this.whosTheWinner = whosTheWinner;
	}

	public void changeTurn() {
	}

	protected void playRedAt(int columnToPlay, Linea game) {
		throw new RuntimeException(theresAWinnerYouCannotPlayMessage);
	}

	protected void playBlueAt(int columnToPlay, Linea game) {
		throw new RuntimeException(theresAWinnerYouCannotPlayMessage);
	}

	protected GameEndedWithWinner winner() {
		return null;
	}
	protected GameEndedWithTie tied() {
		return null;
	}
	
	public void whosTheWinner() {
		throw new RuntimeException(this.whosTheWinner);
	}

}
