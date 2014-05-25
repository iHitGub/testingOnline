package com.zaid.www.rb;

/***
 * An instance of RBTreeNode is an internal node in the tree. Other than the
 * height method, the methods in RBTreeNode are getters and setters. A single
 * constructor is provided for RBTreeNode, which takes a label and parent node.
 * 
 * @author Zaid Ahmad
 * @version 1.0
 */
public class RBTreeNode extends RBNode {
	private Integer label;

	/***
	 * The constructor, setting the left and right child to instances of Nil.
	 * 
	 * @param label
	 * @param parent
	 */
	public RBTreeNode(Integer label, RBNode parent){
		this.label = label;
		setParent(parent);
		setLeft(Nil.getNil());
		setRight(Nil.getNil());
	}	
	
	/***
	 * Returns the height of the RBTreeNode
	 */
	public int height(){		
		int lh = ( getLeft() == Nil.getNil() ) ? 0 : getLeft().height ();
		int rh = ( getRight() == Nil.getNil() ) ? 0 : getRight().height ();
		return 1 + Math.max (lh , rh);
	}	
	
	/***
	 * Returns the label of the RBTreeNode
	 * @return label
	 */
	public Integer getLabel(){
		return label;
	}

	/***
	 * Used for debugging.
	 */
	public String toString () {
		if ( label == null ) {
			return " null ";
		} else {
			String cStr = ( getColour () == null ) ? "" : getColour ()
					. toString (). substring (0, 1);
			String suffix = " ["+ cStr +"]";
			return label . toString () + suffix ;
		}
	}
}
