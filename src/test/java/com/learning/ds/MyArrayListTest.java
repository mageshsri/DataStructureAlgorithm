package com.learning.ds;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    private MyArrayList<String> stringList;
    private MyArrayList<Integer> integerList;

    @BeforeEach
    public void setUp() {
        stringList = new MyArrayList<>();
        integerList = new MyArrayList<>();
    }

    @Test
    public void testAdd() {
        stringList.add("Java");
        stringList.add("DataStructure");
        stringList.add("Algorithm");
        assertEquals(3, stringList.size());
        assertEquals("Java", stringList.get(0));
        assertEquals("DataStructure", stringList.get(1));
        assertEquals("Algorithm", stringList.get(2));
    }

    @Test
    public void testAddAtIndex() {
        testAdd();
        stringList.add(1, "Spring");
        assertEquals(4, stringList.size());
        assertEquals("Java", stringList.get(0));
        assertEquals("Spring", stringList.get(1));
        assertEquals("DataStructure", stringList.get(2));
        assertEquals("Algorithm", stringList.get(3));

        stringList.add(3, "Hibernate");
        assertEquals(5, stringList.size());
        assertEquals("Java", stringList.get(0));
        assertEquals("Spring", stringList.get(1));
        assertEquals("DataStructure", stringList.get(2));
        assertEquals("Hibernate", stringList.get(3));
        assertEquals("Algorithm", stringList.get(4));
    }

    @Test
    public void testRemove() {
        testAddAtIndex();
        String removedElement = stringList.remove();
        assertEquals("Algorithm", removedElement);
        assertEquals(4, stringList.size());
        assertEquals("Java", stringList.get(0));
        assertEquals("Spring", stringList.get(1));
        assertEquals("DataStructure", stringList.get(2));
        assertEquals("Hibernate", stringList.get(3));
    }

    @Test
    public void testRemoveAtIndex() {
        testAddAtIndex();
        String removedElement = stringList.remove(2);
        assertEquals("DataStructure", removedElement);
        assertEquals(4, stringList.size());
        assertEquals("Java", stringList.get(0));
        assertEquals("Spring", stringList.get(1));
        assertEquals("Hibernate", stringList.get(2));
        assertEquals("Algorithm", stringList.get(3));
    }
}
