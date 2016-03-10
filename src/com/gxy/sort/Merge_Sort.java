package com.gxy.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author gxy
 * @date 2016年1月19日下午2:54:39
 * 
 * 归并排序算法
 * public static void merge(int[] list){
 *  if(list.length > 1) {
 *   mergeSort(list[0... list.length/2]);
 *   mergeSort(list[list.length /2 + 1 ... list.length]);
 *   merge list[0 ... list.length / 2] with list[list.length /2 + 1 ... list.length];
 *  }
 * }
 * 
 */
public class Merge_Sort {
	
	public static void main(String[] args) {
		int[] arr = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
		mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void mergeSort(int[] arr) {
		if(arr.length > 1) {
			//merge sort the first half
			int[] firstHalf = new int[arr.length / 2];
			System.arraycopy(arr, 0, firstHalf, 0, arr.length / 2);
			mergeSort(firstHalf);
			
			//merge sort the second half
			int secondHalfLength = arr.length - arr.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(arr, arr.length / 2, secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);
			
			//merge first half and second half
			int[] temp = merge(firstHalf, secondHalf);
			System.arraycopy(temp, 0, arr, 0, temp.length);
		}
	}
	
	private static int[] merge(int[] arr1, int[] arr2) {
		int[] temp = new int[arr1.length + arr2.length];
		
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		
		while (index1 < arr1.length && index2 < arr2.length) {
			if(arr1[index1] < arr2[index2])
				temp[index3++] = arr1[index1++];
			else
				temp[index3++] = arr2[index2++];
		}
		
		while (index1 < arr1.length)
			temp[index3++] = arr1[index1++];
		
		while (index2 < arr2.length)
			temp[index3++] = arr2[index2++];
		
		return temp;
	}
	
}
