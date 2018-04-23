package mementopck;

/**
 * @author Sophy
 *
 */
public class Memento {
	private String state; 
	
	/** 
	 * Constructor
	 * @param s
	 */
	public Memento (String s){
		this.state =s; 
	}
	
	
	/**
	 * @return the state of the memento (string of the command)
	 */
	public String getState(){
		return this.state;
	}
}
