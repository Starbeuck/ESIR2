package commandes;

import mainpck.Buffer;

/**
 * @author Sophy
 *
 */
public class Cut implements Command {

	private Buffer buffer;
	
	/**
	 *  Constructor
	 * @param b
	 */
	public Cut(Buffer b) {
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.cut();

	}

}
