package com.zaid.www.josephus;

import com.zaid.www.cl.CList;

/***
 * The concept of the Jospehus problem is that we have a group of men standing
 * in a circle. We start counting from a certain person, then Every 3rd man
 * should leave the circle and kill himself, and the process should continue
 * until there was only one man left.
 * 
 * The solution provided below allow us to work out the position for the
 * surviving man, providing any any given list (CList<T> list), with a given
 * start position (int start) and interval (int interval).
 * 
 * @author Zaid Ahmad
 * @version 1.0
 */


public class Josephus {

	/**
	 * findSurvivor returns the surviving element from the input list by solving
	 * the Josephus problem.
	 * 
	 * @param list - the list containing the nodes
	 * @param start - the starting node
	 * @param interval - the interval where elements should be killed (usually 3 for
	 *        this problem)
	 * @return the surviving element
	 */
	public static <T> T findSurvivor(CList<T> list, int start, int interval) {
		int man = start;	
		if(list == null) {
			return null;
		} else {
			while (list.getLength()>1){
				man = (man + interval -1) % list.getLength();
				list.remove(man);
			}
		}
		return list.getCurrent();
	}			
}
