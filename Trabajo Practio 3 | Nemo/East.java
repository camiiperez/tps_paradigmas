
public class East extends Direction {
	static String value;
	
	public East() {
		value = "East";
	}
	
	public void turnRight() {
		Submarine.direction = new South();
	}

	public void turnLeft() {
		Submarine.direction = new North();
	}

	public void moveForward() {
		Submarine.coordinates[0] += 1;
	}
	
	public String getValue() {
		return value;
	}
}

