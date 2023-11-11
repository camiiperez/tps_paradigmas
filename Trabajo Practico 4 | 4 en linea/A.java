
public class A extends TypeOfGame {
	char key;
	
	public A() {
		key = 'A';
	}
	
	public char getKey() {
		return this.key;	
	}

	public boolean isThereAnyWin(Linea game) {
		return game.verticalWin() 
				|| game.horizontalWin();
	}
	
}