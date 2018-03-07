import java.util.ArrayList;
import java.util.Random;


public class Synchronize extends Thread{
	static Random indexer= new Random();
	ArrayList<Long> a= new ArrayList(); 
	
	
	synchronized public void deleteRandom() throws InterruptedException{
	
			while(a.size()==0){
				this.wait();
			}
			int index= indexer.nextInt(a.size());
			a.remove(index);
		
	}

	synchronized public void addRandom(){
	
			int index= indexer.nextInt(a.size()+1);
			a.add(index, currentThread().getId() );
			if(a.size()>0){
				this.notify();
			}
	}
	
	synchronized public void observe(){
			System.out.println("size"+ a.size());

		
	}
}
