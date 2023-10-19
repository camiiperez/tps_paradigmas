
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
	
	public void moveForward(Point position) {
		position.sumVector(-1,0);
	}
	
	public String getValue() {
		return value;
	}
}
