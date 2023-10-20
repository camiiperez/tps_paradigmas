
public class OnSurface extends Depth{
	int value;
	int i = 0 ;
	
	public OnSurface() {
		depthHistory.clear();
		value = 0;	
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
		return value;
	}

}
