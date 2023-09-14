package queue;

public class Box {
	Object cargo;
	public Box(Object cargo) {
		this.cargo = cargo;
	}
	
	Object getCargo() {
		return this.cargo;
	}
	
	boolean isEmpty() {
		return false;
	}
}
