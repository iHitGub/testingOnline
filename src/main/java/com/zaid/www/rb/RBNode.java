package com.zaid.www.rb;

/***
 * All nodes in a red-black tree are instances of one of the subclasses of the
 * abstract class RBNode: RBTreeNode and Nil. RBNode includes methods and
 * attributes forming the basis of the implementation of Red-Black trees.
 * 
 * @author Zaid Ahmad
 * @version 1.0
 */
public abstract class RBNode {
	private RBTree.RBColour COLOUR;
	private RBNode parent;
	private RBNode left;
	private RBNode right;
	
	/***
	 * Returns the height of the tree
	 * @return height
	 */
	public abstract int height();
	
	/***
	 * Returns the colour of the node
	 * @return colour
	 */
	public RBTree.RBColour getColour(){
		return COLOUR;		
	}
	
	/***
	 * Sets the colour of the node
	 * @param c colour to be set
	 */
	public void setColour(RBTree.RBColour c ){
		this.COLOUR = c;		
	}
	
	/***
	 * Returns the parent node in the tree
	 * @return the parent
	 */
	public RBNode getParent(){
		return parent;
	}
	
	/***
	 * Sets the parent node in the tree
	 * @param p node to be set
	 */
	public void setParent(RBNode p){
		this.parent = p;
	}
	
	/***
	 * Return the left node in the tree
	 * @return the left node
	 */
	public RBNode getLeft(){
		return left;
	}
	
	/***
	 * Return the right node in the tree
	 * @return the right node
	 */
	public RBNode getRight(){
		return right;
	}
	
	/***
	 * Sets the left node in the tree
	 * @param l left node to be set
	 */
	public void setLeft(RBNode l){
		this.left = l;
	}
	/***
	 * Sets the right node in the tree
	 * @param r right node to be set
	 */
	public void setRight(RBNode r){
		this.right = r;
	}
}
