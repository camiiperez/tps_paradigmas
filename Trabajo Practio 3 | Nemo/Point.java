
public class Point {
	static int[] coordinates = new int[2];
	
	public Point(int xValue, int yValue) {
		coordinates[0] = xValue;
		coordinates[1] = yValue;
	}

	public void sumVector(int xValue, int yValue) {		
		int x = coordinates[0] += xValue;
		int y = coordinates[1] += yValue;
		
		Submarine.setPosition(new Point(x,y));
	}
	
	public int[] getCoordinates() {
		return coordinates;
	}
	
}
