package com.learning.ds;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

	Node<T> root;

	static class Node<T> {
		T data;
		Node<T> left;
		Node<T> right;

		public Node(T data) {
			super();
			this.data = data;
		}

	}

	public void add(T value) {
		root = add(root, value);
	}

	private Node<T> add(Node<T> node, T value) {
		if (node == null) {
			node = new Node<>(value);
			return node;
		}
		if (value.compareTo(node.data) < 0) {
			node.left = add(node.left, value);
		} else {
			node.right = add(node.right, value);
		}
		return node;
	}

	public boolean contains(T value) {
		return contains(root, value);
	}

	private boolean contains(Node<T> node, T value) {
		if (node == null)
			return false;
		else if (value.equals(node.data))
			return true;
		else
			return value.compareTo(node.data) < 0 ? contains(node.left, value) : contains(node.right, value);
	}

	public T findMin() {
		return findMin(root);
	}

	public T findMin(Node<T> current) {
		if (current.left == null)
			return current.data;
		return findMin(current.left);
	}

	public T findMax() {
		return findMax(root);
	}

	private T findMax(Node<T> current) {
		if (current.right == null)
			return current.data;
		return findMax(current.right);
	}

	public void delete(T value) {
		root = delete(root, value);
	}

	private Node<T> delete(Node<T> node, T value) {
		if (node == null)
			return null;
		else if (value.compareTo(node.data) < 0) {
			node.left = delete(node.left, value);
		} else if (value.compareTo(node.data) > 0) {
			node.right = delete(node.right, value);
		} else {
			if (node.left == null && node.right == null)
				return null;
			else if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;
			else {
				T min = findMin(node.right);
				node.data = min;
				node.right = delete(node.right, min);
			}
		}
		return node;
	}

	public void inOrderTranversal() {
		System.out.println("inOrderTranversal");
		inOrderTranversal(root);
	}

	private void inOrderTranversal(Node<T> node) {
		if (node == null)
			return;
		inOrderTranversal(node.left);
		System.out.println(node.data);
		inOrderTranversal(node.right);
	}

	public void preOrderTranversal() {
		System.out.println("preOrderTranversal");
		preOrderTranversal(root);
	}

	private void preOrderTranversal(Node<T> node) {
		if (node == null)
			return;
		System.out.println(node.data);
		preOrderTranversal(node.left);
		preOrderTranversal(node.right);
	}

	public void postOrderTranversal() {
		System.out.println("postOrderTranversal");
		postOrderTranversal(root);
	}

	private void postOrderTranversal(Node<T> node) {
		if (node == null)
			return;
		postOrderTranversal(node.left);
		postOrderTranversal(node.right);
		System.out.println(node.data);
	}

	public void levelOrderTranversal() {
		System.out.println("levelOrderTranversal");
		LinkedList<Node<T>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<T> node = queue.removeFirst();
			System.out.println(node.data);
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);
		}
	}
	
	public List<T> rightSideView(){
		List<T> list= new ArrayList<>();
		LinkedList<Node<T>> queue=new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int qLen=queue.size();
			int counter=0;
			T value=null;
			while(counter<qLen) {
			Node<T> node=queue.removeFirst();
			value=node.data;
			if(node.left!=null)
				queue.add(node.left);
			if(node.right!=null)
				queue.add(node.right);
			++counter;
			}	
			list.add(value);
		}
		return list;
	}

	public int treeHeight() {
		return treeHeight(root);
	}

	private int treeHeight(Node<T> node) {
		if (node == null)
			return -1;
		else
			return Math.max(treeHeight(node.left), treeHeight(node.right)) + 1;
	}

	public boolean isBinarySearchTree() {
		return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	private boolean isBinarySearchTree(Node<T> node, Integer minValue, Integer maxValue) {
		if (node == null)
			return true;
		else
			return node.data.compareTo((T) minValue) > 0 && node.data.compareTo((T) maxValue) < 0
					&& isBinarySearchTree(node.left, minValue, (Integer) node.data)
					&& isBinarySearchTree(node.right, (Integer) node.data, maxValue);
	}
	
	public T lowestCommonAncestor(T val1,T val2) {
		return  lowestCommonAncestor(root,val1,val2);
	}

	private T lowestCommonAncestor(Node<T> node, T val1, T val2) {
		if(node==null)
			return null;
		if(node.data.compareTo(val1)>0 && node.data.compareTo(val2)>0)
		return lowestCommonAncestor(node.left,val1,val2);
		if(node.data.compareTo(val1)<0 && node.data.compareTo(val2)<0)
			return lowestCommonAncestor(node.right,val1,val2);
		return node.data;
	}
	
	public void merge(BinarySearchTree<T> tree1) {
		this.root=merge(this.root,tree1.root);
		
	}

	@SuppressWarnings("unchecked")
	private Node<T> merge(Node<T> node1, Node<T> node2) {
		if(node2==null)
			return node1;
		if(node1==null)
			return node2;
		Integer sum=(Integer)Integer.sum((Integer)node1.data, (Integer)node2.data);
		node1.data=(T) sum;
		node1.left=merge(node1.left,node2.left);
		node1.right=merge(node1.right,node2.right);
		return node1;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.add(22);
		tree.add(18);
		tree.add(26);
		tree.add(16);
		tree.add(20);
		tree.add(24);
		//tree.add(28);
/*	        22
          /    \
        18      26
      /   \    /  \
     16    20 24     */
		System.out.println(" Min value " + tree.findMin());
		System.out.println(" Max value " + tree.findMax());
		tree.inOrderTranversal();
		tree.preOrderTranversal();
		tree.postOrderTranversal();
		tree.levelOrderTranversal();
		System.out.println("Height of the tree " + tree.treeHeight());
		System.out.println("Is Binary Search tree " + tree.isBinarySearchTree());
		System.out.println(" Lowest Common Ancestor for 24 & 28 "+tree.lowestCommonAncestor(24,28));
		System.out.println(" Lowest Common Ancestor for 16 & 24 "+tree.lowestCommonAncestor(16,24));
		System.out.println(tree.rightSideView());
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
		tree1.add(12);
		tree1.add(10);
		tree1.add(22);
		tree1.add(11);
		tree1.add(21);
		tree1.add(24);
		tree1.add(28);
/*	        12
          /    \
        10      22
          \    /  \
           11 21   28   */
	  tree.merge(tree1);
	  tree.levelOrderTranversal();
	}

	

}
