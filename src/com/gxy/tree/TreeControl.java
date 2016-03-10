package com.gxy.tree;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.gxy.tree.BinaryTree.TreeNode;

public class TreeControl extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BinaryTree<Integer> tree; // A binary tree to be displayed
	private JTextField jtfKey = new JTextField(5);
	private TreeView view = new TreeView();
	private JButton jbtInsert = new JButton("Insert");
	private JButton jbtDelete = new JButton("Delete");
	
	/**
	 * Construct a view for binary tree
	 * @param tree
	 */
	public TreeControl(BinaryTree<Integer> tree) {
		this.tree = tree;
		setUI();
	}
	
	/**
	 * Initialize UI for binary tree
	 */
	private void setUI() {
		this.setLayout(new BorderLayout());
		add(view, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter a key: "));
		panel.add(jtfKey);
		panel.add(jbtInsert);
		panel.add(jbtDelete);
		add(panel, BorderLayout.SOUTH);
		
		//Listener for the Insert button
		jbtInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int key = Integer.parseInt(jtfKey.getText());
				if (tree.search(key)) { // key is in the tree already
					JOptionPane.showMessageDialog(null, key + " is already in the tree");
				} else {
					tree.insert(key); // Insert a new key
					view.repaint(); // Redisplay the tree
				}
			}
		});
		
		//Listener for the Delete button
		jbtDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int key = Integer.parseInt(jtfKey.getText());
				if (!tree.search(key)) { //key is not in the tree
					JOptionPane.showMessageDialog(null, key + " is not in the tree");
				} else {
					tree.delete(key); //Delete a key
					view.repaint(); //Redisplay the tree
				}
			}
		});
	}

	/**
	 * Inner class TreeView for displaying a tree on a panel
	 * @author gxy
	 * @date 2016年2月9日下午9:26:47
	 */
	class TreeView extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private int radius = 20; //Tree node radius
		private int vGap = 50; //Gap between two levels in a tree
	
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (tree.getRoot() != null) {
				//Display tree recursively
				displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
			}
		}

		/**
		 * Display a subtree rooted at position(x, y)
		 * @param g
		 * @param root
		 * @param i
		 * @param j
		 * @param k
		 */
		private void displayTree(Graphics g, TreeNode<Integer> root, int x, int y, int hGap) {
			//Display the root
			g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
			g.drawString(root.element + "", x - 6, y + 4);
			
			if (root.left != null) {
				//Draw a line to the left node
				connectLeftChild(g, x - hGap, y + vGap, x, y);
				//Draw the left subtree recursively
				displayTree(g, root.left, x - hGap, y + vGap, hGap / 2);
			}
			
			if (root.right != null) {
				//Draw a line to the right node
				connectRightChild(g, x + hGap, y + vGap, x, y);
				//Draw the right subtree recursively
				displayTree(g, root.right, x + hGap, y + vGap, hGap / 2);
			}
		}

		/**
		 * Connect a parent at (x2, y2) with its left child at (x1, y1)
		 */
		private void connectLeftChild(Graphics g, int x1, int y1, int x2, int y2) {
			double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
			int x11 = (int)(x1 + radius * (x2 - x1) / d);
			int y11 = (int)(y1 - radius * vGap / d);
			int x21 = (int)(x2 - radius * (x2 - x1) / d);
			int y21 = (int)(y2 + radius * vGap / d);
			g.drawLine(x11, y11, x21, y21);
		}
		
		/**
		 * Connect a parent at (x2, y2) with its right child at (x1, y1)
		 */
		private void connectRightChild(Graphics g, int x1, int y1, int x2, int y2) {
			double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
			int x11 = (int)(x1 - radius * (x1 - x2) / d);
			int y11 = (int)(y1 - radius * vGap / d);
			int x21 = (int)(x2 + radius * (x1 - x2) / d);
			int y21 = (int)(y2 + radius * vGap / d);
			g.drawLine(x11, y11, x21, y21);
		}
		
	}
	
}
