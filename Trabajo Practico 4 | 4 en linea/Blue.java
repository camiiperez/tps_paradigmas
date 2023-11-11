
public class Blue extends Turn {
	
	public void changeTurn() {
		Linea.setTurn(new Red());	
	}

	
	protected void shouldBluePlay() {		
	}

	
	protected void shouldRedPlay() {
		throw new RuntimeException(itsNotYourTurnMessage);
		
	}

}
