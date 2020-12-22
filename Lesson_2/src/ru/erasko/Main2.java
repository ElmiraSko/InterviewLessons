package ru.erasko;

import java.util.Iterator;

class Main2 {
    public static void main(String[] args) {

        MyArrayList<String> list = new MyArrayList<>(7);
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(list.toString());  // MyArrayList [a, b, c, d, null, null, null]
        list.add(2, "m");
        list.add("i");
        list.add("f");
        list.set(0, "A");
        System.out.println(list.toString());  // MyArrayList [A, b, m, c, d, i, f]

        System.out.println("Удалили: " + list.remove(2));  // Удалили: m
        System.out.println("Количество элементов: " + list.size()); // Количество элементов: 6
        System.out.println("Емкость: " + list.getCapacity());   // Емкость: 7
        System.out.println(list.toString());     // MyArrayList [A, b, c, d, i, f, null]

        System.out.println();

        System.out.println("Удалили: " + list.remove(0));     // Удалили: A
        System.out.println("Количество элементов: " + list.size()); // Количество элементов: 5
        System.out.println("Емкость: " + list.getCapacity());      // Емкость: 7
        System.out.println(list.toString());       // MyArrayList [b, c, d, i, f, null, null]

        System.out.println();

        System.out.println("Удалили: " + list.remove("d"));   // Удалили: d
        System.out.println("Количество элементов: " + list.size());   // Количество элементов: 4
        System.out.println("Емкость: " + list.getCapacity());     // Емкость: 7
        System.out.println(list.toString());        // MyArrayList [b, c, i, f, null, null, null]
        System.out.println(list.get(3));             // f

        list.clear();
        System.out.println(list.isEmpty());    // true
        System.out.println(" === linkedList === ");

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 7; i++) {
            linkedList.add(i*2+1);
        }
        System.out.println(linkedList);     //  [ 1, 3, 5, 7, 9, 11, 13 ]

        linkedList.addElement(1, 80);
        System.out.println(linkedList);     //  [ 1, 80, 3, 5, 7, 9, 11, 13 ]
        System.out.println(linkedList.indexOf(5));  //  3
        System.out.println(linkedList.removeFirst());  //  1
        System.out.println(linkedList.removeLast());  //  13
        linkedList.removeElement(80);
        System.out.println(linkedList);         //  [ 3, 5, 7, 9, 11 ]

        for (Integer integer : linkedList) {
            System.out.print(integer + " ");  //  3 5 7 9 11
        }





    }
}
