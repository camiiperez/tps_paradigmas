
public class C extends TypeOfGame {
	char key;
	
	public C() {
		key = 'C';
	}
	
	public char getKey() {
		return this.key;
	}

	public boolean isThereAnyWin(Linea game) {
		return game.verticalWin() || game.horizontalWin() || game.diagonalWin();
	}
}
