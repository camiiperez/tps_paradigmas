package queue;

public class GoodBox extends Box {
	Object cargo;
	
	public GoodBox(Object cargo) {
		this.cargo = cargo;
	}
	
	boolean isEmpty() {
		return false;
	}
	
	Object getCargo() {
		return this.cargo;
	}
}
