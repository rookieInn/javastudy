package com.gxy.sort;

import java.util.Arrays;

import com.gxy.sort.heap.Heap;

public class Heap_Sort {
	
	public static void main(String[] args) {
		Integer[] arr = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	/**
	 * heap sort method
	 * @param list
	 */
	public static <E extends Comparable<E>> void heapSort(E[] arr) {
		//create a heap of integers
		Heap<E> heap = new Heap<E>();
		
		//add elements to the heap
		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i]);
		}
		
		//remove elements from the heap
		for (int i = arr.length - 1; i>= 0; i--) {
			arr[i] = heap.remove();
		}	
	}
	
}
