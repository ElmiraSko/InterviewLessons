package ru.erasko.task1;

public class PingPong {

    boolean flag = false;

    synchronized void writePing() {
        while (flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Ping");
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        flag = true;
        notify();
    }

    synchronized void writePong() {
        while (!flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Pong");
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        flag = false;
        notify();
    }
}

