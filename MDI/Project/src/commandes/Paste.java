package commandes;
import mainpck.Buffer;

/**
 * @author Sophy
 *
 */
public class Paste implements Command {

	private Buffer buffer;
	
	/**
	 * Constructor
	 * @param b
	 */
	public Paste(Buffer b) {
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.paste();

	}

}
