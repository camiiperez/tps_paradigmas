import java.util.ArrayList;
import java.util.Collections;

public abstract class Depth {
	public static ArrayList<Depth> depthHistory 
	= new ArrayList<>(Collections.singletonList(new OnSurface()));
	
	public abstract Depth ascend();

	public abstract Depth descend();
	
	public abstract void releaseCapsule();

	public abstract int getValue();
}
