package queue;

public class GoodBox extends Box {
	Object cargo;
	public GoodBox(Object cargo) {
		this.cargo = cargo;
	}
	
	Object getCargo() {
		return this.cargo;
	}
	
	boolean isEmpty() {
		return false;
	}
}
