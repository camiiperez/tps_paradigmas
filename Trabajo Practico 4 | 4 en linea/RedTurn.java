
public class RedTurn extends GameState {

	public static final String redIsTheWinnerMessage = "Red is the winner!";

	protected void playRedAt(int columnToPlay, Linea game) {
		game.placeRedToken(columnToPlay);
	}
	
	protected void playBlueAt(int columnToPlay, Linea game) {
		throw new RuntimeException(itsNotYourTurnMessage);		
	}
	
	public void changeTurn() {
		Linea.setGameState(new BlueTurn());	
	}

	protected GameEndedWithWinner winner() {
		return new GameEndedWithWinner(redIsTheWinnerMessage);
	}
	protected GameEndedWithTie tied() {
		return new GameEndedWithTie();
	}
	
	protected void whosTheWinner() {		
	}	
}
