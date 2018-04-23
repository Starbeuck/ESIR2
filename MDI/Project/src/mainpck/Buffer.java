package mainpck;
import java.util.ArrayList;
import java.util.List;

import commandes.Command;
import mementopck.Memento;

public class Buffer<Static> {

	private String buffer;
	private PressePapier pressePapier; 
	private Selection selection; 
	private List<Memento> listActions; 
	private int actualState;

	public Buffer(PressePapier presse, Selection selection){
		this.buffer="";
		this.pressePapier = presse; 
		this.selection = selection;
		this.listActions=new ArrayList<Memento>();
		this.actualState=0;
	}

	public void copy(){
		//si on a selectionné quelque chose
		if (selection.getStart() >0 && selection.getEnd()>selection.getStart()){
			// on récupère la string de start à end et on l'écrit dans le presse papier
			pressePapier.write(buffer.substring(this.selection.getStart(), this.selection.getEnd()));
		}
	}

	public void paste(){
		// 2 ca,s on a selectionné un texte qu'on remplace, ou on est juste positionné à un endroit et on colle 
		this.buffer= this.buffer.substring(0, selection.getStart())+ pressePapier.read() +this.buffer.substring(selection.getEnd());

		//set cursor
		this.selection.setStart(this.selection.getStart() + this.pressePapier.read().length());
		this.selection.setEnd(this.selection.getStart());

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}

	public void cut(){	
		this.copy(); 
		this.buffer= this.buffer.substring(0, selection.getStart()) +this.buffer.substring(selection.getEnd());	

		//set cursor
		this.selection.setEnd(this.selection.getStart());

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}

	public void delete() {
		if(this.selection.getStart()<this.selection.getEnd()) {
			this.buffer = this.buffer.substring(0,  this.selection.getStart())+ this.buffer.substring(this.selection.getEnd(),this.buffer.length());
		} else {
			this.buffer = this.buffer.substring(0, this.selection.getStart()-1) + this.buffer.substring(this.selection.getEnd(), this.buffer.length());
			this.selection.setStart(this.selection.getStart()-1);	
		}
		this.selection.setEnd(this.selection.getStart());

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}

	//pour écrire, caractère par caractère
	public void inserer(char c){
		this.buffer = this.buffer.substring(0, selection.getStart()) +c+buffer.substring(selection.getEnd());
		this.selection.setStart(selection.getStart()+1);
		this.selection.setEnd(this.selection.getStart());

		this.listActions.add(new Memento (this.buffer));
		this.actualState++;
	}

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

		selection.setStart(begin);
		selection.setEnd(end);
	}

	private static void save(){
		//into a file ? 
	}

	public String getText() {
		return this.buffer;
	}

	public void undo(){
		if (actualState>0){
			actualState --;
			this.buffer=this.listActions.get(actualState).getState();
		}
	}

	public void redo(){
		if (actualState<listActions.size()-2){
			actualState ++;
			this.buffer=this.listActions.get(actualState).getState();
		}

	}
}
