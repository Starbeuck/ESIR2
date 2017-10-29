package p1;


import static org.junit.Assert.*;
import org.junit.Test;
import test0.Sequence;


public class MainTest {
	Sequence s= new SequenceImpl();
	
	@Test
	public void test1() {
		Integer[] t1 = new Integer[] { 1, 2, 3 };
		Integer[] t2 = new Integer[] { 1, 2, 3, 4};
		
		assertTrue(s.subSeq(t1,t2));
	}
	
	@Test
	public void test2() {
		Integer[] t1 = new Integer[] { 1, 2, 3 };
		Integer[] t2 = new Integer[] { 1, 2, 3};
		
		assertTrue(s.subSeq(t1,t2));
	}
	
	@Test
	public void test3() {
		Integer[] t1 = new Integer[] {  };
		Integer[] t2 = new Integer[] { };
		
		assertTrue(s.subSeq(t1,t2));
	}
	
	@Test
	public void test4() {
		Integer[] t1 = new Integer[] { 1, 2, 3 };
		Integer[] t2 = new Integer[] { };
		
		assertFalse(s.subSeq(t1,t2));
	}
	
	@Test
	public void test5() {
		Integer[] t1 = new Integer[] {  };
		Integer[] t2 = new Integer[] { 1, 2, 3, 4};
		
		assertTrue(s.subSeq(t1,t2));
	}
	
	@Test
	public void test6() {
		Integer[] t1 = new Integer[] { 1, 2, 3 };
		Integer[] t2 = new Integer[] { 3, 2, 1};
		
		assertFalse(s.subSeq(t1,t2));
	}
	
	@Test
	public void test7() {
		Integer[] t1 = new Integer[] { 1, 2, 2, 3 };
		Integer[] t2 = new Integer[] { 1, 2, 3, 4};
		
		assertTrue(s.subSeq(t1,t2));
	}
	
	@Test
	public void test8() {
		Integer[] t1 = new Integer[] { 1, 3, 2 };
		Integer[] t2 = new Integer[] { 1, 2, 3, 4};
		
		assertFalse(s.subSeq(t1,t2));
	}
	
	@Test
	public void test9() {
		Integer[] t1 = new Integer[] { 1, 1, 1};
		Integer[] t2 = new Integer[] { 1, 2, 3, 4};
		
		assertFalse(s.subSeq(t1,t2));
	}
	// A COMPLETER!!!
}
