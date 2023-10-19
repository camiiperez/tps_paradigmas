
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

	public void moveForward(Point position) {
		position.sumVector(0,1);
	}
	
	public String getValue() {
		return value;
	}

	

}
