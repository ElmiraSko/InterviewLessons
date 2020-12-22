package ru.erasko;

import java.util.Arrays;

public class MyArrayList<T>{

    private static final int DEFAULT_CAPACITY = 10;
    protected int capacity;
    private int elementCounter = 0;

    private T[] array;

    // Конструкторы

    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        array = (T[])new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    // Основные методы

    public int size() {
        return elementCounter;
    }

    public boolean add(T element) {
        if (elementCounter == array.length) {
            growCapacity();
        }
        array[elementCounter] = element;
        elementCounter++;
        return true;
    }
    // Добавление элемента по индексу
    public boolean add(int index, T element) {
        checkIndex(index, elementCounter);
        if (elementCounter == array.length) {
            growCapacity();
        }
        for (int i = elementCounter; i >= index; i--) {
            array[i+1] = array[i];
        }
        array[index] = element;
        elementCounter++;
        return true;
    }

    // Замена элемента по индексу
    public void set(int index, T element) {
        checkIndex(index, elementCounter);
        array[index] = element;
    }

    public T get(int index) {
        checkIndex(index, elementCounter);
        return array[index];
    }

    public T remove(int index) {
        int m = -1;
        T el = null;
        checkIndex(index, elementCounter);
        for (int i = 0; i < elementCounter; i++) {
            if (array[i] == array[index]) {
               m = i;
               el = (T) array[i];
               break;
            }
        }
        move(m, elementCounter);
        elementCounter--;
        array[elementCounter] = null;
        return el;
    }

    public T remove (T element) {
        int m = -1;
        T el = null;
        for (int i = 0; i < elementCounter; i++) {
           if (array[i].equals(element)) {
               m = i;
               el = (T) array[i];
               break;
           }
        }
        move(m, elementCounter);
        elementCounter--;
        array[elementCounter] = null;
        return el;
    }

    public boolean isEmpty() {
        return elementCounter == 0;
    }

    public void clear() {
        for (int i = 0; i < elementCounter; i++) {
            array[i] = null;
        }
        elementCounter = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "MyArrayList " +
                Arrays.toString(array);
    }

    private void move(int with, int length) {
        for (int i = with; i < length - 1; i++) {
            array[i] = array[i+1];
        }
    }

    private void growCapacity() {
        capacity = (capacity * 3)/2;
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, elementCounter);
        array = newArray;
    }

    private void checkIndex(int index, int length) {
        if (index < 0 || index >= length)
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + length);
    }
}
