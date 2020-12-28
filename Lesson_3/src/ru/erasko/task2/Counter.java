package ru.erasko.task2;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void increase() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();
        try {
            if (count > 0)
            count--;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
