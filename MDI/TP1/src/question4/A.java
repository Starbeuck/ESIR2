package question4;

public class A {

	private B b;
	private boolean majB = false;
	
	public void setB (B b){
		this.b = b;
		if (!majB){
			majB = true;
			if (this.b != null){
					this.b.setA(this);
			}
			majB = false;
		}
	}
	
}
