import java.util.ArrayList;
import java.util.Arrays;

public abstract class Direction {
	
	public static ArrayList<Direction> directions
	= new ArrayList<>(Arrays.asList(new North(), new East(), new South(), new West()));
	
	public abstract Direction turnRight();
	
	public abstract Direction turnLeft();
	
	public abstract void moveForward(Point position);

	public abstract String getValue();

	public static Direction directionFor(String directionSearched) {
		
		 Direction directionToReturn = directions.stream()
		            .filter(direction -> direction.getValue().equals(directionSearched))
		            .findFirst()
		            .orElse(null);  
		    return directionToReturn;
	}
}
