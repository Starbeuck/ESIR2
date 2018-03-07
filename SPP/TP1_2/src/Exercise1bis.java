import java.util.concurrent.locks.ReentrantReadWriteLock;
public class Exercise1bis extends Thread {
	static ReentrantReadWriteLock myLockbis = new ReentrantReadWriteLock();
	public void run(){
		long counterbis=0;
		for(int i=0; i<= 1000; i++){
			MyRWLock cacabis = new MyRWLock();
			try {
				cacabis.lockWrite();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				counterbis=Exercise1.counter; 
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cacabis.unlockRead();
			if(i%200==0){
				System.out.println(counterbis);
			}
		}

	}
}
