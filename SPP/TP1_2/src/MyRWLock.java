import java.util.concurrent.Semaphore;

public class MyRWLock {
	Semaphore read = new Semaphore(1);
	Semaphore write = new Semaphore(1); 

	public void lockRead() throws InterruptedException{
			read.acquire();
	}
	public void lockWrite() throws InterruptedException{
		write.acquire();
	}
	public void unlockRead(){
		read.release();
	}
	public void unlockWrite(){
		write.release();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
