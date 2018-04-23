package commandes;
import mainpck.Buffer;

public class Paste implements Command {

	private Buffer buffer;
	
	public Paste(Buffer b) {
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.paste();

	}

}
