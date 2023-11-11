
public class B extends TypeOfGame {
	char key;
	
	public B() {
		key = 'B';
	}
	
	public char getKey() {
		return this.key;
	}

	public boolean isThereAnyWin(Linea game) {
		return game.diagonalWin();
	}
}

