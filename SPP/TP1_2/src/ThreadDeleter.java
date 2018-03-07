
public class ThreadDeleter extends Thread {


	public void run(){
		for(int i=0; i<= 10000; i++){
			try {
				ThreadAdder.synchronizergene.deleteRandom();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
