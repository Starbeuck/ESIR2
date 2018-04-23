package mainpck;
public class PressePapierImpl implements PressePapier {
	
	String texteIntermediaire;
	
	public void PressePapier (){
		texteIntermediaire="";
	}
	
	@Override
	public void write(String s) {
		this.texteIntermediaire=s;
	}

	@Override
	public String read() {
		return this.texteIntermediaire;
	}

}
