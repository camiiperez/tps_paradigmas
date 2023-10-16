
public class BeyondMinusOne extends Depth{
	int value;
	
	public BeyondMinusOne() {
		Submarine.depthHistory.add(this);
		value = -(Submarine.depthHistory.size() - 1);
	}

	public Depth ascend() {
		Submarine.depthHistory.remove(Submarine.depthHistory.size() - 1);
		return Submarine.depthHistory.get(Submarine.depthHistory.size() - 1);
	}
	
	public Depth descend() {
		return new BeyondMinusOne();
	}

	public void releaseCapsule() {
		throw new RuntimeException("El submarino ha dejado de funcionar");
	}
	
	public int getValue() {
		return value;
	}

}