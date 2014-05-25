package com.zaid.www.rb;
import com.zaid.www.rb.RBNode;

/***
 * RBTree: This class is used to create and manipulate red-black trees and
 * contains the find, insert, delete and subsidiary methods. It should include
 * an enum class RBColour with two fields, RED and BLACK.
 * 
 * @author Zaid Ahmad
 * @version 1.0
 */
public class RBTree {

	/***
	 * Enum holding the red balck colours
	 * @author Zaid Ahmad
	 */
	public enum RBColour{ 
		BLACK , RED; 
	}

	private RBNode root= Nil.getNil();

	/***
	 * Return the RBNode root element
	 * @return root
	 */
	public RBNode getRoot(){
		return root;
	}

	/***
	 * Set the RBNode root element
	 * @param r RBNode root to be set
	 */
	public void setRoot( RBNode r ){ 
		root = r;
	}

	/***
	 * Returns a boolean whether a node is found or not
	 * 
	 * @param e Integer
	 * @return boolean
	 */
	public boolean find(Integer e) {

		boolean isFound = false;
		RBTreeNode current = (RBTreeNode) getRoot();

		if (!getRoot().equals(Nil.getNil())) {
			while (!isFound) {
				if (current.getLabel() == e) {
					return true;
				} else if (current.getLabel() > e) {
					current = (RBTreeNode) current.getLeft();
				}
				if (current.getLabel() < e) {
					current = (RBTreeNode) current.getRight();
				}
			}
		}
		return isFound;
	}

	/***
	 * Finds a node in the tree
	 * @param e Integer
	 * @return RBNode the node found
	 */
	public RBNode findNode(Integer e) {
		RBNode node = getRoot();
		while (!(node.equals(Nil.getNil()))
				&& (e.equals(((RBTreeNode) node).getLabel()))) {
			if (e < ((RBTreeNode) node).getLabel()) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		return node;
	}

	/***
	 * The insert algorithm inserts a new element to a red-black tree. It does
	 * this in the same way as for a BST, inserting the new node with the colour
	 * RED, then calls a second routine to repair the red-black properties.
	 * 
	 * @param e Integer
	 */
	public void insert (Integer e){
		RBTreeNode z = new RBTreeNode (e, null);
		RBNode y = Nil.getNil();
		RBNode x = getRoot();
		while(!(x.equals(Nil.getNil()))) {
			y = x;
			if(z.getLabel() < ((RBTreeNode) x).getLabel()) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}			
		}
		z.setParent(y);
		if( y.equals( Nil.getNil() ) ) {
			setRoot(z);
		}else 
			if(z.getLabel() < ((RBTreeNode)y).getLabel()) {		
				y.setLeft(z);
			} else {			
				y.setRight(z);
			}
		z.setLeft( Nil.getNil() );
		z.setRight(  Nil.getNil() );
		z.setColour( RBColour.RED );
		fixUpInsert(z);
	}

	/***
	 * Deletion from a red-black tree
	 * @param z Integer
	 */
	public void delete (Integer z){
		RBNode y = findNode(z);
		RBNode yClone = findNode(z);
		RBNode x;
		RBColour yOrigColour = y.getColour();							

		if (yClone.getLeft().equals(Nil.getNil()) ) {
			x = yClone.getRight();
			transplant(yClone,yClone.getRight());
		}
		else if (yClone.getRight().equals(Nil.getNil()) ){
			x = yClone.getLeft();
			transplant(yClone,yClone.getLeft());
		}
		else{
			y = minimum(yClone.getRight());
			yOrigColour = y.getColour();
			x=y.getRight();
			if (y.getParent().equals(yClone) ) {
				x.setParent(y);
			}
			else{
				transplant(y,y.getRight());
				y.setRight(yClone.getRight());
				y.getRight().setParent(y);
			}
			transplant(yClone,y);
			y.setLeft(yClone.getLeft());
			y.getLeft().setParent(y);
			y.setColour(yClone.getColour());
		}
		if (yOrigColour.equals(RBColour.BLACK) ) {
			fixupDelete(x);
		}
	}


	/***
	 * The insert-fixup algorithm repairs the red-black properties of a tree
	 * following the insertion of a new node. There are three cases to consider,
	 * corresponding to recolouring and two types of rotation.
	 * 
	 * @param n RBNode
	 */
	private void fixUpInsert(RBNode n) {
		RBNode y = Nil.getNil();
		while (n.getParent().getColour().equals(RBColour.RED)) {
			if (n.getParent().equals(n.getParent().getParent().getLeft())) {
				y = n.getParent().getParent().getRight();
				if (y.getColour().equals(RBColour.RED)) {
					n.getParent().setColour(RBColour.BLACK);
					y.setColour(RBColour.BLACK);
					n.getParent().getParent().setColour(RBColour.RED);
					n = n.getParent().getParent();
				} else {
					if (n.equals(n.getParent().getRight())) {
						n = n.getParent();
						rotateLeft((RBTreeNode) n);
					}
					n.getParent().setColour(RBColour.BLACK);
					n.getParent().getParent().setColour(RBColour.RED);
					rotateRight((RBTreeNode) n.getParent().getParent());
				}
			} else if (n.getParent().equals(
					n.getParent().getParent().getRight())) {
				y = n.getParent().getParent().getLeft();
				if (y.getColour().equals(RBColour.RED)) {
					n.getParent().setColour(RBColour.BLACK);
					y.setColour(RBColour.BLACK);
					n.getParent().getParent().setColour(RBColour.RED);
					n = n.getParent().getParent();
				} else {
					if (n.equals(n.getParent().getLeft())) {
						n = n.getParent();
						rotateRight((RBTreeNode) n);
					}
					n.getParent().setColour(RBColour.BLACK);
					n.getParent().getParent().setColour(RBColour.RED);
					rotateLeft((RBTreeNode) n.getParent().getParent());
				}
			}
		}
		getRoot().setColour(RBColour.BLACK);
	}

	/***
	 * The delete-fixup repairs the red-black properties of a tree following the
	 * deletion of a node.
	 * 
	 * @param n RBNode
	 */
	private void fixupDelete(RBNode n) {
		RBNode w = Nil.getNil();
		while ((!(n.equals(this.getRoot())))
				&& n.getColour().equals(RBColour.BLACK)) {
			if (n.equals(n.getParent().getLeft())) {
				w = n.getParent().getRight();
				if (w.getColour().equals(RBColour.RED)) {
					w.setColour(RBColour.BLACK);
					n.getParent().setColour(RBColour.RED);
					this.rotateLeft((RBTreeNode) n.getParent());
					w = n.getParent().getRight();
				}
				if (w.getLeft().getColour().equals(RBColour.BLACK)
						&& w.getRight().getColour().equals(RBColour.BLACK)) {
					w.setColour(RBColour.RED);
					n = n.getParent();
				} else {
					if (w.getRight().getColour().equals(RBColour.BLACK)) {
						w.getLeft().setColour(RBColour.BLACK);
						w.setColour(RBColour.RED);
						this.rotateRight((RBTreeNode) w);
						w = n.getParent().getRight();
					}

					w.setColour(n.getParent().getColour());
					n.getParent().setColour(RBColour.BLACK);
					w.getRight().setColour(RBColour.BLACK);
					this.rotateLeft((RBTreeNode) n.getParent());
					n = this.getRoot();
				}
			} else {
				w = n.getParent().getLeft();
				if (w.getColour().equals(RBColour.RED)) {
					w.setColour(RBColour.BLACK);
					n.getParent().setColour(RBColour.RED);
					this.rotateRight((RBTreeNode) n.getParent());
					w = n.getParent().getLeft();
				}
				if (w.getRight().getColour().equals(RBColour.BLACK)
						&& w.getLeft().getColour().equals(RBColour.BLACK)) {
					w.setColour(RBColour.RED);
					n = n.getParent();
				} else {
					if (w.getLeft().getColour().equals(RBColour.BLACK)) {
						w.getRight().setColour(RBColour.BLACK);
						w.setColour(RBColour.RED);
						this.rotateLeft((RBTreeNode) w);
						w = n.getParent().getLeft();
					}

					w.setColour(n.getParent().getColour());
					n.getParent().setColour(RBColour.BLACK);
					w.getLeft().setColour(RBColour.BLACK);
					this.rotateRight((RBTreeNode) n.getParent());
					n = this.getRoot();
				}
			}
		}

		n.setColour(RBColour.BLACK);
	}	


	/***
	 * The rotate-left algorithm performs a left rotation on a given node.
	 * 
	 * @param n the RBTreeNode
	 */
	private void rotateLeft(RBTreeNode n) {
		RBNode y = n.getRight();
		n.setRight(y.getLeft());
		if (!(y.getLeft().equals(Nil.getNil()))) {
			y.getLeft().setParent(n);
		}
		y.setParent(n.getParent());
		if (n.getParent().equals(Nil.getNil())) {
			setRoot(y);
		} else if (n.equals(n.getParent().getLeft())) {
			n.getParent().setLeft(y);
		} else {
			n.getParent().setRight(y);
		}
		y.setLeft(n);
		n.setParent(y);
	}

	/***
	 * The rotate-right algorithm performs a right rotation on a given node.
	 * 
	 * @param n the RBTreeNode
	 */
	private void rotateRight(RBTreeNode n) {
		RBNode y = n.getLeft();
		n.setLeft(y.getRight());
		if (!(y.getRight().equals(Nil.getNil()))) {
			y.getRight().setParent(n);
		}
		y.setParent(n.getParent());
		if (n.getParent().equals(Nil.getNil())) {
			setRoot(y);
		} else if (n.equals(n.getParent().getRight())) {
			n.getParent().setRight(y);
		} else {
			n.getParent().setLeft(y);
		}
		y.setRight(n);
		n.setParent(y);
	}

	/***
	 * Required to delete a node from a red-black tree, which
	 * swaps the position of two nodes (and their children) within the tree.
	 * 
	 * @param u RBNode
	 * @param v RBNode
	 */
	public void transplant(RBNode u, RBNode v) {
		if (u.getParent().equals(Nil.getNil())) {
			setRoot(v);
		} else if (u.equals(u.getParent().getLeft())) {
			u.getParent().setLeft(v);
		} else {
			u.getParent().setRight(v);
		}
		v.setParent(u.getParent());
	}

	/***
	 * The minimum algorithm finds the smallest node in a subtree and is used by
	 * delete-fixup.
	 * 
	 * @param x RBNode
	 * @return RBNode
	 */
	public RBNode minimum(RBNode x) {
		while (!(x.getLeft().equals(Nil.getNil()))) {
			x = x.getLeft();
		}
		return x;
	}
}
