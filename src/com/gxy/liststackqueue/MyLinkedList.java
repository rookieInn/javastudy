package com.gxy.liststackqueue;

public class MyLinkedList<E> extends MyAbstractList<E> {

	private Node<E> head, tail;
	
	public MyLinkedList() {
	}
	
	public MyLinkedList(E[] objects) {
		super(objects);
	}
	
	/**
	 * return the head element in the list
	 * @return
	 */
	public E getFirst() {
		if (size == 0) {
			return null;
		}
		else {
			return head.element;
		}
	}
	
	/**
	 * return the last element in the list
	 * @return
	 */
	public E getLast() {
		if (size == 0) {
			return null;
		}
		else {
			return tail.element;
		}
	}
	
	/**
	 * add an element to the end of the list
	 * @param e
	 */
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e); //create a new code
		newNode.next = head; // link the new node with the head
		head = newNode; //head points to the new node
		size++; // increase list size
		
		if (tail == null) { //the new node is the only in list
			tail = head;
		}
	}
	
	/**
	 * add an element to the end of the list
	 * @param e
	 */
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e); // create a new node for e 
		
		if(tail == null) {
			head = tail = newNode; // the only node in list
		}
		else {
			tail.next = newNode; //link the new with the last node
			tail = tail.next; // tail now points to the last node
		}
		
		size++;
		
	}
	
	@Override
	public void add(int index, E e) {
		if (index == 0) addFirst(e); //insert first
		else if (index >= size) addLast(e); //insert last
		else { //insert in the middle
			Node<E> current = head;
			for (int i = 0; i < index; i++)
				current = current.next;
			
			Node<E> temp =current.next;
			
			current.next = new Node<E>(e);
			(current.next).next = temp;
			size++;
		}
	}

	public E removeFirst() {
		if (size == 0) return null;
		else if (size ==1){
			Node<E> temp = head;
			head = tail = null; // list becoms empty
			size = 0;
			return temp.element;
		} 
		else {
			Node<E> temp = head;
			head = head.next;
			size--;
			return temp.element;
		}
	}
	
	public E removeLast() {
		if (size == 0) return null; // nothing to remove
		else if (size == 1) { //Only one element in the list
			Node<E> temp = head;
			head = tail = null; // list becoms empty
			size = 0;
			return temp.element;
		}
		else {
			Node<E> current = head;
			for (int i = 0; i < size - 2; i++)
				current = current.next;
			
			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}
	
	@Override
	public void clear() {
		head = tail = null;
	}

	@Override
	public boolean contains(E e) {
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (e.equals(current.element)) {
				return true;
			}
			
			current = current.next;
		}
		
		return false;
	}

	@Override
	public E get(int index) {
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		return current.element;
	}

	@Override
	public int indexOf(E e) {
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			if (e.equals(current.element)) {
				return i;
			}
			
			current = current.next;
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(E e) {
		Node<E> current = tail;
		for (int i = size-1; i >= 0; i--) {
			if (e.equals(current.element)) {
				return i;
			}
			
			current = current.next;
		}
		
		return -1;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) return null; //out of range
		else if (index ==0) removeFirst();
		else if (index == size - 1) return removeLast();
		else {
			Node<E> privous = head;
			for (int i = 0; i < index; i++) {
				privous = privous.next;
			}
			
			Node<E> current = privous.next;
			privous.next = current.next;
			size--;
			return current.element;
		}
		return null;
	}

	@Override
	public E set(int index, E e) {
		Node<E> privous = head; //前一个结点
		for (int i = 0; i < index - 1; i++) {
			privous = privous.next;
		}
		
		Node<E> temp = privous.next;
		privous.next = new Node<E>(e);
		(privous.next).next = temp.next;
		return temp.element;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			sb.append(current.element);
			current = current.next;
			if (current != null) {
				sb.append(",");
			}
			else {
				sb.append("]");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 结点
	 * 
	 * 链表中的每一个元素都包含一个称为结点的结构。
	 * 当向链表中加入一个新的元素时，就会产生一个包含它的结点。每个结点都和它的相邻结点相链接。
	 * 
	 * @author gxy
	 * @date 2016年1月22日下午4:53:12
	 * @param <E>
	 */
	private static class Node<E>{
		E element;
		Node<E> next;
		
		public Node(E element) {
			this.element = element;
		}
	}
	
}
