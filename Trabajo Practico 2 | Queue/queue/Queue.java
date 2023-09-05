package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	public List<Object> queues = new ArrayList<>();
  
	public boolean isEmpty() {
		return queues.isEmpty();
		}

	public Queue add( Object  element ) {
		queues.add(element);
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
