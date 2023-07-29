package com.learning.ds;

public class MyQueueUsingLinkedList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	
	static class Node<T>{
		T data;
		Node<T> next;
		public Node(T data) {
			super();
			this.data = data;
		}
	}
	
	public void add(T value) {
		Node<T> newNode= new Node<>(value);
		if(head==null)
			head=newNode;
		else
			tail.next=newNode;
		tail=newNode;
	}
	
	public T remove() {
		if(head==null)
			return null;
		T value=head.data;
		head=head.next;
		if(head==null)
			tail=null;
		return value;
	}

	public boolean isEmpty() {
		return head==null;
	}
	public static void main(String[] args) {
		MyQueueUsingLinkedList<Integer> queue= new MyQueueUsingLinkedList<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		queue.add(3);
		System.out.println(queue.remove());
	}

}
