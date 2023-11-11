
public class BlueToken extends Token {
	
	public BlueToken(int x,int y,Linea game) {
		this.value = 'B';
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public char getValue() {
		return this.value;
	}

	public int countRedInDirection(Direction direction) {
		return 0;
	}

	public int countBlueInDirection(Direction direction) {
		return 1 + game.provideToken(x,y,direction).countBlueInDirection(direction);
	}

	public int countInDirection(Direction direction) {
		return this.countBlueInDirection(direction);
	}
}
