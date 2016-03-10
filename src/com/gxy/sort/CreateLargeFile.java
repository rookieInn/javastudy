package com.gxy.sort;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 生成一个保存百万级别int数据的dat文件
 * @author gxy
 * @date 2016年1月21日下午3:08:33
 */
public class CreateLargeFile {

	public static void main(String[] args) throws Exception {
		String fileName = "largedata.dat";
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		
		for (int i = 0; i < 800004; i++) 
			out.writeInt((int)(Math.random() * 1000000));
		
		out.close();
		
		//display first 100 number
		DataInputStream input = new DataInputStream(new FileInputStream(fileName));
		for (int i = 0; i < 100; i++) 
			System.out.print(input.readInt() + " ");
		
		input.close();
	}
	
}
