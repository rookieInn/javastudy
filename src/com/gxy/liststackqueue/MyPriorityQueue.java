package com.gxy.liststackqueue;

import com.gxy.sort.heap.Heap;

/**
 * 优先队列
 * 
 * 普通队列是一种先进先出结构，元素在队列尾部追加，而从队列头删除。在优先队列中元素被赋予优先级。
 * 当访问元素时，具有最高优先级的元素被最先删除。优先队列具有最高进先出的行为特征。
 * 
 * 可以用堆实现优先队列，其中根结点是队列中具有最高优先级的对象。
 * 
 * 
 * @author gxy
 * @date 2016年1月25日下午5:30:13
 */
public class MyPriorityQueue<E extends Comparable<E>> {

	private Heap<E> heap = new Heap<E>();
	
	public void enqueue(E newObject) {
		heap.add(newObject);
	}
	
	public E dequeue() {
		return heap.remove();
	}
	
	public int getSize() {
		return heap.getSize();
	}
	
}
