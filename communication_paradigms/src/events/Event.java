package events;

public class Event {	
	
	protected EventState value;
	
	public Event(EventState initial){
		value = initial;
	}
	
	public synchronized void await(EventState s) throws InterruptedException {
		while(value != s){
			wait();
		}
	}
	
	public synchronized void set(){
		value = EventState.UP;
	}
	
	public synchronized void reset() {
		value = EventState.DOWN;
		notifyAll();
	}
	
	public synchronized void toggle(){
		if(value == EventState.DOWN)
			value = EventState.UP;
		else
			value = EventState.DOWN;
		notifyAll();
	}
	
	public synchronized EventState getState(){
		return value;
	}

}
