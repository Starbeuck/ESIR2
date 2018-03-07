import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Exercise1 extends Thread{
	
	static long counter = 0; 
	static ReentrantReadWriteLock myLock  = new ReentrantReadWriteLock();
	public void run(){
		for(int i=1; i<=1000; i++){
			MyRWLock caca = new MyRWLock();
			try {
				caca.lockWrite();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			try {
				this.sleep(1);
				Exercise1.counter++ ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			caca.unlockWrite();

		}
	}

	public static void main(String[] args) throws InterruptedException {
		long time = System.currentTimeMillis();
		// TODO Auto-generated method stub
		Thread [] readers = new Thread [5]; 
		for (int i=0; i<5; i++){
			readers[i]=new Exercise1();
			readers[i].run();
		}
		for(int i=0; i<readers.length; i++){
			readers[i].join();
		}
		
		Thread [] readers2 = new Thread [15]; 
		for (int a=0; a<15; a++){
			readers2[a]=new Exercise1bis();
			readers2[a].start();
		}
		for(int i=0; i<readers2.length; i++){
			readers2[i].join();
		}
		long temps = System.currentTimeMillis()-time;
		System.out.println("temps écoulé" + temps);
	}

	}


