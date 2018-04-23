package commandes;
import mainpck.Buffer;
import mainpck.IHM;

public class Insert implements Command {

	private Buffer buffer;
	private IHM ihm;
	
	public Insert(Buffer b, IHM ihm) {
		this.buffer = b;
		this.ihm = ihm;
	}
	
	@Override
	public void execute() {
		this.buffer.inserer(this.ihm.getTempochar());
	}

}
