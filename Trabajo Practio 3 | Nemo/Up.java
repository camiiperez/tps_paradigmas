
public class Up extends Command{
	static char type;
	
	public Up() {
		type = 'u';
	}
	
	public char getType() {
		return type;
	}

	public void execute(Depth depth, Direction direction) {
		Submarine.depth = depth.ascend();
	}

}
