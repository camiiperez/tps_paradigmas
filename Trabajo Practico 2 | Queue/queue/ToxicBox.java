package queue;

public class ToxicBox extends Box {
	
	
	
	public ToxicBox(Object cargo) {
		super(cargo);
	}
	
	Object getCargo() {
		throw new Error("Queue is empty"); 
	}

	boolean isEmpty() {
		return true;
	}
	
}
