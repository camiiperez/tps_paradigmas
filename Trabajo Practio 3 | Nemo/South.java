
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
	
	public void moveForward(Point position) {
		position.sumVector(0,-1);
	}
	
	public String getValue() {
		return value;
	}

}
