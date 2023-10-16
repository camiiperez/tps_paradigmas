
public class South extends Direction {
	static String value;
	
	public South() {
		value = "South";
	}
	
	public Direction turnRight() {
		return new West();
	}

	public Direction turnLeft() {
		return new East();
	}
	
	public int[] moveForward() {
		int yCoordinates = Submarine.coordinates[1] - 1;
		int xCoordinates = Submarine.coordinates[0];
		return new int[] {xCoordinates,yCoordinates};
	}
	
	public String getValue() {
		return value;
	}

}
