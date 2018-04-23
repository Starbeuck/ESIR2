package commandes;

import mainpck.Buffer;
import mainpck.IHM;

/**
 * @author Sophy and Solenn 
 *
 */
public class Selections implements Command {
	//parameters
	private Buffer buffer;
	private int debut;
	private int fin;

	//constructor
	public Selections (Buffer b, int deb, int end){
		this.buffer = b;
		this.debut=deb; 
		this.fin=end;
	}
	
	@Override
	public void execute() {
		this.buffer.selections(debut, fin);
		
	}
}
