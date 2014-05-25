package com.zaid.www.util;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import com.zaid.www.rb.RBNode;
import com.zaid.www.rb.RBTreeNode;

/**
 * Code adapted from http://stackoverflow.com/a/5273509/589352 
 *
 */

public class PrettyPrinter {

	/***
	 * Formatting of a binary tree to the output stream
	 * @param tree
	 * @param level
	 * @param indentSpace
	 * @param out
	 */
    public static <T>void printPretty(RBNode tree, int level, int indentSpace, PaddedWriter out) {
        int h = tree.height();
        int nodesInThisLevel = 1;
        int branchLen = 2*((int)Math.pow( 2.0, h )-1) - (3-level) *(int)Math.pow( 2.0, h-1 );
        int nodeSpaceLen = 2+(level+1)*(int)Math.pow(2.0,h);
        int startLen = branchLen + (3-level) + indentSpace;

        Deque<RBNode> nodesQueue = new LinkedList<RBNode>();
        nodesQueue.offerLast( tree );
        for (int r = 1; r < h; r++) {
            printBranches( branchLen, nodeSpaceLen, startLen, nodesInThisLevel, nodesQueue, out );
            branchLen = branchLen/2 - 1;
            nodeSpaceLen = nodeSpaceLen/2 + 1;
            startLen = branchLen + (3-level) + indentSpace;
            printNodes(branchLen, nodeSpaceLen, startLen, nodesInThisLevel, nodesQueue, out);

            for (int i = 0; i < nodesInThisLevel; i++) {
                RBNode currNode = nodesQueue.pollFirst();
                if (currNode instanceof RBTreeNode) {
                    nodesQueue.offerLast( ((RBTreeNode)currNode).getLeft() );
                    nodesQueue.offerLast( ((RBTreeNode)currNode).getRight() );
                } else {
                    nodesQueue.offerLast( null );
                    nodesQueue.offerLast( null );
                }
            }
            nodesInThisLevel *= 2;
        }
        printBranches(branchLen, nodeSpaceLen, startLen, nodesInThisLevel, nodesQueue, out);
        printLeaves(indentSpace, level, nodesInThisLevel, nodesQueue, out);
    }

    /***
     * Prints the tree branches
     * @param branchLen
     * @param nodeSpaceLen
     * @param startLen
     * @param nodesInThisLevel
     * @param nodesQueue
     * @param out
     */
    private static <T>void printBranches(int branchLen, int nodeSpaceLen, int startLen, 
    		int nodesInThisLevel, Deque<RBNode> nodesQueue, PaddedWriter out) {
        Iterator<RBNode> iterator = nodesQueue.iterator();
        for (int i = 0; i < nodesInThisLevel/2; i++) {
            if (i == 0) {
                out.setw(startLen-1);
            } else {
                out.setw(nodeSpaceLen-2);
            }
            out.write();
            RBNode next = iterator.next();
            if (next != null) {
                out.write( "/" );
            } else {
                out.write(" ");
            }
            out.setw(2*branchLen+2);
            out.write();
            next = iterator.next();
            if (next != null) {
                out.write( "\\" );
            } else {
                out.write( " " );
            }
        }
        out.endl();
    }

    /***
     * Print the branches and node (eg, ___10___ )
     * @param branchLen
     * @param nodeSpaceLen
     * @param startLen
     * @param nodesInThisLevel
     * @param nodesQueue
     * @param out
     */
    private static <T>void printNodes(int branchLen, int nodeSpaceLen, int startLen, int nodesInThisLevel, 
    		Deque<RBNode> nodesQueue, PaddedWriter out) {
        Iterator<RBNode> iterator = nodesQueue.iterator();
        RBNode currentNode;
        for (int i = 0 ; i < nodesInThisLevel; i++) {
            currentNode = iterator.next();
            if (i == 0) {
                out.setw(startLen );
            } else {
                out.setw(nodeSpaceLen );
            }
            out.write();
            if (currentNode instanceof RBTreeNode && ((RBTreeNode) currentNode).getLeft() != null) {
                out.setfill( '_' );
            } else {
                out.setfill( ' ' );
            }
            out.setw( branchLen+2 );
            if (currentNode instanceof RBTreeNode) {
                out.write(currentNode.toString());
            } else {
                out.write();
            }
            if (currentNode instanceof RBTreeNode && ((RBTreeNode) currentNode).getRight() != null) {
                out.setfill( '_' );
            } else {
                out.setfill( ' ' );
            }
            out.setw(branchLen);
            out.write();
            out.setfill(' ');
        }
        out.endl();
    }

    /***
     * Print the leaves only (just for the bottom row)
     * @param indentSpace
     * @param level
     * @param nodesInThisLevel
     * @param nodesQueue
     * @param out
     */
    private static <T>void printLeaves(int indentSpace, int level, int nodesInThisLevel, 
    		Deque<RBNode> nodesQueue, PaddedWriter out) {
        Iterator<RBNode> iterator = nodesQueue.iterator();
        RBNode currentNode;
        for (int i = 0; i < nodesInThisLevel; i++) {
            currentNode = iterator.next();
            if (i == 0) {
                out.setw(indentSpace+2);
            } else {
                out.setw(2*level+2);
            }
            if (currentNode instanceof RBTreeNode) {
                out.write(currentNode.toString());
            } else {
                out.write();
            }
        }
        out.endl();
    }
}
