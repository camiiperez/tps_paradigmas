
public abstract class Turn {
	public static final String itsNotYourTurnMessage = "It's not your turn!";
	
	public abstract void changeTurn();

	protected abstract void shouldBluePlay();

	protected abstract void shouldRedPlay();
}
