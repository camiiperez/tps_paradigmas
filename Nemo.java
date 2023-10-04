import java.util.ArrayList;
import java.util.List;

public class Nemo {
	private static int[] coordinates = new int[2];
	private static int depth;
	private static String direction;
	private List<String> orientations = new ArrayList<>(List.of("North","East","South","West"));	
	private int contador = 0;
	
	public Nemo() {
		coordinates[0] = 0;
		coordinates[1] = 0;
		depth = 0;
		direction = "North";
	}

	public static int[] getCoordinates() {
		return coordinates;
	}
	
	public static int getDepth() {
		return depth;
	}
	
	public static String getDirection() {
		return direction;
	}

	public Nemo move(String command) {
		for (int i = 0; i < command.length(); i++) {
			
	    char comando = command.charAt(i);
		
	    if (comando == ' ') {
	    	if (i == command.length() - 1) {
	    	return this;	
	    	}
			
		}
		
		if(comando == 'd') {
			depth -= 1;
			if (i == command.length() - 1) {
		    	return this;	
		    	}
		}
		
		if (comando == 'u') {
			if (depth < 0) {	
			depth += 1;
			}
		}
		if (comando == 'r') {
			if (contador == 3) {
				contador = 0;
				direction = orientations.get(contador);
				if (i == command.length() - 1) {
			    	return this;	
			    	}
				continue;
			}
			
			contador += 1;
			direction = orientations.get(contador);
			if (i == command.length() - 1) {
		    	return this;	
		    	}
			
		}
		
		if (comando == 'l') {
			if (contador == 0) {
				contador = 3;
				direction = orientations.get(contador);
				if (i == command.length() - 1) {
			    	return this;	
			    	}
				continue;
			}
			
			contador -= 1;
			direction = orientations.get(contador);
			
			if (i == command.length() - 1) {
		    	return this;	
		    	}
			
		}

	}
		return null;
	
}
}
