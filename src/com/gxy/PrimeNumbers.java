package com.gxy;

import java.util.Scanner;

/**
 * 找出素数
 * @author gxy
 * @date 2016年1月5日下午2:46:43
 */
public class PrimeNumbers {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Find all prime numbers <=n, enter n: ");
		int n = input.nextInt();;
		input.close();
		
		final int NUMBER_PER_LINE = 10; // Display 10 per line
		int count = 0; //count the number of prime numbers
		int number = 2; //a number to be tested for primeness
		
		System.out.println("the prime numbers are: ");
		
		//repeatedly find prime numbers
		while(number <= n){
			//assume the number is prime
			boolean isPrime = true; //is the current number is prime?
			
			//test is number is prime
			for (int divisor = 2; divisor <= (int)(Math.sqrt(number)); divisor++) {
				if(number % divisor == 0) {//if true,divisor is not prime
					isPrime = false;
					break; //exit the for loop
				}
			}
			
			//print the prime number and increase the count
			if(isPrime) {
				count ++;//increase the count
				
				if(count % NUMBER_PER_LINE == 0) {
					//print the number and advance to the new line
					System.out.printf("%7d\n", number);
				} 
				else
					System.out.printf("%7d",number);
			}
			
			//check if the next number is prime
			number ++;
		}
		
		System.out.println("\n" + count + " prime(s) less than n equal to " + n);

	}
	
}
