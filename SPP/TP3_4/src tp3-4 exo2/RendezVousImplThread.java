import java.util.Random;
import java.util.concurrent.Exchanger;

public class RendezVousImplThread extends Thread{
	public String name; 
	public String message; 
	public Exchanger exchanger; 
	
	//CONSTRUCTOR
	public RendezVousImplThread (String nom, String texte, Exchanger exchangeur){
		this.name=nom;
		this.message=texte; 
		this.exchanger=exchangeur;
	}
	
	
	public void run(){
		
		for(int i=0; i<3; i++){
			System.out.println("Iteration: " +i+" "+ this.name +" "+this.message);
			System.out.println("Iteration: " +i+" "+ this.name +" going to sleep.");
			Random rand = new Random(); 
			int value = rand.nextInt(5001); 
			try {
				Thread.sleep((long) value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			System.out.println("Iteration: " +i+" "+ this.name +" ready to exchange");
			try {
				this.message= (String) (this.exchanger).exchange(message);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Iteration: " +i+" "+ this.name +" exchange completed");
		
		}
	}
	
	

	public static void main(String[] args) {
		// creation of two threads 
		Exchanger<String> exchanger = new Exchanger<String>();
		Thread t1= new RendezVousImplThread("Alice", "Ping", exchanger);
		
		Thread t2=new RendezVousImplThread("Bob", "Pong", exchanger); 
		t1.start();
		t2.start();

	}

}
