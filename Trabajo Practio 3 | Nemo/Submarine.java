public class Submarine {
	private static int[] coordinates = new int[2];
	private static int depth;
	private static String direction;
//	"North","East","South","West";	

	
	public Submarine() {
		coordinates[0] = 0;
		coordinates[1] = 0;
		depth = 0;
		direction = "North";
	}
	
	public Submarine sendInstructions(String commandsToFollow) {
		for (int i = 0; i < commandsToFollow.length(); i++) {
		    char comando = commandsToFollow.charAt(i);
		    
		    if (comando == 'd') {
		    	descend();
		    }
		    
		    if (comando == 'u') {
		    	ascend();
		    }
		    
		    if (comando == 'l') {
		    	turnLeft();
		    }
		    
		    if (comando == 'r') {
		    	turnRight();
		    }
		    
		    if (comando == 'f') {
		    	moveForward();
		    	
		    }
		    
		    if (comando == 'm') {
		    	releaseCapsule();
		    	
		    }
		}
		return this;
	}
	private void turnRight() {
		if (direction == "North") {
			direction = "East";
		}
		else if (direction == "East") {
			direction = "South";
		}
		else if(direction == "South") {
			direction = "West";
		}
		else if(direction == "West") {
			direction = "North";
		}
	}
	
	private void turnLeft() {
		if (direction == "North") {
			direction = "West";
		}
		else if (direction == "West") {
			direction = "South";
		}
		else if (direction == "South") {
			direction = "East";
		}
		else if (direction == "East") {
			direction = "North";
		}
	}
	
	private void ascend() {
		if (depth < 0) {
			depth += 1;
		}
	}
	
	private void descend() {
		depth -= 1;
	}
	
	private void moveForward() {
		if (direction == "North") {
			coordinates[1] += 1;
		}
		else if (direction == "South") {
			coordinates[1] -= 1;
		}
		else if(direction == "East") {
			coordinates[0] += 1;
		}
		else if(direction == "West") {
			coordinates[0] -= 1;
		}
	}
	
	Object releaseCapsule() {
		if (depth >= -1) {
			return "La capsula se ha liberado";
		}
		else if(depth < 1) {
			throw new RuntimeException("El submarino ha dejado de funcionar");
		}
		return null;
	}
	

	public static int[] getCoordinates() {
		return coordinates;
	}
	
	public static int getDepth() {
		return depth;
	}
	
	public static String getDirection() {
		return direction;
	}
}
