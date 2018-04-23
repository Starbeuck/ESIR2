package commandes;
import mainpck.Buffer;
import mainpck.IHM;

/**
 * @author Sophy & Solenn
 *
 */
public class Insert implements Command {

	//parameters
	private Buffer buffer;
	private String carAjout;
	
	
	/**
	 * Constructor
	 * @param b :the buffer
	 * @param ihm : the ihm
	 * @param car : the character to add
	 */
	public Insert(Buffer b,  String car) {
		this.buffer = b;
		this.carAjout=car;
	}
	
	/* (non-Javadoc)
	 * @see commandes.Command#execute()
	 */
	@Override
	public void execute() {
		this.buffer.inserer(this.carAjout);
	}

}
