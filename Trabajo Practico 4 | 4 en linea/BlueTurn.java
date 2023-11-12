
public class BlueTurn extends GameState {
	
	public static final String blueIsTheWinnerMessage = "Blue is the winner!";

	protected void playRedAt(int columnToPlay, Linea game) {
		throw new RuntimeException(itsNotYourTurnMessage);		
	}
	
	protected void playBlueAt(int columnToPlay, Linea game) {
		game.placeBlueToken(columnToPlay);
	}
	
	public void changeTurn() {
		Linea.setGameState(new RedTurn());	
	}

	protected GameEndedWithWinner winner() {
		return new GameEndedWithWinner(blueIsTheWinnerMessage);
	}

	protected void whosTheWinner() {
	}

	protected GameEndedWithTie tied() {
		return new GameEndedWithTie();
	}
}
