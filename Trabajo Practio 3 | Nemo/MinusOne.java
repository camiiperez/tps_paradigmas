
public class MinusOne extends Depth{
	int value;
	
	public MinusOne() {
		value = -1;
		depthHistory.add(this);
	}
	
	public Depth ascend() {
		depthHistory.remove(depthHistory.size() - 1);
		return depthHistory.get(depthHistory.size() - 1);
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
