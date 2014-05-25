package com.zaid.www.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.zaid.www.cl.CList;

/***
 * Unit test for circular linked list
 * @author Zaid Ahmad
 *
 */
public class TestCLinkedList {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNext() {
		CList<Integer> l = new CList<Integer>();
		l.insert(1);
		l.insert(5);
		l.insert(9);
		assertTrue(l.getCurrent() == 9);
		l.next();
		assertTrue(l.getCurrent() == 5);
		l.next();
		assertTrue(l.getCurrent() == 1);

		l.next();
		l.next();
		l.next();
		l.next();
		assertTrue(l.getCurrent() == 9);
	}
	
	@Test
	public void testInsert() {
		CList<Integer> l = new CList<Integer>();
		for(int i=0;i<99;i++) l.insert(i);
		assertEquals(l.getLength(), 99);
	}
	
	@Test
	public void testRemove() {
		CList<Integer> l = new CList<Integer>();
		l.insert(1);
		l.insert(5);
		l.insert(9);		
		System.out.println(l.toString()); // debug
		
		l.remove(1);
		System.out.println(l.toString()); // debug
		assertEquals(l.getLength(), 2);
		assertTrue(l.getCurrent() == 9);
		l.next();
		assertTrue(l.getCurrent() == 1);
		l.remove(0);
		System.out.println(l.toString()); // debug
		assertTrue(l.getCurrent() == 9);
		
		l = new CList<Integer>();
		for(int i=0;i<10;i++) l.insert(i);
		assertTrue(l.getCurrent() == 9);
		l.remove(10);
		assertTrue(l.getCurrent() == 8);
		l.remove(9);
		assertTrue(l.getCurrent() == 7);
	}
	
	@Test
	public void testToString() {
		CList<String> l = new CList<String>();
		l.insert("cow");
		l.insert("brown");
		l.insert("now");
		l.insert("How");
		assertEquals(l.toString(), "How:now:brown:cow:↩");
	}
}

