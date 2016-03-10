package com.gxy.sort;

import java.util.Arrays;

/**
 * 基数排序
 * @author gxy
 * @date 2016年1月21日下午2:34:46
 */
public class Radix_Sort {

    public static void main(String[] args) {
        int[] array = {3,2,3,2,5,333,45566,2345678,78,990,12,432,56};
        int time = countDigit(array);
        //System.out.println(time);
        radixSort(array,10,time);
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
    }
	
	/**
	 * 找出最大的元素，根据最大的数获得排序的趟数
	 * @param array
	 * @return
	 */
	public static int countDigit(int[] array) {
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		
		int time = 0;
		while (max > 0) {
			max /= 10;
			time ++;
		}
		return time;
	}
	
	/**
	 * 基数排序
	 * @param array  待排序数组
	 * @param radix  代表基数，实际就是几个数字，那就是10
	 * @param digit  代表排序元素的位数，实际就是排序的趟数
	 */
	public static void radixSort(int[] array, int radix, int digit) {
		int length = array.length;
		int[] res = new int[length];
		int[] c = new int[radix]; // radix就是10，因为0-9共10个数字
		int divide = 1; // 用于每次把数字缩小10倍
		
		for (int i = 0; i < digit; i++) {
			res = Arrays.copyOf(array, length);
			Arrays.fill(c, 0);
			
			for (int j = 0; j < length; j++) {
				int tempKey = (res[j]/divide) % radix;
				c[tempKey]++;
			}
			
			for (int j = 1; j < radix; j++) {
				c[j] = c[j] + c[j-1];
			}
			
			for (int j = length-1; j >= 0; j--) {
				int tempKey = (res[j]/divide) % radix;
				array[c[tempKey] - 1] = res[j];
				c[tempKey]--;
			}
			
			divide *= radix; 
		}
	}
	
}
