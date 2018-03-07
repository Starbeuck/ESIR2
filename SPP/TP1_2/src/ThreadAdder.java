import java.util.ArrayList;

public class ThreadAdder extends Thread{
	static Synchronize synchronizergene = new Synchronize();
	public void run(){
		for(int i=0; i<= 10000; i++){
		synchronizergene.addRandom();
		}
	}


	public static void main(String[] args) throws InterruptedException {
		long time = System.currentTimeMillis();
		// TODO Auto-generated method stub
		Thread [] insertThread = new Thread [10]; 
		for (int i=0; i<insertThread.length; i++){
			insertThread[i]=new ThreadAdder();
			insertThread[i].start();
		}
		

		Thread [] deleteThread = new Thread [10]; 
		for (int a=0; a<10; a++){
			deleteThread[a]=new ThreadDeleter();
			deleteThread[a].start();
		}
		
		Thread obs= new ThreadObserver();
		obs.start();

		
		//calcul time
		long temps = System.currentTimeMillis()-time;
		System.out.println("temps écoulé" + temps);
		


	}

}


