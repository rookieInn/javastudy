package com.gxy.liststackqueue;

/**
 * MyAbstractList声明变量size，表示线性表中的元素个数。
 * 该类实现了isEmpty()、size()、add(E)、remove(E)
 * @author gxy
 * @date 2016年1月22日下午2:23:29
 * @param <E>
 */
public abstract class MyAbstractList<E> implements MyList<E> {

	protected int size = 0; // the size of the list 
	
	/** create a default list */
	protected MyAbstractList() {	
	}
	
	protected MyAbstractList(E[] objects){
		for (int i = 0; i < objects.length; i++)
			add(objects[i]);
	}
	
	@Override
	public void add(E e) {
		add(size, e);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean remove(E e) {
		if(indexOf(e) >= 0) {
			remove(indexOf(e));
			return true;
		} else 
			return false;
	}

	@Override
	public int size() {
		return size;
	}

}
