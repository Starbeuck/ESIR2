
package commandes;

import mainpck.Buffer;

/**
 * @author Sophy and Solenn
 *
 */
public class Delete implements Command {

	private Buffer buffer;
	
	//constructor 
	public Delete(Buffer b) {
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.delete();
	}

}
