package commandes;
import java.util.ArrayList;
import java.util.List;

import mainpck.Buffer;

public class Macro implements Command {
	private List<Command> listCommd ;
	private Buffer buffer;
	
	
	public Macro(Buffer b){
		this.listCommd= new ArrayList<Command>();
		this.buffer=b;
	}

	@Override
	public void execute() {
		for(int i=0; i<listCommd.size();i++){
			listCommd.get(i).execute();
		}

	}

}
