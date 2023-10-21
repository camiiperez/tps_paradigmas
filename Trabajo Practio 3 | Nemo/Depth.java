import java.util.ArrayList;

public abstract class Depth {
	public static ArrayList<Depth> depthHistory =  new ArrayList<Depth>();
	
	public abstract Depth ascend();

	public abstract Depth descend();
	
	public abstract void releaseCapsule();

	public abstract int getValue();

	public static Depth actualDepth() {
		return depthHistory.get(depthHistory.size() - 1);
	}
}
