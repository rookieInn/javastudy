package com.gxy;

import java.util.Scanner;

/**
 * 求最大公约数的算法
 * @author gxy
 * @date 2016年1月5日下午2:00:29
 */
public class GCD {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter first integer: ");
		int m = input.nextInt();
		System.out.println("Enter second integer: ");
		int n = input.nextInt();
		
		System.out.println("the greatest common divisor for " + m + " and " + n + " is " + gcd1(m,n));
		input.close();
	}
	
	public static int gcd1(int m, int n){
		if(m % n == 0)
			return n;
		else 
			return gcd1(n, m % n);
	}
	
	/**
	 * find greatest common divisor for integers m and n
	 * @param m
	 * @param n
	 * @return
	 */
	public static int gcd(int m, int n) {
		int gcd = 1;
		
		if(m % n == 0) return n;
		
		for (int k = n / 2; k >= 1; k--) {
			if(m % k ==0 && n % k==0){
				gcd = k;
				break;
			}
		}
		
		return gcd;
	}
	
}
