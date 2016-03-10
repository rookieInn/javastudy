package com.gxy.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author gxy
 * @date 2016年1月19日下午4:56:49
 * 
 * 快速排序算法
 * public static void quickSort(int[] arr) {
 *  if(arr.length > 1){
 *   select a pivot;
 *   patition arr into arr1 and arr2 such that
 *   	all elements in arr1 <= pivot and
 *   	all elements in arr2 >
 *   quickSort(arr1);
 *   quickSort(arr2);
 *  }
 * }
 * 
 */
public class Quick_Sort {
	
	public static void main(String[] args) {
		int[] arr = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
		quickSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}

	private static void quickSort(int[] arr, int first, int last) {
		if(first < last) {
			int pivotIndex = partition(arr, first, last);
			quickSort(arr, first, pivotIndex - 1);
			quickSort(arr, pivotIndex + 1, last);
		}
	}
	
	/**
	 * partition the array arr[first... last]
	 * @param arr
	 * @param first
	 * @param last
	 * @return
	 */
	private static int partition(int[] arr, int first, int last) {
		int pivot = arr[first]; //choose the first elements as the pivot
		int low = first + 1; //index for forward search
		int high = last; //index for backward search
		
		while(high > low) {
			//search forward from left
			while(low <= high && arr[low] <= pivot)
				low++;
			
			//search backward from right
			while(low <= high && arr[high] > pivot)
				high--;
			
			//swap two elements in the arr
			if(high > low) {
				int temp = arr[high];
				arr[high] = arr[low];
				arr[low] = temp;
			}
		}
		
		while (high > first && arr[high] >= pivot)
			high--;
		//swap pivot with arr[high]
		if (pivot > arr[high]) {
			arr[first] = arr[high];
			arr[high] = pivot;
			return high;
		}else {
			return first;
		}
	}
	
}
