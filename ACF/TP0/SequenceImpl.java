package p1;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import test0.Sequence;
import test0.Tester;

public class SequenceImpl implements Sequence {

	public static int contain(Object[] t1, Object t2) {
		int indice = -1;
		int i = 0;
		while (indice == -1 && i < t1.length) {
			if (t1[i].equals( t2))
				indice = i;
			else
				i++;
		}

		return indice;
	}

	/**
	 * @param l1
	 * @param l2
	 * @return true if list t1 is a sublist of list t2, where at most one
	 *         element of t1 may not occur in t2
	 */

	public boolean subSeq(Object[] t1, Object[] t2) {
		// TODO: program this function
		int indice = -1;
		boolean occurencesautee = false;
		int valintermediaire = -1;
		System.out.println(t1.length);
		for (int i = 0; i < t1.length; i++) {
			valintermediaire = contain(t2, t1[i]);
			System.out.println(valintermediaire);

			System.out.println(indice);
			if ((valintermediaire == -1||valintermediaire==indice) && occurencesautee == false){
				occurencesautee = true;
				System.out.println("a");
			}
			else if (valintermediaire==-1 && occurencesautee==true){
				System.out.println("bl");
				return false ;

			}
			else if (occurencesautee==true && valintermediaire==indice){

				System.out.println("c");
				return false;
			}
			else if (valintermediaire >= indice){
				indice = valintermediaire;
				System.out.println("d");
			}
			else {

				System.out.println("e");
				return false;
			}
		}
		return true;

	}

	public static void main(String[] args) {
		Sequence s = new SequenceImpl();
		Integer[] t1 = new Integer[] { 1, 1, 1};
		Integer[] t2 = new Integer[] { 1, 2, 3, 4};
		
		s.subSeq(t1, t2);
	}

}
