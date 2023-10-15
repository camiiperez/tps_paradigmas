
public abstract class Command {
	public abstract void ascend();

	public abstract void descend();
	
	public abstract void turnRight();

	public abstract void turnLeft();
	
	public abstract void moveForward();
	
	public abstract void releaseCapsule();

	public abstract char getType();
}
