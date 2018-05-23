package mainpck;
import commandes.Command;
import commandes.Copy;
import commandes.Cut;
import commandes.Delete;
import commandes.Insert;
import commandes.Paste;
import commandes.Selections;

public class IHM {
	public Command copier ;
	public Command cut; 
	public Command insert; 
	public Command select;
	public Command paste;
	public Command delete;
	
	public int debSel;
	public int endSel;
	private char tempoChar;
	
	private Buffer buffer;
	private Selection selector;
	
	
	public IHM() {
		PressePapier presse = new PressePapierImpl();
		this.selector = new SelectionImpl();
		this.buffer = new Buffer(presse, selector);
		
		setCommandCopy(new Copy(buffer));
		setCommandCut(new Cut(buffer));
		setCommandPaste(new Paste(buffer));
		setCommandSelections(new Selections(buffer,this));
		setCommandSupprimer(new Delete(buffer));
		setCommandInserer(new Insert(buffer, this));
	}
	
	public void setCommandCopy(Command c){
		this.copier =c;

	}
	public void setCommandPaste (Command c){
		this.paste=c;
	}

	public void setCommandCut(Command c){
		this.cut =c;
	}

	public void setCommandInserer(Command c){
		this.insert=c;
	}

	public void setCommandSelections(Command c){
		this.select=c;
	}
	
	public void setCommandSupprimer(Command c) {
		this.delete = c;
	}
	
	public void selectionner(int a, int b) {
		this.buffer.selections(a, b);
	}
	
	public void insert(char c) {
		this.tempoChar = c;
	}
	
	public String read() {
		return this.buffer.getText();
	}
	
	public int getStartSel(){
		return this.selector.getStart();
	}

	public int getEndSel(){
		return this.selector.getEnd();
	}
	
	public char getTempochar(){
		return this.tempoChar;
	}

}
