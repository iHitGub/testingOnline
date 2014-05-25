package com.zaid.www.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zaid.www.cl.CList;
import com.zaid.www.josephus.Josephus;

public class TestJosephus {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testClassicScenario() {
		CList<Integer> l1 = new CList<Integer>();
		for(int i=41;i>0;i--) l1.insert(i);
		assertEquals(31, Josephus.findSurvivor(l1, 0, 3).intValue());
	}
	
	@Test
	public void testNull() {
		CList<Integer> l1 = new CList<Integer>();
		assertNull(Josephus.findSurvivor(l1, 0, 3));
	}
	
	@Test
	public void testWrapAround() {
		CList<Integer> l1 = new CList<Integer>();
		for(int i=7;i>0;i--) l1.insert(i);
		assertEquals(7, Josephus.findSurvivor(l1, 0, 9).intValue());
	}
	
	@Test
	public void testNonZeroStart() {
		CList<Integer> l1 = new CList<Integer>();
		for(int i=7;i>0;i--) l1.insert(i);
		assertEquals(6, Josephus.findSurvivor(l1, 2, 3).intValue());
	}

	@Test
	public void testStrings() {
		CList<String> l1 = new CList<String>();
		l1.insert("Carol");
		l1.insert("Bob");
		l1.insert("Alice");
		l1.insert("Josephus");
		assertEquals("Josephus", Josephus.findSurvivor(l1, 0, 2));
	}

}
