package com.gxy.sort.heap;

import java.util.ArrayList;

/**
 * 堆
 * @author gxy
 * @date 2016年1月19日下午6:09:52
 * 
 * 堆是一颗具有以下属性的二叉树：
 * 1. 它是一颗完全二叉树
 * 2. 每个结点大于或等于它的任意一个孩子
 * 
 * 对堆排序：
 * 		如果堆的大小是提前知道的，那么可以将堆存储在一个arraylist或一个数组中。
 *      树根在角标0处，它的两个孩子在角标1和角标2处，对于位置i处的结点，它的左孩子在2i+1处，它的右孩子在2i+2处，它的父亲在位置(i-1)/2处。
 *      
 * 添加新结点：
 * 	    为了给堆添加新结点，首先将它添加到堆的尾部，然后按照下边的方式重建这棵树
 *   let the last node be the current node;
 *   while(the current node is greater than its parent) {
 *   	swap the current node with its parent;
 *   	now the current node is one level up;
 *   }
 * 
 * 删除根结点：
 *   经常需要从堆中删除最大元素，也就是说这个堆中的根结点。再删除跟结点之后，就必须重建这颗树以保持堆的特征。
 *   重建这颗树的算法如下：
 *   move the last node to replace the root;
 *   let the root be the current node;
 * 
 * 
 */
public class Heap<E extends Comparable<E>> {

	private ArrayList<E> list = new ArrayList<>();
	
	public Heap() {
		
	}
	
	public Heap(E[] objects) {
		for (int i = 0; i < objects.length; i++) {
			add(objects[i]);
		}
	}

	/**
	 * add a new object into the heap
	 * @param newObject
	 */
	public void add(E newObject) {
		list.add(newObject); //append to the heap
		int currentIndex = list.size() - 1;// the index of the last node
		while (currentIndex > 0) {
			int parentIndex = (currentIndex - 1) / 2;
			//swap if the current object is greater than its parent
			if(list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
				E temp = list.get(currentIndex);
				list.set(currentIndex, list.get(parentIndex));
				list.set(parentIndex, temp);
			} else 
				break;
			
			currentIndex = parentIndex;
		}
	}
	
	/**
	 * remove the root from the heap
	 * @return
	 */
	public E remove() {
		if(list.size() == 0) 
			return null;
		
		E removeObject = list.get(0);
		list.set(0, list.get(list.size()-1));
		list.remove(list.size() - 1);
		
		int currentIndex = 0;
		while (currentIndex < list.size()) {
			int leftChildIndex = 2 * currentIndex + 1;
			int rightChildIndex = 2* currentIndex + 2;
			
			//find the maximum between two children
			if (leftChildIndex >= list.size())
				break; // the tree is a heap
			
			int maxIndex = leftChildIndex;
			if (rightChildIndex < list.size()) {
				if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0){
					maxIndex = rightChildIndex;
				}
			}
			
			//swap if the current node is less than the maximum
			if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
				E temp = list.get(maxIndex);
				list.set(maxIndex, list.get(currentIndex));
				list.set(currentIndex, temp);
				currentIndex = maxIndex;
			}
			else
				break; //the tree is a heap
		}	
		return removeObject;
	}
	
	/**
	 * get the number of nodes in the tree
	 * @return
	 */
	public int getSize() {
		return list.size();
	}
	
}
