package com.learning.ds;

public class MyDoubleLinkedList<T> {
	
	Node<T> head;
	Node<T> tail;
	
	public static class Node<T>{
		T data;
		Node<T> next;
		Node<T> prev; 
		public Node(T data) {
			super();
			this.data = data;
		}
		public Node(Node<T> prev,T data, Node<T> next) {
			super();
			this.prev = prev;
			this.data = data;
			this.next = next;
		}		
	}
	
	public void add(T value) {
		Node<T> newNode=new Node<>(tail,value,null);
		if(head==null)
			head=newNode;
		else 
		   tail.next=newNode;
		tail=newNode;
	}

	public T remove() {
		if(tail==null)
			return null;
		T value=tail.data;
		if(tail.prev==null)//It has one node
			head=null;
		else
		    tail.prev.next=null;
		tail=tail.prev;
		return value;
	}

	public void addFirst(T value) {
		Node<T> newNode=new Node<>(null,value,head);
		if(head==null)
			tail=newNode;
		else 
		   head.prev=newNode;
		head=newNode;
	}

	public T removeFirst() {
		if(head==null)
			return null;
		T value=head.data;
		if(head.next==null)//It has one node
			tail=null;
		else
			head.next.prev=null;
		head=head.next;
		return value;
	}

	
	public void traverseForward() {
		Node<T> currentNode=head;
		while(currentNode!=null) {
			System.out.println(currentNode.data);
			currentNode=currentNode.next;
		}
	}
	
    public void traverseReverse() {
    	Node<T> currentNode=tail;
		while(currentNode!=null) {
			System.out.println(currentNode.data);
			currentNode=currentNode.prev;
		}
	}
	@Override
	public String toString() {
		StringBuffer strBuffer= new StringBuffer();
		 Node<T> currentNode=head;
				while(currentNode!=null) {
					strBuffer.append("[").append(currentNode.data).append("]");
					if(currentNode.next!=null)
						strBuffer.append("->");
					currentNode=currentNode.next;
				}
		return strBuffer.toString();
	}
	public static void main(String[] args) {
		MyDoubleLinkedList<Integer> doubleLinkedList= new MyDoubleLinkedList<>();
		doubleLinkedList.add(20);
		doubleLinkedList.add(40);
		doubleLinkedList.add(70);
		doubleLinkedList.add(80);
		System.out.println(doubleLinkedList);
		doubleLinkedList.traverseForward();
		doubleLinkedList.traverseReverse();
		doubleLinkedList.remove();
		doubleLinkedList.remove();
		System.out.println(doubleLinkedList);
		doubleLinkedList.traverseForward();
		doubleLinkedList.traverseReverse();
		doubleLinkedList.addFirst(5);
		doubleLinkedList.addFirst(10);
		System.out.println(doubleLinkedList);
		doubleLinkedList.removeFirst();
		System.out.println(doubleLinkedList);
	}

}
