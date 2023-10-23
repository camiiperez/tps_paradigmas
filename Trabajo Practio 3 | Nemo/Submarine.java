public class Submarine {
	static Point position;
	static Depth depth;
	static Direction direction;
	static boolean isCapsuleInside;
	
	public Submarine(int xValue, int yValue, String directionSearched) { 
	
		position = new Point(xValue, yValue);
	    depth = new OnSurface();
	    direction = Direction.directionFor(directionSearched);
	    isCapsuleInside = true;
	}

	public Submarine sendInstructions(String commandsToFollow) {
	        commandsToFollow.chars() 
	                .forEach(character -> {	          
	                    char command = (char) character;
	                    Command.commandFor(command).execute(depth,direction);	             	                 
	                });
	                return this;
	    }
	
	public static Point getPosition() {
		return position;
	}
	
	public static void setPosition(Point point) {
		position = point;
	}
	
	public static boolean getCapsuleState() {
		return isCapsuleInside;
	}
	
	public static void setCapsuleState(boolean isItInside) {
		isCapsuleInside = isItInside;
	}
	
	public static Depth getDepth() {
		return depth;
	}
	public static void setDepth(Depth newDepth) {
		depth = newDepth;
	}
	
	public static Direction getDirection() {
		return direction;
	}
	
	public static void setDirection(Direction newDirection) {
		direction = newDirection;
	}
	
}
