
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class TypeOfGame {
public abstract char getKey();
	
	public abstract boolean isThereAnyWin(Linea game);
	
	public static List<TypeOfGame> gameModes
	= new ArrayList<>(Arrays.asList(new A(), new B(), new C()));
	
	public static TypeOfGame typeOfGameFor(char variantOfTriumph) {
		TypeOfGame typeOfGameToReturn = gameModes.stream()
	            .filter(typeOfGame -> typeOfGame.getKey() == variantOfTriumph)
	            .findFirst()
	            .orElse(null);
	    return typeOfGameToReturn;
	}
}