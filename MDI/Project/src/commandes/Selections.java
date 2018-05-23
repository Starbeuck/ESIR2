package commandes;

import mainpck.Buffer;
import mainpck.IHM;

public class Selections implements Command {
	
	private IHM ihm; 
	private Buffer buffer;

	public Selections (Buffer b, IHM ih){
		this.ihm=ih;
		this.buffer = b;
	}
	
	@Override
	public void execute() {
		this.buffer.selections(ihm.getStartSel(), ihm.getEndSel());
		this.ihm.debSel = this.ihm.getStartSel();
		this.ihm.endSel = this.ihm.getEndSel();

	}
}
