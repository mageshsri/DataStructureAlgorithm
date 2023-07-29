package com.learning.ds;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStackUsingLinkedList<T> {
	
	private Node<T> top;
	
	static class Node<T>{
		T data;
		Node<T> next;
		public Node(T data,Node<T> next) {
			super();
			this.data = data;
			this.next=next;
		}
		
	}
	
	public void push(T value) {
		Node<T> newNode=new Node<>(value,top);
		top=newNode;
		
	}
	
	public T pop() {
		if(top==null)
			return null;
		T value=top.data;
		top=top.next;
		return value;
	}
	
	public T peek() {
		return top!=null?top.data:null;
	}
	
	public boolean isEmpty() {
		return top==null;
	}
	
	public static void main(String[] args) {
		MyStackUsingLinkedList<Integer> stack= new MyStackUsingLinkedList<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
