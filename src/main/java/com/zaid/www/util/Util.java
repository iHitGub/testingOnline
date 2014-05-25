package com.zaid.www.util;

import java.util.ArrayList;

import com.zaid.www.rb.Nil;
import com.zaid.www.rb.RBNode;
import com.zaid.www.rb.RBTree;
import com.zaid.www.rb.RBTreeNode;

/***
 * helper class to print red and black trees
 *
 */
public class Util {

	public static ArrayList<Integer> blackHeights(RBNode n) {
		ArrayList<Integer> res =  blackHeightsHelper(n.getLeft(), 0, new ArrayList<Integer>());
		res.addAll(blackHeightsHelper(n.getRight(), 0, new ArrayList<Integer>()));
		return res;
	}
	
	private static ArrayList<Integer> blackHeightsHelper(RBNode n, int acc, ArrayList<Integer> accList) {
		if(n instanceof Nil) {
			accList.add(++acc);
			return accList;
		} else {
			if(n.getColour().equals(RBTree.RBColour.BLACK)) {
				acc++;
			}
			return blackHeightsHelper(n.getLeft(), acc, blackHeightsHelper(n.getRight(), acc, accList));
		}
	}
}
