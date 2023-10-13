
public class MinusOne extends Depth{
	int value;
	
	public MinusOne() {
		value = -1;
		Submarine.depthHistory.add(this);
	}
	
	public void ascend() {
		Submarine.depthHistory.remove(Submarine.depthHistory.size() - 1);
	}

	public Depth descend() {
		return new BeyondMinusOne();
	}

	public void releaseCapsule() {
	}
	
	public int getValue() {
		return value;
	}

}
