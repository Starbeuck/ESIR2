package mainpck;
import commandes.Command;
import commandes.Copy;
import commandes.Cut;
import commandes.Delete;
import commandes.Insert;
import commandes.Paste;
import commandes.Selections;


/**
 * @author Sophy & Solenn
 *
 */
public class IHM {
	//parameters
	public Command copier ;
	public Command cut; 
	public Command insert; 
	public Command selection;
	public Command paste;
	public Command delete;

	
	public int debSel;
	public int endSel;
	
	private Buffer buffer;

	
	//constructor
	public IHM() {
		PressePapier presse = new PressePapierImpl();
		this.buffer = new Buffer(presse);
		
		setCommandCopy(new Copy(buffer));
		setCommandCut(new Cut(buffer));
		setCommandPaste(new Paste(buffer));
		setCommandSupprimer(new Delete(buffer));
		setCommandInserer(new Insert(buffer,""));
		setCommandSelections(new Selections (this.buffer,0,0));
	
	}
	
	/**
	 * @param c : the command we will set the parameter with
	 */
	public void setCommandCopy(Command c){
		this.copier =c;
	}

	/**
	 * @param c : the command we will set the parameter with
	 */
	public void setCommandPaste (Command c){
		this.paste=c;
	}

	/**
	 * @param c : the command we will set the parameter with
	 */
	public void setCommandCut(Command c){
		this.cut =c;
	}

	/**
	 * @param c : the command we will set the parameter with
	 */
	public void setCommandInserer(Command c){
		this.insert=c;
	}

	/**
	 * @param c : the command we will set the parameter with
	 */
	public void setCommandSelections(Command c){
		this.selection=c;
	}
	
	/**
	 * @param c : the command we will set the parameter with
	 */
	public void setCommandSupprimer(Command c) {
		this.delete = c;
	}
	
	/**
	 * @param a: the begin of the selection
	 * @param b: the end of the selection
	 */
	public void selectionner(int a, int b) {
		this.buffer.selections(a, b);
		Command select= new Selections(this.buffer,a,b);
		setCommandSelections(select);
		
	}
	
	/**
	 * @param c the character we need to insert 
	 */
	public void insert(char c) {
		this.insert= new Insert(buffer,c+"");
	}
	
	/**
	 * @return the text in the buffer
	 */
	public String read() {
		return this.buffer.getText();
	}
	
	/**
	 * @return the biginning of the selection
	 */
	public int getStartSel(){
		return this.buffer.getDebSel();
	}

	/**
	 * @return the end of the selection
	 */
	public int getEndSel(){
		return this.buffer.getFinSel();
	}
	
	
	/**
	 * @param t: the character we initialis the insert command with
	 */
	public void setTempoChar(char t){
		this.insert=new Insert(buffer,t+"");
	}

	/**
	 * @return the text of the buffer
	 */
	@SuppressWarnings("rawtypes")
	public Buffer getbuffer() {
		return this.buffer;
	}
}
