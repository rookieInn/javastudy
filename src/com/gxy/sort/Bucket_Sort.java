package com.gxy.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 桶排序
 * @author gxy
 * @date 2016年1月21日下午2:21:26
 */
public class Bucket_Sort {

	public static void main(String[] args) {
		Integer[] arr = {2, 3, 2, 5, 6, 1, 3, 14, 12};
		bucketSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void bucketSort(Integer[] arr) {
		ArrayList<Integer>[] buckets = new ArrayList[100];
		
		for (int i = 0; i < arr.length; i++) {
			Integer key = arr[i];
			
			if (null == buckets[key])
				buckets[key] = new ArrayList();
			
			buckets[key].add(arr[i]);
		}
		
		int k = 0;
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				for (int j = 0; j < buckets[i].size(); j++) {
					arr[k++] = buckets[i].get(j);
				}
			}
		}
		
	}
	
}