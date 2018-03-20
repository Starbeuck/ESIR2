package exercice2;

import java.util.ArrayList;

public class Client {

	private Banque mybank;
	private ArrayList<Compte> my_compte;
	private String adress;
	private String nom;
	private String numeroClient;
	
	public Client(Banque b){
		this.mybank = b;
	}
	
	public void addCompte(Compte c){}
	public void removeCompte (Compte c){}
}
