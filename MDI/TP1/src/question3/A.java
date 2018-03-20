package question3;

import java.util.ArrayList;

public class A {
	private ArrayList<B> rb;

	public A() {
		rb = new ArrayList<B>();
	}

	public ArrayList<B> getArray() {
		return (rb);
	}

	public void remove(B b) {
		rb.remove(b);
	}

	public void addB(B b) {
		if (!rb.contains(b)) {
			if (b.getA() != null)
				b.getA().remove(b);
			b.setA(this);
			rb.add(b);
		}
	}
}
