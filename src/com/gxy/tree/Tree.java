package com.gxy.tree;

import java.util.Iterator;

public interface Tree<E extends Comparable<E>> {
	
	public boolean search(E e); //如果指定的元素在书中则返回true
	
	public boolean insert(E e); //如果成功添加元素则返回true
	
	public boolean delete(E e);	//如果数中成功删除元素则返回true

	public void inorder(); //打印以中序遍历的结点
	
	public void preorder();	//打印以前序遍历的结点
	
	public void postorder(); //打印以后序遍历的结点
	
	public int getSize(); //返回树中的元素
	
	public boolean isEmpty(); //如果数为空则返回true
	
	public Iterator<E> iterator(); //返回遍历元素的迭代器
	
	public void clear(); //删除树中所有元素
	
}
