
public class Down extends Command{
	static char type;
	
	public Down() {
		type = 'd';
	}
	
	public char getType() {
		return type;
	}
	
	public void execute(Depth depth, Direction direction) {
		Submarine.depth = depth.descend();
	}

}
