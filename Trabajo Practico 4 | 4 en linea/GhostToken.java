
public class GhostToken extends Token {
	
	public GhostToken(int x,int y,Linea game) {
		this.value = 'G';
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
		return 0;
	}
	
	public int countInDirection(Direction direction) {
		return 0;
	}
}
