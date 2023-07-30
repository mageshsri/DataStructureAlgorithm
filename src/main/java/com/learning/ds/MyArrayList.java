package com.learning.ds;

import java.util.Arrays;

public class MyArrayList<T> {

    Object list[];

    private final static int DEFAULT_CAPACITY = 5;

    private int size = 0;

    public MyArrayList() {
        super();
        list = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }


    public T get(int index) {
        return getData(index);
    }


    public void add(T data) {
        checkCapacity();
        this.list[size++] = data;
    }

    public void add(int index, T data) {
        checkCapacity();
        for (int i = size - 1; i >= index; i--)
            list[i + 1] = list[i];
        list[index] = data;
        size++;
    }


    public T remove() {
        T data = getData(size - 1);
        this.list[--size] = null;
        return data;
    }


    public T remove(int index) {
        T data = getData(index);
        for (int i = index; i < size - 1; i++) {
            this.list[i] = this.list[i + 1];
        }
        this.list[--size] = null;
        return data;
    }

    @SuppressWarnings("unchecked")
    private T getData(int index) {
        return (T) this.list[index];
    }

    private void checkCapacity() {
        if (list.length == size) {
            Object[] newlist = new Object[this.list.length * 2];
            for (int i = 0; i < list.length; i++)
                newlist[i] = list[i];
            list = newlist;
        }
    }


    @Override
    public String toString() {

        return "MyArrayList [" + Arrays.toString(list) + "]";
    }

    public static void main(String[] args) {

        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hi");
        list.add("Hello");
        list.add("Hi1");
        list.add("Hello1");
        list.add(1, "oneindex");
        System.out.println(list.remove());
        System.out.println(list.remove(2));
        System.out.println(list + " size " + list.size);
        list.add("Hi2");
        list.add("Hello2");
        list.add(1, "last");
        System.out.println(list + " size " + list.size);
    }

}
