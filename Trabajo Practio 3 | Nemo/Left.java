
public class Left extends Command{
	static char type;
	
	public Left() {
		type = 'l';
	}
	
	public char getType() {
		return type;
	}
	
	public void execute(Depth depth, Direction direction) {
		Submarine.setDirection(direction.turnLeft());
	}

}
