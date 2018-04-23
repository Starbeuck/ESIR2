package mainpck;
import java.util.ArrayList;
import java.util.List;

import commandes.Command;
import mementopck.Memento;
import mementopck.MementoCommandus;

/**
 * @author Sophy and Solenn
 *
 * @param <Static>
 */
public class Buffer {
	//attributes
	private String buffer;
	private PressePapier pressePapier; 
	private List<Memento> listActions; 
	private int actualState;
	public int startMacro, endMacro;
	private MementoCommandus commandus;
	private int debSel, finSel;
	
	
	//constructor
	public Buffer(PressePapier presse){
		this.buffer="";
		this.pressePapier = presse; 
		this.listActions=new ArrayList<Memento>();
		this.actualState=-1;
		this.startMacro=0;
		this.commandus= new MementoCommandus();
	}

	/**
	 * this method make the copy depening on the selection
	 */
	public void copy(){
		//if we selected something we add it to the presse papier
		if (debSel >=0 && finSel>debSel){
			pressePapier.write(buffer.substring(debSel, finSel));
		}
	}

	public void paste(){
		// 2 ca,s on a selectionné un texte qu'on remplace, ou on est juste positionné à un endroit et on colle 
		this.buffer= this.buffer.substring(0, debSel)+ pressePapier.read() +this.buffer.substring(finSel);

		//set cursor
		this.debSel+= this.pressePapier.read().length();
		this.finSel =debSel;

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}

	/**
	 * this method make a cut by copying and deleting the string selected
	 */
	public void cut(){	
		this.copy(); 
		this.buffer= this.buffer.substring(0, debSel) +this.buffer.substring(finSel);	

		//set cursor
		this.finSel=debSel;

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}

	/**
	 * This method is used to delete one character in the text depending on where is the cursor 
	 */
	public void delete() {
		if(debSel<finSel) {
			this.buffer = this.buffer.substring(0,  this.debSel)+ this.buffer.substring(this.debSel,this.buffer.length());
		} else {
			if (!this.buffer.equals("")){
			this.buffer = this.buffer.substring(0, this.debSel-1) + this.buffer.substring(this.finSel, this.buffer.length());
			this.debSel = this.debSel-1;	
			}
		}
		this.finSel=(this.debSel);

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}


	/** this method allow you to insert a character in the text
	 * @param c the character you want to insert 
	 */
	public void inserer(String c){
		this.buffer = this.buffer.substring(0, debSel) +c+buffer.substring(finSel);
		this.debSel++;
		this.finSel= debSel;

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}

	/** This method helps to select a text
	 * @param begin : the indice of the beginning of the selection
	 * @param end : the indice of the end of the selection
	 */
	public void selections(int begin, int end){
		//selection inverse
		if(begin > end) {
			int tmp = end;
			end = begin;
			begin = tmp;
		}

		if(begin < 0) begin = 0;
		if(begin > this.buffer.length()) begin = this.buffer.length();
		if(end > this.buffer.length()) end = this.buffer.length();

		this.debSel=begin;
		this.finSel=end;
	}


	/**
	 * @return the actual text in the buffer
	 */
	public String getText() {
		return this.buffer;
	}

	/**
	 * this method go back from one action
	 */
	public void undo(){
		if (actualState>0){
			actualState --;
			this.buffer=this.listActions.get(actualState).getState();
			this.debSel=this.buffer.length();
			this.finSel=debSel;
		}else{
			if (actualState==0){
			actualState --;
			this.buffer="";
			}
		}
	}

	/**
	 * this method reload the last action made
	 */
	public void redo(){
		if (actualState<listActions.size()-1){
			actualState ++;
			this.buffer=this.listActions.get(actualState).getState();
			this.debSel=this.buffer.length();
			this.finSel=debSel;
		}	
	}
	
	public int getActualState(){
		return this.actualState;
	}

	/**Get back the list of action to execute a macro
	 * @return the memnto that have the list of actions
	 */
	public MementoCommandus getMemCom(){
		return this.commandus;
	}

	public int getDebSel() {
		return debSel;
	}

	public int getFinSel() {
		return finSel;
	}
	
}
