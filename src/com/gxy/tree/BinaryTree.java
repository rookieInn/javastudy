package com.gxy.tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 二叉查找树
 * @author gxy
 * @date 2016年2月4日下午8:29:04
 * @param <E>
 */
public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E> {

	protected TreeNode<E> root;
	protected int size = 0;
	
	/**
	 * create a default binary search tree
	 */
	public BinaryTree() {
	}
	
	/**
	 * create a binary search tree from an arrays of objects
	 * @param objects
	 */
	public BinaryTree(E[] objects) {
		for (int i = 0; i < objects.length; i++) {
			insert(objects[i]);
		}
	}
	
	/**
	 * return true if the element is in the tree
	 */
	@Override
	public boolean search(E e) {
		TreeNode<E> current = root; //Start from the root
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else //element matches current.element
				return true;
		}
		
		return false;
	}

	/**
	 * insert element e into the binary search tree
	 * return true if the element is inserted successfully
	 */
	@Override
	public boolean insert(E e) {
		if (root == null) {
			root = createNewNode(e); //create a new root
		} else {
			//locate the parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if(e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					return false; //Duplicate node not inserted
			}
			
			if (e.compareTo(parent.element) < 0) {
				parent.left = createNewNode(e);
			} else
				parent.right = createNewNode(e);
		}
		
		size++;
		return true; //Element inserted
	}

	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}
	
	/**
	 * Inorder traversal from the root
	 */
	public void inorder() {
		inorder(root);
	}
	
	/**
	 * Inorder traversal from a subtree
	 * @param root
	 */
	protected void inorder(TreeNode<E> root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}
	
	/**
	 * Postorder traversal from the root
	 */
	public void postorder() {
		postorder(root);
	}
	
	/**
	 * Postorder traversal from a subtree
	 * @param root
	 */
	protected void postorder(TreeNode<E> root) {
		if(root == null) return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}
	
	/**
	 * Preorder traversal from the root
	 */
	public void preorder() {
		preorder(root);
	}
	
	/**
	 * Preorder traversal from a subtree
	 * @param root
	 */
	protected void preorder(TreeNode<E> root) {
		if (root == null) return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}
	
	/**
	 * Delete an element from the binary search tree.
	 * Return true if the element is deleted successfully
	 * Return false if the element is not in the tree
	 */
	@Override
	public boolean delete(E e) {
		//Locate the node to deleted and also locate its parent node
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			}  else
				break; //Element is in the tree pointed by current
		}
		
		if (current == null) 
			return false; //Element is not in the tree
		
		//Case 1 : current has no left children
		if (current.left == null) {
			//Connect the parent with the right child of the current node
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0) {
					parent.left = current.right;
				} else
					parent.right = current.right;
			}
		} else {
			//Case 2 : The current nodes has a left child
			//Locate the rightmost node in the left subtree of
			//the current node and also its parent
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left; 
			
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;//keep going to the right
			}
			
			//Replace the element in current by element in rightMost
			current.element = rightMost.element;
			
			//Eliminate rightMost node
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else
				//Special case: parentOfRightMost == current
				parentOfRightMost.left = rightMost.left;
		}
		
		size--;
		return true; //Element deleted
	}

	/**
	 * Get the number of nodes in the tree
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * Returns the root of the tree
	 * @return
	 */
	public TreeNode<E> getRoot() {
		return root;
	}
	
	/**
	 * Returns a path from the root leading to the specified element
	 * @param e
	 * @return
	 */
	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> list = new ArrayList<TreeNode<E>>();
		TreeNode<E> current = root; //Start from the root
		
		while (current != null) {
			list.add(current); //Add the node to the list
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else
				break;
		}
		
		return list; //Return an array of nodes
	}
	
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Inner class tree node
	 * @author gxy
	 * @date 2016年2月5日上午9:27:19
	 * @param <E>
	 */
	public static class TreeNode<E extends Comparable<E>> {
		E element;
		TreeNode<E> left;
		TreeNode<E> right;
		
		public TreeNode(E e) {
			element = e;
		}
		
	}
	
	/**
	 * Obtain an iterator. Use inorder
	 */
	public Iterator<E> iterator() {
		return inorderIterator();
	}
	
	/**
	 * Obtain an inorder iterator
	 * @return
	 */
	public Iterator<E> inorderIterator() {
		return new InorderIterator();
	}
	
	/**
	 * Inner calss InorderIterator
	 * @author gxy
	 * @date 2016年2月5日下午7:29:53
	 */
	class InorderIterator implements Iterator<E> {
		
		//Store the elements in a list
		private ArrayList<E> list = new ArrayList<E>();
		private int current = 0; //Point to the current element in list
		
		public InorderIterator() {
			inorder(); //Traverse binary tree and store elements in list 
		}
		
		/**
		 * Inorder traversal from the root
		 */
		private void inorder() {
			inorder(root);
		}
		
		/**
		 * Inorder traversal from a subtree
		 */
		private void inorder(TreeNode<E> root) {
			if (root == null) {
				return;
			}
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}
		
		/**
		 * Next element for traversing?
		 */
		@Override
		public boolean hasNext() {
			if (current < list.size()) {
				return true;
			}
			return false;
		}

		/**
		 * Get the current element and move cursor to the next
		 */
		@Override
		public E next() {
			return list.get(current++);
		}
		
		/**
		 * Remove the current element and refresh the list
		 */
		public void remove() {
			delete(list.get(current)); //Delete the current element 
			list.clear(); //Clear the list
			inorder(); //Rebuild the list
		}
	}

}
