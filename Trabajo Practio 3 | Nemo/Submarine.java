import java.util.ArrayList;

public class Submarine {
	static int[] coordinates = new int[2];
	static Depth depth;
	static Direction direction;
	public static ArrayList<Depth> depthHistory;
	public static ArrayList<Command> commands; 
//	"North","East","South","West";	

	
	public Submarine() {
		coordinates[0] = 0;
		coordinates[1] = 0;
		commands = new ArrayList<Command>();
		commands.add(new Up());
		commands.add(new Down());
		commands.add(new Right());
		commands.add(new Left());
		commands.add(new Forward());
		commands.add(new Eject());
		
		depthHistory = new ArrayList<Depth>();
		depthHistory.add(new OnSurface());
		
		depth = depthHistory.get(depthHistory.size() - 1);
		direction = new North();
	}		
	
	public Submarine sendInstructions(String commandsToFollow) {
	        commandsToFollow.chars() 
	                .forEach(character -> {	          
	                    char command = (char) character; 
	                    commands.stream()
	                    .filter(comando -> comando.getType() == command)
	                    .forEach(comando -> comando.execute(depth,direction));
	                    
	                });
	                return this;
	    }
	


	public static int[] getCoordinates() {
		return coordinates;
	}
	
	public static Depth getDepth() {
		return depth;
	}
	
	public static Direction getDirection() {
		return direction;
	}
}
