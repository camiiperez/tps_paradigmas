
public abstract class Token {
	char value;
	int x;
	int y;
	Linea game;
	
	public abstract char getValue();
	
	public abstract int countRedInDirection(Direction direction);
	
	public abstract int countBlueInDirection(Direction direction);
	
	public abstract int countInDirection(Direction direction);	
}
