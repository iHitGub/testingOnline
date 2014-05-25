package com.zaid.www.cl;

/***
 * ListItem, holds the list items in the circular list
 * 
 * @author Zaid Ahmad
 * @version 1.0
 * @param <T>
 */
public class ListItem<T> {
	T data;
	ListItem<T> next;
	
	/**
	 * Class constructor. Sets the list item when called
	 * 
	 * @param data
	 */
	public ListItem(T data) {
		this.data = data;
	}
	
	/**
	 * Returns the next list item, the item after the current node
	 * 
	 * @return the next list item
	 */
	public ListItem<T> getNext() {
		return next;
	}
	
	/***
	 * Sets the next list item, the item after the current node
	 * 
	 * @param next list item
	 */
	public void setNext(ListItem<T> next) {
		this.next = next;
	}
	
	/***
	 * Returns a string representation of the list item
	 */
	public String toString() {
		return data.toString() + ":";
	}
}
