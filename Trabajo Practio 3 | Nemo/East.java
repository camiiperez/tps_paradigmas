
public class East extends Direction {
	static String value;
	
	public East() {
		value = "East";
	}
	
	public Direction turnRight() {
		return new South();
	}

	public Direction turnLeft() {
		return new North();
	}
	
	public int[] moveForward() {
		int yCoordinates = Submarine.coordinates[1];
		int xCoordinates = Submarine.coordinates[0] + 1;
		return new int[] {xCoordinates,yCoordinates};
	}
	
	public String getValue() {
		return value;
	}
}

