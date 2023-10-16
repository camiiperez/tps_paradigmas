
public class West extends Direction {
	static String value;
	
	public West() {
		value = "West";
	}
	
	public Direction turnRight() {
		return new North();
	}

	public Direction turnLeft() {
		return new South();
	}
	
	public int[] moveForward() {
		int yCoordinates = Submarine.coordinates[1];
		int xCoordinates = Submarine.coordinates[0] - 1;
		return new int[] {xCoordinates,yCoordinates};
	}
	
	public String getValue() {
		return value;
	}
}
