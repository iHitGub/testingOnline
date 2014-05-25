package com.zaid.www.rb;

import com.zaid.www.rb.RBTree.RBColour;

/***
 * An instance of Nil represents an empty child in the tree, and should also be
 * supplied as the parent of the root element. Every instance of Nil should have
 * its colour field set to RBColour.BLACK.
 * 
 * @author Zaid Ahmad
 * @version 1.0
 */
public class Nil extends RBNode {
	private static Nil nil;
	
	/***
	 * Constructor setting node colour to black
	 */
	private Nil(){
		setColour(RBColour.BLACK);
	}
		
	/***
	 * Return height of nil node set to 1
	 */
	public int height(){
		return 1;
	}
	
	/***
	 * Return the nil
	 * @return nil
	 */
	public static Nil getNil(){
		if (nil == null){
			nil = new Nil();
		}
		return nil;
	}
	
	/***
	 * Return left nil, always nil
	 */
	public RBNode getLeft(){
		return nil;
	}
	
	/***
	 * Return right nil, always nil
	 */
	public RBNode getRight(){
		return nil;
	}
	
	/***
	 * return "*"
	 */
	public String toString () {
		return "*";
	}

}
