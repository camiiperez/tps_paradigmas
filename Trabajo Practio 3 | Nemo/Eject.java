
public class Eject extends Command{
	static char type;
	
	public Eject() {
		type = 'm';
	}
	
	public char getType() {
		return type;
	}
	
	public void execute(Depth depth, Direction direction) {
		depth.releaseCapsule();
	}

}
