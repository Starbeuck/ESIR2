package commandes;
import mainpck.Buffer;

/**
 * @author Sophy
 *
 */
public class Copy implements Command {

	private Buffer buffer;

	/**
	 * Constructor
	 * @param b
	 */
	public Copy(Buffer b) {
		this.buffer = b;
	}

	@Override
	public void execute() {
		this.buffer.copy();

	}

}
