
public class ThreadObserver extends Thread {
	public void run(){
		while(true) {
			try {
				sleep(50);
				ThreadAdder.synchronizergene.observe();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
