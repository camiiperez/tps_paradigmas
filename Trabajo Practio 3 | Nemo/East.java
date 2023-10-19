
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
	
	public void moveForward(Point position) {
		position.sumVector(1,0);
	}
	
	public String getValue() {
		return value;
	}
}

