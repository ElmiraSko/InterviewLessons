package ru.erasko.task2;

import java.util.concurrent.CountDownLatch;

// Класс для тестирования
public class MyThread implements Runnable {

    CountDownLatch latch;
    Counter counter;

    public MyThread(CountDownLatch latch, Counter counter) {
        this.latch = latch;
        this.counter = counter;
    }
    @Override
    public void run() {
        for (int j = 0; j < 5; j++) {
            counter.increase();
        }
        System.out.println(counter.getCount());
        latch.countDown(); // уменьшили счетчик потоков на единицу
    }
}
