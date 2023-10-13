
public class North extends Direction {
	static String value;
	public North() {
		value = "North";
	}

	public void turnRight() {
		Submarine.direction = new East();
	}

	public void turnLeft() {
		Submarine.direction = new West();
	}

	public void moveForward() {
		Submarine.coordinates[1] += 1;
	}
	
	public String getValue() {
		return value;
	}

}
