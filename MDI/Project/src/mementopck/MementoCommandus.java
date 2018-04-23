package mementopck;

import java.util.ArrayList;
import java.util.List;

import commandes.Command;

/**
 * @author Sophy
 *
 */
public class MementoCommandus {
	
	//parameters
	private List<Command> listCommandus;
	private boolean recording =false;
	
	/**
	 * Constructor
	 */
	public MementoCommandus(){
		this.listCommandus= new ArrayList<Command> ();
	}
	
	
	/**
	 * if we launch the macro then this change the state of it
	 */
	public void record(){
		recording=!recording;
	}
	
	/**
	 * @param c : the command we will add to the list
	 */
	public void addCommand(Command c){
		this.listCommandus.add(c);
	}
	
	/**
	 * @return  if we are recording or not
	 */
	public boolean getStatusMacro(){
		return recording;
	}
	
	/**
	 * @return the list of commands 
	 */
	public List<Command> getListComm(){
		return this.listCommandus;
	}
	
	/**
	 * reset the list of command after recording
	 */
	public void resetListCommandus(){
		this.listCommandus.clear();
	}
}
