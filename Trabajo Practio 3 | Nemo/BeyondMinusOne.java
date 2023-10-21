
public class BeyondMinusOne extends Depth{
	public static String elSubmarinoHaDejadoDeFuncionarError = "El submarino ha dejado de funcionar";
	int value;
	
	public BeyondMinusOne() {
		depthHistory.add(this);
		value = -(depthHistory.size() - 1);
	}

	public Depth ascend() {
		depthHistory.remove(depthHistory.size() - 1);
		return depthHistory.get(depthHistory.size() - 1);
	}
	
	public Depth descend() {
		return new BeyondMinusOne();
	}

	public void releaseCapsule() {
		throw new RuntimeException(elSubmarinoHaDejadoDeFuncionarError);
	}
	
	public int getValue() {
		return value;
	}

}