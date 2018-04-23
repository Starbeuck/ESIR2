
package commandes;

import mainpck.Buffer;

public class Delete implements Command {

	private Buffer buffer;
	
	public Delete(Buffer b) {
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.delete();

	}

}
