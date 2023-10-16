
public class Forward extends Command{
	static char type;
	
	public Forward() {
		type = 'f';
	}
	
	public char getType() {
		return type;
	}
	
	public void execute(Depth depth, Direction direction) {
		Submarine.coordinates = direction.moveForward();
	}
}
