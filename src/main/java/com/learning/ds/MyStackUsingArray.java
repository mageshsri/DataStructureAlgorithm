package com.learning.ds;

public class MyStackUsingArray<T> {
	
	private Object []  list;
	
	private int top=-1;
	
	private final static int DEFAULT_SIZE=20; 
	
	public MyStackUsingArray() {
		super();
		this.list = new Object[DEFAULT_SIZE];
	}

	public void push(T value) {
		list[++top]=value;
	}
	
	public T pop() {
		return top==-1?null:getData(top--);
	}

	public T peek() {
		return top==-1?null:getData(top);
	}
	
   @SuppressWarnings("unchecked")
   private T getData(int index) {
	   return (T) list[index];
   }
	
	public static void main(String[] args) {
		MyStackUsingArray<Integer> stack= new MyStackUsingArray<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}

}
