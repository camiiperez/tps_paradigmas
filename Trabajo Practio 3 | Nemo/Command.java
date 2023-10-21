import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {
	
	public static ArrayList<Command> commands = 
	new ArrayList<>(Arrays.asList(new Up(), new Down(), new Right(), new Left(), new Forward(), new Eject())); 
	
	public abstract char getType();

	public abstract void execute(Depth depth, Direction direction);

	public static Command commandFor(char command) {
		Command commandToReturn = 
				commands.stream().filter(comando -> comando.getType() == command).findFirst().orElse(null);
		return commandToReturn;
	}
}
