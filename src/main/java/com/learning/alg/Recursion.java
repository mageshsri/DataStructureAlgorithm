package com.learning.alg;

import java.util.Arrays;

public class Recursion {

	public static void main(String[] args) {
		
		Recursion recursion= new Recursion();
		System.out.println(recursion.factorial(5));
		System.out.println(recursion.fibonocci(40));
		int [] arry= new int[41];
		Arrays.fill(arry, -1);
		System.out.println(recursion.fibonocciWithMemoization1(40,arry));
		System.out.println(recursion.powerOf(2,10));
		System.out.println(recursion.powerOfWithGoodPerformnce(2,10));
		System.out.println(recursion.reverseString1("Hello Magesh"));
		System.out.println("hi".substring(1));
		System.out.println("h".substring(1));
	}

	private int fibonocciWithMemoization1(int n, int[] arry) {
		if(n<=1)
			return n;
		else if(arry[n]!=-1)
			return arry[n];
		else {
			arry[n]=fibonocciWithMemoization1(n-1,arry)+fibonocciWithMemoization1(n-2,arry);
			return arry[n];
		}
		
	}

	private String reverseString1(String str) {
		if(str.isEmpty())
			return "";
		else
			return reverseString1(str.substring(1))+str.charAt(0);
	}

	private int factorial(int n) {
		if(n==1)
			return 1;
		return n*factorial(n-1);
	}
	
	private int fibonocci(int n) {
		if(n==0 || n==1)
			return n;
		return fibonocci(n-1)+fibonocci(n-2);
	}
	
	private int fibonocciWithMemoization(int n, int[] arry) {
		if(n==0 || n==1)
			return n;
		else if(arry[n]!=-1)
			return arry[n];
		else
		{
			arry[n]=fibonocciWithMemoization(n-1, arry)+fibonocciWithMemoization(n-2, arry);
			return arry[n];
		}
	}
	
	private int powerOf(int x, int n) {
		if(n==0)
			return 1;
		return x*powerOf(x, n-1);
	}

	private int  powerOfWithGoodPerformnce(int x, int n) {
		if(n==0)
			return 1;
		else if(n%2==0)
		{
			int y=powerOfWithGoodPerformnce(x,n/2);
			return y*y;
		}else
			return x*powerOf(x, n-1);
		
	}
	
	private String reverseString(String str) {
		System.out.println("str "+str);
		if(str.isEmpty())
			return "";
		else
			return reverseString(str.substring(1))+str.charAt(0);
	}
}
