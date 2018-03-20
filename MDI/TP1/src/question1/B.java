package question1;

public class B {

	private A a;

	public void setA(A a) {
		this.a = a;
	}

	public A getA() {
		return a;
	}

	public void addA(A a) {
		if (a != null) {
			if (a.getB() != null) { 
				a.getB().setA(null); 
			}
			this.setA(a);
			a.setB(this);
		}
	}

}
