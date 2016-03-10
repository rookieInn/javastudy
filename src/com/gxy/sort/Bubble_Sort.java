package com.gxy.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author gxy
 * @date 2016年1月19日下午2:36:53
 */
public class Bubble_Sort {
	
	public static void main(String[] args) {
		int[] arr = {1,2,6,8,4,3435,7};
		bubleSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void bubleSort( int[] arr) {
		boolean needNextPass = true;
		
		for (int k = 1; k < arr.length && needNextPass; k++) {
			//Array may be sorted and next pass not need
			//如果某次遍历中没有发生交换，就不必进行下次遍历，所有元素已经排好序了
			needNextPass  = false;
			for (int i = 0; i < arr.length - k; i++) {
				if(arr[i] > arr[i+1]) {
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
					needNextPass = true;
				}
			}
			
		}
	} 
	
}
