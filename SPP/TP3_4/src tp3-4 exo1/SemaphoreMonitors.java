
public class SemaphoreMonitors implements SemaphoreInterface {
	int counter; 
	int waiting ;
	
	//CONSTRUCTORS
	//constructor with number of permits
	public void SemaphoreMonitors(int count){
		this.counter=count;
		this.waiting=0;
	}
	
	//constructor without number of permits
	public void SemaphoreMonitors(){
		this.counter=0;
		this.waiting=0;
	}
	
	
	@Override
	synchronized public void up() {
		this.counter++; 
		notify();
		if (waiting>0){
		waiting--;
		}
	}

	@Override
	synchronized public void down()  {
		while (this.counter==0){
			try {
				waiting++;
				wait();
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.counter--;

	}

	@Override
	synchronized public int releaseAll() {
		int temp =waiting; 
		counter+=waiting; 
		notifyAll(); 
		waiting=0;
	
		return temp;

	}

}
