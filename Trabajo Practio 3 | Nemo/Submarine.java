import java.util.ArrayList;
import java.util.Collections;

public class Submarine {
	static Point position;
	static Depth depth;
	static Direction direction;
	public static ArrayList<Depth> depthHistory;
//	"North","East","South","West";	

	
	public Submarine(int xValue, int yValue, String directionSearched) { 
	    depthHistory = new ArrayList<>(Collections.singletonList(new OnSurface()));
	    
	    position = new Point(xValue, yValue);
	    depth = depthHistory.get(depthHistory.size() - 1);
	    direction = Direction.directionFor(directionSearched);
	}

	public Submarine sendInstructions(String commandsToFollow) {
	        commandsToFollow.chars() 
	                .forEach(character -> {	          
	                    char command = (char) character;
	                    Command.commandFor(command).execute(depth,direction);	             	                 
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
