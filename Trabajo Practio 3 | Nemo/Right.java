
public class Right extends Command{
	static char type;
	
	public Right() {
		type = 'r';
	}
	
	public char getType() {
		return type;
	}
	
	public void execute(Depth depth, Direction direction) {
		Submarine.setDirection(direction.turnRight());
	}

}
