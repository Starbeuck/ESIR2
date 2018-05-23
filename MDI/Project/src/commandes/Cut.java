package commandes;

import mainpck.Buffer;

public class Cut implements Command {

	private Buffer buffer;
	
	public Cut(Buffer b) {
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.cut();

	}

}
