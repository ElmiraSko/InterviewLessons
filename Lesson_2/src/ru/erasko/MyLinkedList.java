package ru.erasko;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T>{

    private Element firstElement; // указатель на первый элемент
    private Element lastElement; // указатель на последний элемент
    private int size = 0;

    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    private Element getFirstElement() {
        return firstElement;
    }

    // Вставка в начало
    public void  addFirst(T value) {
        Element e = new Element(value);
        if (isEmpty()) {
            firstElement = e;
            lastElement = e;
            size++;
        } else {
            firstElement.previous = e;
            e.next = firstElement;
            firstElement = e;
            size++;
        }
    }

    public boolean add(T value) {
        addLast(value);
        return true;
    }

    // Вставка в конец
    public void  addLast(T value) {
        Element e = new Element(value);
        if (isEmpty()) {
            firstElement = e;
            lastElement = e;
            size++;
        } else {
            lastElement.next = e;
            e.previous = lastElement;
            lastElement = e;
            size++;
        }
    }

    // Вставка элемента по индексу
    public void  addElement(int index, T value) {
        if (index >= size) { // если индекс больше количества элементов
            addLast(value);
        } else if (index >= size/2) {
            Element newElement = new Element(value);
            Element current = lastElement;
            int ind = size-1;
            while (ind >= size/2) {
                if (index == ind) {
                    newElement.next = current;
                    newElement.previous = current.previous;
                    current.previous.next = newElement;
                    size++;
                    break;
                }
                current = current.previous;
                ind--;
            }
        } else if (index > 0) {
            Element newElement = new Element(value);
            Element current = firstElement;
            int ind = 0;
            while (ind <= size/2) {
                if (index == ind) {
                    newElement.next = current;
                    newElement.previous = current.previous;
                    current.previous.next = newElement;
                    size++;
                    break;
                }
                current = current.next;
                ind++;
            }
        } else  if (index == 0) {
            addFirst(value);
        } else throw new IndexOutOfBoundsException("Отрицательный индекс!");
    }

    // Удаление первого элемента
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        Element current = firstElement;
        firstElement = firstElement.next;
        if (isEmpty()) {
            lastElement = null;
        } else
            firstElement.previous = null;
        size--;
        return current.value;
    }
    // Удаление последнего элемента
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        Element current = lastElement;
        lastElement = lastElement.previous;
        if (lastElement == null) {
            firstElement = null;
        } else
            lastElement.next = null;
        size--;
        return current.value;
    }

    // Удаление элемента по значению
    public void removeElement(T value) {
        if (isEmpty()) {
            throw new NoSuchElementException(" No elements!");
        }
        if (firstElement.value.equals(value)) {
            removeFirst();
        } else {
            Element current = firstElement; // текущий элемент - первый
            if (current.next == null) { // если current - последний элемент, значит совпадений нет
                throw new NoSuchElementException(" No such element " + value);
            }
            while (current.next != null) { // сравниваем со следующим
                if (current.next.value.equals(value)) {
                    current.next = current.next.next;
                    size--;
                    break;
                }
                current = current.next;
                if (current.next == null) {
                throw new NoSuchElementException(" No such element " + value);
                }
            }
        }
    }

    public boolean isEmpty() {
        return firstElement == null;
    }

    public int indexOf(T value) {
        int index = 0;
        Element current = firstElement;
        while (current != null) {
            if (current.value.equals(value))
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean isContains(T val) {
        return indexOf(val) > -1;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[ ]";
        StringBuilder sb = new StringBuilder("[ ");
        Element current = firstElement;
        while (current.next != null) {
            sb.append(current.value.toString()).append(", ");
            current = current.next;
        }
        sb.append(current.value.toString()).append(" ]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    // Внутренние классы
    private class MyIterator  implements Iterator<T> {
        Element current = new Element(null);

        MyIterator() {
            current.next = firstElement;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return current.value;
        }
    }

    private class Element {
        private T value;
        Element next;
        Element previous;

        Element(T value) {
            this.value = value;
        }
    }
}
