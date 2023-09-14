package queue;

public class ToxicBox extends Box {
	public ToxicBox() {
	}
	
	Object getCargo() {
		throw new Error("Queue is empty"); 
	}

	Object isEmpty() {
		return true;
	}
	
}
