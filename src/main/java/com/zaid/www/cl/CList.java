package com.zaid.www.cl;

/***
 * Circular linked list is a data structure consisting of a group of nodes in
 * which the head element's previous pointer points to the tail element and the
 * tail element's next pointer points to the head element. Special cases can
 * occur when a circular list has only one element, where the element's previous
 * and next pointers point to itself and it is both the head and tail of the
 * list.
 * 
 * @author Zaid Ahmad
 * @version 1.0
 * @param <T>
 */
public class CList<T> {
	private ListItem<T> current, back;
	public int length;

	/***
	 * Returns the length of the circular linked list
	 * 
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/***
	 * If no items in the list, return null, else, return the current list item
	 * 
	 * @return current list item
	 */
	public T getCurrent() {
		if(isEmpty()) {	
			return null;
		} else {
			return current.data;
		}			
	}

	/***
	 * If no items in the list, return null, else, return the next list item,
	 * and move the pointer to next item
	 * 
	 * @return next list item
	 */
	public T next() {
		T temp;
		if(isEmpty()) {
			return null;
		} else {
			temp = current.next.data;
			current = current.next;
		}
		return temp;
	}

	/***
	 * Check if current is null, if yes, initialise current, the entry node and
	 * link entry node to self as there is only one node on the list. If current
	 * is not null, insert element to list and link back to the new current, to
	 * make it circular.
	 * 
	 * @param e the element to be inserted
	 */
	public void insert(T e) {
		if(isEmpty()) {
			current = new ListItem<T>(e);
			current.setNext(current);
			back = current;
		} else {
			ListItem<T> temp = new ListItem<T>(e);		
			temp.setNext(current);
			current = temp;
			back.setNext(current);
		}
		length++;
	}

	/***
	 * Remove items from the list at a specific index, covering all scenarios.
	 * Case 1: any element apart from the first element. Case 2: the first
	 * element only, assuming length>1. Case 3: there is only one element
	 * pointing to itself
	 * 
	 * @param index of element to be removed
	 * @return the removed element
	 */
	public T remove(int index) {
		ListItem<T> temp = current;
		T removed = current.data;
		if((index != 0) && ((index % length) != 0)) {
			for(int i=0; i< index-1;i++) {
				next();
			}
			removed = current.next.data;
			current.setNext(current.getNext().getNext());
			current = temp;		
		} else 
			if((index == 0) || ((index % length) == 0)) {
				temp = current.next;
				for(int i=0; i<length-1; i++) {
					next();
				}
				current.setNext(current.getNext().getNext());
				current = temp;
			}		
		if(index==0 && length==1) {
			current=null;
		}
		length--;
		return removed;
	}

	/***
	 * Return true if current is null else false
	 */
	public boolean isEmpty() {
		return (current == null);
	}

	/***
	 * Return a string buffer of items in the circular list
	 */
	public String toString() {
		StringBuffer res = new StringBuffer();
		if (current != null) {
			ListItem<T> li = current;
			boolean beenRound = false;
			while (true) {
				if (li.equals(current)) {
					if (beenRound) {
						res.append("↩");
						break;
					} else {
						beenRound = true;
					}
				} 
				res.append(li.toString());
				li = li.getNext();
			}
		}
		return res.toString();
	}
}
