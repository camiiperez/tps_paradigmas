
public class West extends Direction {
	static String value;
	
	public West() {
		value = "West";
	}
	
	public void turnRight() {
		Submarine.direction = new North();
	}

	public void turnLeft() {
		Submarine.direction = new South();
	}

	public void moveForward() {
		Submarine.coordinates[0] -= 1;
	}
	
	public String getValue() {
		return value;
	}
}
