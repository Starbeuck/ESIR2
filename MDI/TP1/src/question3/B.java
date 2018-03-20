package question3;

import java.util.ArrayList;

public class B {

	private A ra;

	public B() {
	}

	public A getA() {
		return (ra);
	}

	public void setA(A a) {
		this.ra = a;
	}

	public void addA(A a) {
		if (a != null) {
			if (!a.getArray().contains(this)) {
				if (ra != null)
					ra.remove(this);
				this.setA(a);
				ra.getArray().add(this);
			}
		}
	}

}
