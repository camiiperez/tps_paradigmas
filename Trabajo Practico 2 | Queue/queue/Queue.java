package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	public List<Object> queues = new ArrayList<>();
  
	public boolean isEmpty() {
		if (queues.isEmpty())
		return true;
		else {
			return false;
		}
	}

	public Queue add( Object  cargo ) {
		queues.add(cargo);
		return this;
	}

	public Object take() {
		if(queues.isEmpty()) {
			throw new Error("Queue is empty");
		}
		return queues.remove(0);
		
	}

	public Object head() {
		if (!queues.isEmpty()) {
	        return queues.get(0); // Obtiene el primer elemento de la lista
	    } 
	    throw new Error("Queue is empty");
	}

	public int size() {
		return queues.size();
	}

}

