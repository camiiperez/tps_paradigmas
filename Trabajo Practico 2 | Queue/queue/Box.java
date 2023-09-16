package queue;

public abstract class Box {
	
	public Box() {	
	}
	
	abstract Object getCargo();
	
	abstract boolean isEmpty();
}
