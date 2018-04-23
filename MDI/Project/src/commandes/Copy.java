package commandes;
import mainpck.Buffer;

public class Copy implements Command {

	private Buffer buffer;
	
	public Copy(Buffer b) {
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.copy();

	}

}
