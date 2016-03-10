package com.gxy.liststackqueue;

@SuppressWarnings("unchecked")
public class MyArrayList<E> extends MyAbstractList<E> {

	public static final int INITIAL_CAPACITY = 16;
	public E[] data = (E[])new Object[INITIAL_CAPACITY];
	
	public MyArrayList() {
	}
	
	public MyArrayList(E[] objects) {
		for (int i = 0; i < objects.length; i++)
			add(objects[i]); // warning: don't use super(objects)!
	}
	
	@Override
	public void add(int index, E e) {
		ensureCapacity();
		
		//move the element to the right after the specified index
		for (int i = size - 1; i >=index; i--)
			data[i + 1] = data[i];
		
		//insert new element to data[index]
		data[index] = e;
		
		//increase size by 1
		size++;
	}
	
	/**
	 * create a new large array, double the current size + 1 
	 */
	private void ensureCapacity() {
		if (size >= data.length) {
			E[] newData = (E[])(new Object[size * 2 + 1]);
			System.arraycopy(data, 0, newData, 0, size);
			data = newData;
		}
	}

	@Override
	public void clear() {
		data = (E[])new Object[INITIAL_CAPACITY];
		size = 0;
	}

	@Override
	public boolean contains(E e) {
		for (int i = 0; i < size; i++)
			if (e.equals(data[i])) return true;
		
		return false;
	}

	@Override
	public E get(int index) {
		return data[index];
	}

	@Override
	public int indexOf(E e) {
		for (int i = 0; i < size; i++) 
			if(e.equals(data[i])) return i;
		
		return -1;
	}

	@Override
	public int lastIndexOf(E e) {
		for (int i = size; i >= 0; i--)
			if (e.equals(data[i]))
				return i;

		return -1;
	}

	@Override
	public E remove(int index) {
		E e = data[index];
		
		//shift data to the left
		for (int i = index; i < size - 1; i++)
			data[i] = data[i + 1];

		data[size - 1] = null;// this element is now null
		
		//decrement size
		size--;
		
		return e;
	}

	@Override
	public E set(int index, E e) {
		E old = data[index];
		data[index] = e;
		return old;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		
		for (int i = 0; i < size; i++) {
			result.append(data[i]);
			if (i < size - 1)
				result.append(", ");
		}
		
		return result.toString() + "]";
	}
	
	/**
	 * trims the capacity to current size
	 */
	public void trimToSize() {
		if (size != data.length) {
			//if size == capacity, no need to trim
			E[] newData = (E[])new Object[size];
			System.arraycopy(data, 0, newData, 0, size);
			data = newData;
		}
	}
	
}
