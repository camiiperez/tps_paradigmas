
public class MinusOne extends Depth{
	int value;
	
	public MinusOne() {
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
		Submarine.setCapsuleState(false);
	}
	
	public int getValue() {
		return -1;
	}

}
