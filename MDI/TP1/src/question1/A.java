package question1;

public class A {

	private B b;

	public void setB(B b) {
		this.b = b;
	}

	public B getB() {
		return b;
	}

	public void addB(B b) {
		if (b != null) {
			if (b.getA() != null) {
				b.getA().setB(null);
			}
			this.setB(b);
			b.setA(this);
		}
	}
}
