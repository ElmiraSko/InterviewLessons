package ru.erasko.task2;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {

        Counter counter = new Counter(); // тестируем счетчик counter
        CountDownLatch latch = new CountDownLatch(5); // счетчик выполняемых дочерних потоков
        // запускаем 5 потоков
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new MyThread(latch, counter));
            thread.start();
            System.out.println(thread.getName() + " запущен");
        }

        for (int i = 0; i < 5; i++) {
            counter.decrease();
        }

        try {
            latch.await(); // главный поток ждёт завершения дочерних потоков
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.getCount() + " - итоговое значение в main");
    }
}
