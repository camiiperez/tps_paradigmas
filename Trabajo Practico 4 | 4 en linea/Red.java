
public class Red extends Turn {

	public void changeTurn() {
		Linea.setTurn(new Blue());	
	}

	
	protected void shouldBluePlay() {
		throw new RuntimeException(itsNotYourTurnMessage);
	}

	
	protected void shouldRedPlay() {
	}
	
}
