
public class South extends Direction {
	static String value;
	
	public South() {
		value = "South";
	}
	
	public void turnRight() {
		Submarine.direction = new West();
	}

	public void turnLeft() {
		Submarine.direction = new East();
	}

	public void moveForward() {
		Submarine.coordinates[1] -= 1;
	}
	
	public String getValue() {
		return value;
	}

}
