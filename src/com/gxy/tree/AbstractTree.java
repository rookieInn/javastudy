package com.gxy.tree;

import java.util.Iterator;

public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {

	public void inorder() {
	}
	
	public void postorder() {
	}

	public void preorder() {
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}
	
	public Iterator<E> iterator(){
		return null;
	}
	
}
