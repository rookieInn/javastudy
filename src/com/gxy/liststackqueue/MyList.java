package com.gxy.liststackqueue;

/**
 * 线性表是按照顺序存储数据时常用的一种数据结构。
 * 
 * 线性表的实现方式有两种。
 * 		一种是数组存储线性表的元素。数组是动态创建的。如果元素超过了数组的容量，就创建一个更大的新数组，并将当前数组中的元素复制到新数组中。
 * 		一种是链式结构。链式结构由结点组成，每个节点都是动态创建的，用来存储一个元素。所有的结点链表接成一个线性表。
 * 
 * 可以给线性表定义两个类。方便起见成为MyArrayList和MyLinkedList。这两种类具有相同的操作，但是具有不同的实现。		
 * 
 * @author gxy
 * @date 2016年1月22日下午1:59:28
 */
public interface MyList<E> {

	/** add a new element at the end of the list */
	public void add(E e);
	
	/** add a new element at the specified index in the list */
	public void add(int index, E e);
	
	/** clear the list */
	public void clear();
	
	/** return true if this list contains the element */
	public boolean contains(E e);
	
	/** return the element from this list at the specified index */
	public E get(int index);
	
	/** return the index of the first matching element in this list. return -1 if no match */
	public int indexOf(E e);
	
	/** return true if this list contains no elements */
	public boolean isEmpty();
	
	/** return the index of the last matching element in this list. return -1 if no match. */
	public int lastIndexOf(E e);
	
	/** remove the first occurrence of the element o from this list.
	 *  Shift any subsequent elements to the left.
	 *  return true if the element is removed. */
	public boolean remove(E e);
	
	/** remove the element at the specified position in this list
	 *  Shift any subsequent elements to the left.
	 *  return the element that was removed form the list. */
	public E remove(int index);
	
	/** replace the element at the specified position in this list 
	 *  with the specified element and return the new set. */
	public E set(int index, E e);
	
	/** return the number of elements in this list */
	public int size();
	
}
