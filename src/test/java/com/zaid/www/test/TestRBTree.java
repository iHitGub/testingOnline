package com.zaid.www.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.zaid.www.rb.Nil;
import com.zaid.www.rb.RBNode;
import com.zaid.www.rb.RBTree;
import com.zaid.www.rb.RBTreeNode;
import com.zaid.www.util.PaddedWriter;
import com.zaid.www.util.PrettyPrinter;
import com.zaid.www.util.Util;

public class TestRBTree {
	
	private int TREE_SIZE_BIG = 99;
	private int TREE_SIZE_SMALL = 10;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsertAndFind() {
		RBTree t = new RBTree();
		for (int i = 0; i < TREE_SIZE_SMALL; i++) {
			t.insert(i);
		}
		for (int i = 0; i < TREE_SIZE_SMALL; i++) {
			assertTrue(t.find(i));
		}
		//debug(t);
	}

	/*
	 * Test that every node is RED or BLACK after insertion and deletion
	 */
	@Test
	public void testRBProp1() {
		int[] data = getArrayOfRandomInts(TREE_SIZE_BIG);
		RBTree t = getTreeOfInts(data);
		testRBProp2Helper(t.getRoot());
		
		for(int i=0;i<TREE_SIZE_BIG;i++) {
			t.delete(data[i]);
			testRBProp2Helper(t.getRoot());
		}
	}

	public void testRBProp2Helper(RBNode n) {
		RBTree.RBColour c = n.getColour();
		assertTrue(c.equals(RBTree.RBColour.BLACK)
				|| c.equals(RBTree.RBColour.RED));
		if (n instanceof RBTreeNode) {
			testRBProp2Helper(n.getLeft());
			testRBProp2Helper(n.getRight());
		}
	}

	/*
	 * Test that the root is BLACK after insertion and deletion
	 */
	@Test
	public void testRBProp2() {
		// RBTree t = getTreeOfRandomInts(10);
		RBTree t = getSmallTree();
		//debug(t);
		assertEquals(t.getRoot().getColour(), RBTree.RBColour.BLACK);
		
		for(int i=0;i<TREE_SIZE_SMALL;i++) {
			t.delete(i);
			assertEquals(t.getRoot().getColour(), RBTree.RBColour.BLACK);
		}
	}

	/*
	 * Test that all RED nodes have BLACK children after insertion and deletion
	 */
	@Test
	public void testRBProp3() {
		int[] data = getArrayOfRandomInts(TREE_SIZE_BIG);
		RBTree t = getTreeOfInts(data);
		testRBProp3Helper(t.getRoot());
		
		for(int i=0;i<TREE_SIZE_BIG;i++) {
			t.delete(data[i]);
			testRBProp3Helper(t.getRoot());
		}
	}

	public void testRBProp3Helper(RBNode n) {
		if (!n.equals(Nil.getNil())) {
			if (n.getColour().equals(RBTree.RBColour.RED)) {
				assertEquals(n.getLeft().getColour(), RBTree.RBColour.BLACK);
				assertEquals(n.getRight().getColour(), RBTree.RBColour.BLACK);
			}
			testRBProp2Helper(n.getLeft());
			testRBProp2Helper(n.getRight());
		}
	}
	/*
	 * For each node, $x$, every path from x to a leaf contains the same number of black
    nodes after insertion and deletion.
	 */
	@Test
	public void testRBProp4() {
		RBTree t = getSmallTree();
		debug(t);
		ArrayList<Integer> blackHeights = Util.blackHeights(t.getRoot());
		Integer bh = blackHeights.get(0);
		for(Integer i: blackHeights) {
			assertEquals(bh, i);
		}
		
		int[] data = getArrayOfRandomInts(TREE_SIZE_BIG);
		t = getTreeOfInts(data);
		testRBProp4Helper(t.getRoot());
		
		for(int i=0;i<TREE_SIZE_BIG;i++) {
			t.delete(data[i]);
			testRBProp4Helper(t.getRoot());
		}
	}
	

	public void testRBProp4Helper(RBNode n) {
		ArrayList<Integer> blackHeights = Util.blackHeights(n);
		//System.out.println(blackHeights.toString());
		Integer bh = blackHeights.get(0);
		for(Integer i: blackHeights) {
			assertEquals(bh, i);
		}
	}

	private RBTree getSmallTree() {
		RBTree t = new RBTree();
		for (int i = 0; i < 10; i++) {
			t.insert(i);
		}
		return t;
	}

	private int[] getArrayOfRandomInts(int size) {
		int[] res = new int[size];
		Random gen = new Random();
		for (int i = 0; i < size; i++) {
			res[i] = gen.nextInt();
		}
		return res;
	}
	
	private RBTree getTreeOfInts(int[] contents) {
		RBTree t = new RBTree();
		int size = contents.length;
		for (int i = 0; i < size; i++) {
			t.insert(contents[i]);
		}
		return t;
	}

	private void debug(RBTree t) {
		PrettyPrinter.printPretty(t.getRoot(), 1, 0, new PaddedWriter(
				System.out));
	}

}
