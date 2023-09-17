package queue;

public class ToxicBox extends Box {
	
	public static String QueueIsEmpty = "Queue is empty";

	public ToxicBox() {
	}

	boolean isEmpty() {
		return true;
	}

	Object getCargo() {
		throw new Error(QueueIsEmpty); 
	}
}
