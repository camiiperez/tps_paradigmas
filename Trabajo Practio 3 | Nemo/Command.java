
public abstract class Command {
	public abstract char getType();

	public abstract void execute(Depth depth, Direction direction);
}
