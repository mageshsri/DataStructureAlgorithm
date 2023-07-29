package com.learning.alg;

import java.util.Arrays;


public class Sorting {

	public static void main(String[] args) {
         int [] arry= {10,12,4,3,9,5,13,1};
         System.out.println("Array elemets before sorting::"+Arrays.toString(arry));
         Sorting sorting= new Sorting();
        // sorting.bubbleSort(arry);
         //sorting.mergeSort(arry);
         //System.out.println("Array elemets after merge sorting::"+Arrays.toString(arry));
         //sorting.quickSort(arry,0,arry.length-1);
         //System.out.println("Array elemets after quick sorting::"+Arrays.toString(arry));
         sorting.selectionSort(arry);
         //sorting.insertionSort(arry);
	}

	private void insertionSort(int[] arry) {
		int len=arry.length;
		for(int i=1;i<len;i++) {
			int val=arry[i];
			int hole=i;
			while(hole>0 && val<arry[hole-1])
			{
				arry[hole]=arry[hole-1];
				hole--;
			}
			arry[hole]=val;
		}
		System.out.println("Array elemets after insertion sorting::"+Arrays.toString(arry));
	}

	private void selectionSort(int[] arry) {
		int len=arry.length;
		for(int i=0;i<len-1;i++) {
			int min=i;
			for(int j=i+1;j<len;j++)
			{
				if(arry[j]<arry[min])
					min=j;
			}
			swap(arry,i,min);
		}
		System.out.println("Array elemets after selection sorting::"+Arrays.toString(arry));
	}
  void quickSort(int[] arry, int startIndx, int endIndx) {
		System.out.println(" startIndx "+startIndx+" endIndx "+endIndx);
		if(startIndx<endIndx)
		{	
		 int partnIndx=partitionForQuickSort(arry,startIndx,endIndx);
		 System.out.println("partnIndx "+partnIndx+" partioned Array "+Arrays.toString(arry));
		 quickSort(arry,startIndx,partnIndx-1);
		 quickSort(arry,partnIndx+1,endIndx);
		}
	}

	private int partitionForQuickSort(int[] arry, int startIndx, int endIndx) {
		int pivot=arry[endIndx];
		int partnIndx=startIndx;
		for(int i=startIndx;i<endIndx;i++) {
			if(arry[i]<=pivot) {
				swap(arry,i,partnIndx);
				partnIndx++;
			}
		}
		swap(arry,partnIndx,endIndx);
		return partnIndx;
	}

	private void mergeSort(int[] arry) {
		int n=arry.length;
		if(n<2)
			return;
		int m=n/2;
		int l[]= new int[m];
		int r[]= new int[n-m];
		for(int i=0;i<m;i++)
			l[i]=arry[i];
		for(int j=0;j<n-m;j++)
			r[j]=arry[j+m];
		mergeSort(l);
		mergeSort(r);
		merge(arry,l,r);
		
	}

	private void merge(int[] arry, int[] l, int[] r) {
		int lIndx=0;
		int rIndx=0;
		int mIndx=0;
		while(lIndx<l.length && rIndx<r.length)
		{
			if(l[lIndx]<=r[rIndx])
				arry[mIndx++]=l[lIndx++];
			else
				arry[mIndx++]=r[rIndx++];
		}
		while(lIndx<l.length)
			arry[mIndx++]=l[lIndx++];
		while(rIndx<r.length)
			arry[mIndx++]=r[rIndx++];  
	}

	private void bubbleSort(int[] arry) {
		int len=arry.length;
		for(int outer=len-1;len>0;--len) {
			for(int inner=0;inner<outer;inner++)
			{
				if(arry[inner]>arry[inner+1]) {
					swap(arry,inner,inner+1);
				}
			}
		}
		
		System.out.println("Array elemets after Bubble sort::"+Arrays.toString(arry));
		
	}

	private void swap(int[] arry, int inner, int outer) {
		// TODO Auto-generated method stub
		int temp=arry[inner];
		arry[inner]=arry[outer];
		arry[outer]=temp;
	}

}
