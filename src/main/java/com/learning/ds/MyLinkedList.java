package com.learning.ds;

public class MyLinkedList<T> {

   private Node<T> head;
   
   private int size;
	
   static class Node<T>{
	  
	  T data;
	  Node<T> next;
	  
	  public Node(T data) {
		super();
		this.data = data;
	  }
	  
   }
   
   public void add(T data) {
	   Node<T> newNode=new Node<>(data);
	   if(head==null) {
		   head=newNode;
	   }else {	   
	       Node<T> currentNode=head; 
		   while(currentNode.next!=null) 
			   currentNode=currentNode.next;
		   currentNode.next=newNode;
	   }
	   ++size;
   }
   
   public void addFirst(T data) {
	   Node<T> newNode=new Node<>(data);
	   newNode.next=head;
	   head=newNode;
	   ++size;
   }
   
   public void add(int index,T data) {
	   Node<T> newNode=new Node<>(data);
	   Node<T> currentNode=head;
	   if(index==0)
	   {
		 newNode.next=head;
		 head=newNode;
	   }else {
	     for(int i=1;i<index;i++)
	    	 currentNode=currentNode.next;
	     Node<T> nextNode=currentNode.next;
	     currentNode.next=newNode; 
	     newNode.next=nextNode;
	   }
	   ++size;
   }
   
   public T remove() {	 
	 Node<T> currentNode=head;
	 if(head.next==null) {
		 head=null;
		 --size;
		 return currentNode.data;
	 }
	 Node<T> prevNode=currentNode;
	 while(currentNode.next!=null)
	 {
		 prevNode=currentNode;
		 currentNode=currentNode.next;
	 }	
	 prevNode.next=null;
	 --size;
	 return currentNode.data;
   }
   
    public T removeFirst() {	 
		 Node<T> currentNode=head;
		 head=head.next;
		 --size;
		 return currentNode.data;
	}
	
    public T remove(int index) {	 
		 Node<T> currentNode=head;
		 if(index==0) {
			 head=head.next;
			 --size;
			 return currentNode.data;
		 }else {
			 for(int i=1;i<index;i++)
				 currentNode=currentNode.next;
			 T data=currentNode.next.data;
			 currentNode.next=currentNode.next.next;
			 --size;
			 return data;
		 }
	}
    
    public void traverseForward(Node<T> node) {
    	if(node==null)
    		return;
    	System.out.println(node.data);
    	traverseForward(node.next);
    }
    
    public void traverseReverse(Node<T> node) {
    	if(node==null)
    		return;
    	traverseReverse(node.next);
    	System.out.println(node.data);
    }
    
    public void reverseLinkedListIterative() {
    	Node<T> current=head;
    	Node<T> prev=null;
    	while(current!=null) {
    	    Node<T> next=current.next;
    	    current.next=prev;
    	    prev=current;
    	    current=next;
    	}
    	head=prev;
    }
    
    public void reverseLinkedListRecursive() {
    	reverse(head);
    }
	
    private void reverse(Node<T> node) {
		if(node.next==null) {
			head=node;
			return;
		}
    	reverse(node.next);
    	node.next.next=node;
    	node.next=null;
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
		
		MyLinkedList<Integer> linkedList= new MyLinkedList<>();
		linkedList.add(1);
		linkedList.add(45);
		linkedList.addFirst(0);
		linkedList.add(99);
		linkedList.add(2,33);
		System.out.println(linkedList);
		System.out.println(linkedList.remove());
		System.out.println(linkedList);
		System.out.println(linkedList.removeFirst());
		System.out.println(linkedList);
		System.out.println(linkedList.remove(1));
		System.out.println(linkedList);
		System.out.println(linkedList.remove());
		System.out.println(linkedList.remove());
		System.out.println(linkedList);
		linkedList.add(330);
		linkedList.add(120);
		linkedList.add(220);
		linkedList.add(450);
		linkedList.add(1);
		System.out.println(linkedList);
		linkedList.traverseForward(linkedList.head);
		linkedList.traverseReverse(linkedList.head);
		linkedList.reverseLinkedListIterative();
		System.out.println(linkedList);
		linkedList.reverseLinkedListRecursive();
		System.out.println(linkedList);
	}

	public void reverse() {
		reverse1(head);
	}
	
	public void reverse1(Node<T> node) {
		
		if(node.next==null) {
			head=node;
			return;
		}
		reverse1(node.next);
		node.next.next=node;
		node.next=null;
	}
	
}
