package com.gxy.tree;

import java.util.Scanner;

import com.gxy.sort.heap.Heap;

/**
 * 哈夫曼 编码
 * 
 * 哈夫曼编码通过使用很少的比特对经常出现的字符编码来压缩数据。
 * 字符编码是基于字符在文本中出现的次数使用二叉树来构建的，该树称为哈夫曼编码数。
 * 
 * 为了构建一颗哈夫曼编码树，使用一个贪婪算法，如下所示：
 * 1. 从由树构成的森林开始。每棵树都包含一个字符结点。每个结点的权重就是文本中字符的出现次数。
 * 2. 重复这个步骤直到只有一棵树为止：
 *    选择两颗有最小权重的树，创建一个新结点作为它们的父结点。这棵树的权重是两颗子树的权重和。
 *    对于每个内部结点，给它的左边赋值0，而给它的右边赋值1.所有的叶子结点都表示文本中的字符。
 * 
 * 
 * @author gxy
 * @date 2016年2月10日下午2:14:17
 */
public class HuffmanCode {
	  public static void main(String[] args) {
	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter a text: ");
	    String text = input.nextLine();
	    input.close();
	    
	    int[] counts = getCharacterFrequency(text); // Count frequency

	    System.out.printf("%-15s%-15s%-15s%-15s\n",
	      "ASCII Code", "Character", "Frequency", "Code");  
	    
	    Tree tree = getHuffmanTree(counts); // Create a Huffman tree
	    String[] codes = getCode(tree.root); // Get codes
	        
	    for (int i = 0; i < codes.length; i++)
	      if (counts[i] != 0) // (char)i is not in text if counts[i] is 0
	        System.out.printf("%-15d%-15s%-15d%-15s\n", 
	          i, (char)i + "", counts[i], codes[i]);
	  }
	  
	  /** Get Huffman codes for the characters 
	   * This method is called once after a Huffman tree is built
	   */
	  public static String[] getCode(Tree.Node root) {
	    if (root == null) return null;    
	    String[] codes = new String[2 * 128];
	    assignCode(root, codes);
	    return codes;
	  }
	  
	  /* Recursively get codes to the leaf node */
	  private static void assignCode(Tree.Node root, String[] codes) {
	    if (root.left != null) {
	      root.left.code = root.code + "0";
	      assignCode(root.left, codes);
	      
	      root.right.code = root.code + "1";
	      assignCode(root.right, codes);
	    }
	    else {
	      codes[(int)root.element] = root.code;
	    }
	  }
	  
	  /** Get a Huffman tree from the codes */  
	  public static Tree getHuffmanTree(int[] counts) {
	    // Create a heap to hold trees
	    Heap<Tree> heap = new Heap<Tree>(); // Defined in Listing 24.10
	    for (int i = 0; i < counts.length; i++) {
	      if (counts[i] > 0)
	        heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
	    }
	    
	    while (heap.getSize() > 1) { 
	      Tree t1 = heap.remove(); // Remove the smallest weight tree
	      Tree t2 = heap.remove(); // Remove the next smallest weight 
	      heap.add(new Tree(t1, t2)); // Combine two trees
	    }

	    return heap.remove(); // The final tree
	  }
	  
	  /** Get the frequency of the characters */
	  public static int[] getCharacterFrequency(String text) {
	    int[] counts = new int[256]; // 256 ASCII characters
	    
	    for (int i = 0; i < text.length(); i++)
	      counts[(int)text.charAt(i)]++; // Count the character in text
	    
	    return counts;
	  }
	  
	  /** Define a Huffman coding tree */
	  public static class Tree implements Comparable<Tree> {
	    Node root; // The root of the tree

	    /** Create a tree with two subtrees */
	    public Tree(Tree t1, Tree t2) {
	      root = new Node();
	      root.left = t1.root;
	      root.right = t2.root;
	      root.weight = t1.root.weight + t2.root.weight;
	    }
	    
	    /** Create a tree containing a leaf node */
	    public Tree(int weight, char element) {
	      root = new Node(weight, element);
	    }
	    
	    /** Compare trees based on their weights */
	    public int compareTo(Tree o) {
	      if (root.weight < o.root.weight) // Purposely reverse the order
	        return 1;
	      else if (root.weight == o.root.weight)
	        return 0;
	      else
	        return -1;
	    }

	    public class Node {
	      char element; // Stores the character for a leaf node
	      int weight; // weight of the subtree rooted at this node
	      Node left; // Reference to the left subtree
	      Node right; // Reference to the right subtree
	      String code = ""; // The code of this node from the root

	      /** Create an empty node */
	      public Node() {
	      }
	      
	      /** Create a node with the specified weight and character */
	      public Node(int weight, char element) {
	        this.weight = weight;
	        this.element = element;
	      }
	    }
	  }  
	}