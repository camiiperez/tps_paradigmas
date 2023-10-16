
public class OnSurface extends Depth{
	int value;
	
	public OnSurface() {
		value = 0;
	}
	
	public Depth ascend() {
		return this;
	}

	public Depth descend() {	
		return new MinusOne();
	}

	public void releaseCapsule() {
	}
	
	public int getValue() {
		return value;
	}

}
