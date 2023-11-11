
public class RedToken extends Token {
	
	public RedToken(int x,int y,Linea game) {
		this.value = 'R';
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public char getValue() {
		return this.value;
	}

	public int countRedInDirection(Direction direction) {
		return 1 + game.provideToken(x,y,direction).countRedInDirection(direction);
	}

	public int countBlueInDirection(Direction direction) {
		return 0;
	}
	
	public int countInDirection(Direction direction) {
		return this.countRedInDirection(direction);
	}

}
