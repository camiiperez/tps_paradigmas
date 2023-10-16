
public class North extends Direction {
	static String value;
	public North() {
		value = "North";
	}

	public Direction turnRight() {
		return new East();
	}

	public Direction turnLeft() {
		return new West();
	}

	public int[] moveForward() {
		int yCoordinates = Submarine.coordinates[1] + 1;
		int xCoordinates = Submarine.coordinates[0];
		return new int[] {xCoordinates,yCoordinates};
	}
	
	public String getValue() {
		return value;
	}

}
