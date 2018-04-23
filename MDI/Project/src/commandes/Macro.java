package commandes;
import java.util.ArrayList;
import java.util.List;

import mainpck.Buffer;

public class Macro implements Command {
	private int startMacro, endMacro;
	private boolean macroRecording = false;
	private Buffer buff; 


	public Macro(Buffer b){
		this.buff=b;

	}

	@Override
	public void execute() {
		if (!macroRecording){
			macroRecording=true;
			this.startMacro=buff.getActualState();
			buff.startMacro=buff.getActualState();
			System.out.println("début de la macro indice" + startMacro);
		}else {
			this.endMacro=buff.getActualState();
			buff.endMacro=buff.getActualState();
			macroRecording = false;
			System.out.println("fin de la macro indice" + endMacro);
		}
	}

	public int getStartMacro(){
		return this.startMacro;
	}
	
	public int getEndMacro(){
		return this.endMacro;
	}
}
