package queue;

public class ToxicBox extends Box {
	
	public ToxicBox() {
	}

	boolean isEmpty() {
		return true;
	}

	Object getCargo() {
		throw new Error("Queue is empty"); 
	}
}
