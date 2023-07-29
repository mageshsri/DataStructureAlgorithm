package com.learning.ds;

public class MyQueueUsingArray<T> {
	
	private static final Integer DEFAULT_CAPACIRY=3;
	private int front;
	private int tail;
	private Object[] list;
	
	public MyQueueUsingArray() {
		super();
		this.front=-1;
		this.tail=-1;
		list= new Object[DEFAULT_CAPACIRY];
	}

	public void add(T value) {
		list[++tail]=value;
		if(front==-1)
			++front;
	}
	
	public T remove() {
		if(front==-1)
			return null;
		T value=getData(front++);
		if(front>tail) {
			front=-1;
			tail=-1;	
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	private T getData(int index) {
		// TODO Auto-generated method stub
		return (T) list[index];
	}

	public static void main(String[] args) {
		MyQueueUsingArray<Integer> queue= new MyQueueUsingArray<>();
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
