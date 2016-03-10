package com.gxy.sort;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * 外部排序
 * @author gxy
 * @date 2016年1月21日下午3:13:35
 */
public class External_Sort {

	public static final int MAX_ARRAY_SIZE = 100000;
	public static final int BUFFER_SIZE = 100000;
	
	public static void main(String[] args) throws Exception {
		//sort largedata.dat to sortedfile.dat
		sort("largedata.dat", "sortedfile.dat");
		
		//display the first 100 nubmers in the sorted file
		displayFile("sortedfile.dat");
	}
	
	/**
	 * sort data in source file and into target file
	 * @param sourcefile
	 * @param targetfile
	 * @throws Exception 
	 */
	public static void sort(String sourcefile, String targetfile) throws Exception {
		//implement phase 1: create initial segments
		int numberOfSegments = initializeSegments(MAX_ARRAY_SIZE, sourcefile, "f1.dat");
		
		//implement phase 2: merge segments recursively
		merge(numberOfSegments, MAX_ARRAY_SIZE, "f1.dat", "f2.dat", "f3.dat", targetfile);
		
	}
	
	/**
	 * sort original file into sorted segments
	 * @param maxArraySize
	 * @param sourcefile
	 * @param string
	 * @return
	 * @throws Exception 
	 */
	private static int initializeSegments(int segmentSize, String originalFile, String f1) throws Exception {
		int[] arr = new int[segmentSize];
		DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(originalFile)));
		DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f1)));
		
		int numberOfSegments = 0;
		while (input.available() > 0) {
			numberOfSegments++;
			int i = 0;
			for ( ; input.available() > 0 && i < segmentSize; i++) {
				arr[i] = input.readInt();
			}
			
			//sort an array to f1.dat
			Arrays.sort(arr, 0, i);
			
			//write the array list[0..i-1]
			for (int j = 0; j < i; j++) {
				output.writeInt(arr[j]);
			}
		}	
		
		input.close();
		output.close();
		
		return numberOfSegments;
	}
	
	private static void merge(int numberOfSegments, int segmentSize, String f1, String f2, String f3, String targetfile) throws Exception {
		if (numberOfSegments > 1) {
			mergeOneStep(numberOfSegments, segmentSize, f1, f2, f3);
			merge((numberOfSegments + 1) / 2, segmentSize * 2, f3, f1, f2, targetfile);
		} else { //rename f1 as the final sorted file
			File sortedFile = new File(targetfile);
			if (sortedFile.exists()) 
				sortedFile.delete();
			
			new File(f1).renameTo(sortedFile);
		}
	}

	private static void mergeOneStep(int numberOfSegments, int segmentSize, String f1, String f2, String f3) throws Exception {
		DataInputStream f1Input = new DataInputStream(new BufferedInputStream(new FileInputStream(f1), BUFFER_SIZE));
		DataOutputStream f2Output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f2), BUFFER_SIZE));
		
		//copy half number of segment from f1.dat to f2.dat
		copyHalfToF2(numberOfSegments, segmentSize, f1Input, f2Output);
		f2Output.close();
		
		DataInputStream f2Input = new DataInputStream(new BufferedInputStream(new FileInputStream(f2), BUFFER_SIZE));
		DataOutputStream f3Output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f3), BUFFER_SIZE));
		
		mergeSegments(numberOfSegments / 2, segmentSize, f1Input, f2Input, f3Output);
		
		f1Input.close();
		f2Input.close();
		f3Output.close();
	}

	/**
	 * Copy first half number of segments from f1.dat to f2.dat
	 * @param numberOfSegments
	 * @param segmentSize
	 * @param f1Input
	 * @param f2Output
	 * @throws Exception 
	 */
	private static void copyHalfToF2(int numberOfSegments, int segmentSize, DataInputStream f1,
			DataOutputStream f2) throws Exception {
		for (int i = 0; i < (numberOfSegments / 2) * segmentSize; i++) {
			f2.writeInt(f1.readInt());
		}
	}
	
	/**
	 * merge all segments
	 * @param i
	 * @param segmentSize
	 * @param f1Input
	 * @param f2Input
	 * @param f3Output
	 * @throws Exception 
	 */
	private static void mergeSegments(int numberOfSegments, int segmentSize, DataInputStream f1, DataInputStream f2, DataOutputStream f3) throws Exception {
		for (int i = 0; i < numberOfSegments; i++) {
			mergeTwoSegments(segmentSize, f1, f2, f3);
		}
		
		//f1 may have one extra segment, copy it to f3
		while (f1.available() > 0) {
			f3.writeInt(f1.readInt());
		}
	}

	private static void mergeTwoSegments(int segmentSize, DataInputStream f1, DataInputStream f2, DataOutputStream f3) throws Exception {
		int intFromF1 = f1.readInt();
		int intFromF2 = f2.readInt();
		int f1Count = 1;
		int f2Count = 1;
		
		while (true) {
			if (intFromF1 < intFromF2) {
				f3.writeInt(intFromF1);
				if (f1.available() == 0 || f1Count++ >= segmentSize) {
					f3.writeInt(intFromF2);
					break;
				} else {
					intFromF1 = f1.readInt();
				}
			} else {
				f3.writeInt(intFromF2);
				if (f2.available() == 0 || f2Count++ >= segmentSize) {
					f3.writeInt(intFromF1);
					break;
				} else {
					intFromF2 = f2.readInt();
				}
			}
		}
		
		while (f1.available() > 0 && f1Count++ < segmentSize) {
			f3.writeInt(f1.readInt());
		}
		
		while (f2.available() > 0 && f2Count++ < segmentSize) {
			f3.writeInt(f2.readInt());
		}
	}
	
	public static void displayFile(String filename) {
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(filename));
			for (int i = 0; i < 100; i++)
				System.out.print(input.readInt() + " ");
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
