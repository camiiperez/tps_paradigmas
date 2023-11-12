
public abstract class GameState {
	public static final String itsNotYourTurnMessage = "It's not your turn!";
	
	public abstract void changeTurn();

	protected abstract void playRedAt(int columnToPlay, Linea game);

	protected abstract void playBlueAt(int columnToPlay, Linea game);

	protected abstract GameEndedWithWinner winner();

	protected abstract void whosTheWinner();

	protected abstract GameEndedWithTie tied();
}
