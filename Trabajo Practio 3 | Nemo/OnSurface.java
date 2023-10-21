
public class OnSurface extends Depth{
	int value;
	
	public OnSurface() {
		depthHistory.clear();
		depthHistory.add(this);	
	}
	
	public Depth ascend() {
		return this;
	}

	public Depth descend() {	
		return new MinusOne();
	}

	public void releaseCapsule() {
		Submarine.setCapsuleState(false);
	}
	
	public int getValue() {
		return 0;
	}

}
