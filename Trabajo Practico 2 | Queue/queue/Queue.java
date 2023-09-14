package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	public List<Box> queue; 

	public Queue(){
		queue= new ArrayList<Box>();
		queue.add(new ToxicBox());
	}
	
	public boolean isEmpty() {
		return (boolean) queue.get(0).isEmpty();
		}

	public Queue add( Object  cargo ) {
		Box lastObject = queue.remove(this.size());
		queue.add(new GoodBox(cargo));
		queue.add(lastObject);
		return this;
	}

	public Object take() {
		this.head();
		Box firstElement = queue.remove(0);
		return firstElement.getCargo();
	}

	public Object head() {
		return queue.get(0).getCargo();
	}

	public int size() {
		return queue.size() - 1;
	}

}


