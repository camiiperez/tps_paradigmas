public class Submarine {
	static Point position;
	static Depth depth;
	static Direction direction;
	
	public Submarine(int xValue, int yValue, String directionSearched) { 
	    position = new Point(xValue, yValue);
	    depth = new OnSurface();
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
