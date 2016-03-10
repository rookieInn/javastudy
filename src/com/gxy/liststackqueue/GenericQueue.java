package com.gxy.liststackqueue;

import java.util.LinkedList;

public class GenericQueue<E> {

	private LinkedList<E> list = new LinkedList<E>();
	
	/**
	 * 进队
	 * @param e
	 */
	public void enqueue(E e) {
		list.addLast(e);
	}
	
	public E dequeue() {
		return list.removeFirst();
	}
	
	public int getSize() {
		return list.size();
	}
	
	public String toString() {
		return "Queue: " + list.toString();
	}
	
}
