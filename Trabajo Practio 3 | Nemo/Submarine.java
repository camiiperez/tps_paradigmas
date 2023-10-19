import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Submarine {
	static Point position;
	static Depth depth;
	static Direction direction;
	public static ArrayList<Depth> depthHistory;
	public static ArrayList<Command> commands; 
//	"North","East","South","West";	

	
	public Submarine(/*int xValue, int yValue, String diretzioneee*/) {
	    position = new Point(0, 0);
	    commands = new ArrayList<>(Arrays.asList(new Up(), new Down(), new Right(), new Left(), new Forward(), new Eject()));
	    depthHistory = new ArrayList<>(Collections.singletonList(new OnSurface()));
	    depth = depthHistory.get(depthHistory.size() - 1);
	    direction = new North();
	}
		
	
	public Submarine sendInstructions(String commandsToFollow) {
	        commandsToFollow.chars() 
	                .forEach(character -> {	          
	                    char command = (char) character; 
	                    commands.stream()
	                    .filter(comando -> comando.getType() == command)
	                    .forEach(comando -> comando.execute(depth,direction));
	                    
	                });
	                return this;
	    }
	


	public static int[] getPosition() {
		return position.getCoordinates();
	}
	
	public static Depth getDepth() {
		return depth;
	}
	
	public static Direction getDirection() {
		return direction;
	}
}
