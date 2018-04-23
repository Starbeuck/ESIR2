package mainpck;
public class SelectionImpl implements Selection {
	int debut;
	int fin;
	
	//constructeur
	public SelectionImpl(int start, int end){
		this.debut=start;
		this.fin =end;
	}
	
	public SelectionImpl(){
		this.debut= 0; 
		this.fin= 0 ;
	}
	
	@Override
	public void setStart(int begin) {
		this.debut=begin;
	}

	@Override
	public void setEnd(int end) {
		this.fin=end;

	}

	@Override
	public int getEnd() {
		// TODO Auto-generated method stub
		return this.fin;
	}

	@Override
	public int getStart() {
		// TODO Auto-generated method stub
		return this.debut;
	}
}
