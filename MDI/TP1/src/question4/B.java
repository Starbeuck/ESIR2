package question4;

public class B {

	private A a;
	private boolean majA = false;
	
	public void setA (A a){
		this.a = a;
		if (!majA){
			majA = true;
			if (this.a != null){
				this.a.setB(this);
			}
			majA = false;
		}
	}
}
